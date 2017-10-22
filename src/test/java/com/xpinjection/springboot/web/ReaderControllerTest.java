package com.xpinjection.springboot.web;

import com.xpinjection.springboot.domain.Book;
import com.xpinjection.springboot.domain.Reader;
import com.xpinjection.springboot.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReaderController.class)
@ActiveProfiles("test")
public class ReaderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReaderService readerService;

	@Test
	public void createReaderWithUniqueId() throws Exception {
		String body = "{\"name\": \"Name1\", \"age\":\"20\", \"favouriteAuthor\":\"Who knows?\"}";

		Reader reader = new Reader("Name1", 20);
		when(readerService.registerReader(reader)).thenReturn(5L);

		mockMvc.perform(put("/reader").content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(5));

		verify(readerService, only()).registerReader(reader);
	}

	@Test
	public void readerNameShouldBePresent() throws Exception {
		String body = "{\"age\":\"20\"}";

		mockMvc.perform(put("/reader").content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void readerAgeShouldBeInSpecificRange() throws Exception {
		String body = "{\"name\": \"Name1\", \"age\":\"0\"}";

		mockMvc.perform(put("/reader").content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void findRecommendedBooks() throws Exception {
		Long readerId = 10L;
		Book book = new Book();
		book.setName("Spring in Action");
		book.setAuthor("Who knows?");
		when(readerService.findRecommendationForReader(readerId)).thenReturn(Collections.singletonList(book));

		mockMvc.perform(get("/reader/{id}/recommendation", readerId)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].name").value(book.getName()))
				.andExpect(jsonPath("$[0].author").value(book.getAuthor()));
	}
}
