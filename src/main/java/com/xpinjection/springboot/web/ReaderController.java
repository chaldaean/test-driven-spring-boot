package com.xpinjection.springboot.web;

import com.xpinjection.springboot.domain.Book;
import com.xpinjection.springboot.domain.Reader;
import com.xpinjection.springboot.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReaderController {
	private final ReaderService readerService;

	@PutMapping(value = "/reader")
	public ReaderDTO createReader (@RequestBody @Valid Reader reader){
		long id = readerService.registerReader(reader);
		return new ReaderDTO(id);
	}

	@GetMapping(value = "/reader/{id}/recommendation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Book> findRecommendedBooks(@PathVariable Long id) throws Exception {
		List<Book> books = readerService.findRecommendationForReader(id);
		return books;
	}
}
