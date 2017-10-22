package com.xpinjection.springboot.service;

import com.xpinjection.springboot.dao.ReaderDao;
import com.xpinjection.springboot.domain.Book;
import com.xpinjection.springboot.domain.Reader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {
	private final ReaderDao dao;

	@Override
	public long registerReader(Reader reader) {
		return dao.save(reader).getId();
	}

	@Override
	public List<Book> findRecommendationForReader(Long id) throws Exception {
		if(id == null) {
			throw new Exception("Invalid id");
		}
		Reader reader = dao.findOne(id);

		return Collections.emptyList();
	}
}
