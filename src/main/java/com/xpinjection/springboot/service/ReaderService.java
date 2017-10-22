package com.xpinjection.springboot.service;

import com.xpinjection.springboot.domain.Book;
import com.xpinjection.springboot.domain.Reader;

import java.util.List;

public interface ReaderService {
	long registerReader(Reader reader);

	List<Book> findRecommendationForReader(Long id) throws Exception;
}
