package com.xpinjection.springboot.web;

import com.xpinjection.springboot.domain.Reader;
import com.xpinjection.springboot.service.ReaderService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class BookRecommendationsSystemTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void showReadersRecommendedBooks() throws Exception {
		//		context.getBean(ReaderService.class).registerReader(new Reader("Name1", 20));

		String body = "{\"name\": \"Name1\", \"age\":\"20\", \"favouriteAuthor\":\"Who knows?\"}";
		Map reader = new ObjectMapper().readValue(body, Map.class);

		MvcResult result = mockMvc.perform(put("/reader").content(body)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andReturn();

		String contend = result.getResponse().getContentAsString();
		Map readerId = new ObjectMapper().readValue(contend, Map.class);

		mockMvc.perform(get("/reader/{id}/recommendation", readerId.get("id"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.length").value(1))
				.andExpect(jsonPath("$[0].name").value("Spring in Action"))
				.andExpect(jsonPath("$[0].author").value(reader.get("favouriteAuthor")));
	}
}
