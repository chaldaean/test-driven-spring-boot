package com.xpinjection.springboot.service;

import com.xpinjection.springboot.dao.BookDao;
import com.xpinjection.springboot.dao.ReaderDao;
import com.xpinjection.springboot.domain.Book;
import com.xpinjection.springboot.domain.Reader;
import org.hibernate.annotations.NotFound;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReaderServiceImplTest {
	private ReaderService readerService;
	@Mock
	private ReaderDao dao;

	@Before
	public void init() {
		readerService = new ReaderServiceImpl(dao);
	}

	@Test(expected = Exception.class)
	public void findRecommendationForReaderIfReaderNotFound() throws Exception {
		readerService.findRecommendationForReader(null);
	}

	@Test
	public void findRecommendationForReaderTopFiveBooks() throws Exception {
		Long readerId = 5L;
		Reader reader = new Reader("Name", 20);
		reader.setFavouriteAuthor("Author");

		when(dao.findOne(readerId)).thenReturn(reader);

		assertThat(readerService.findRecommendationForReader(readerId), not(empty()));
	}

}