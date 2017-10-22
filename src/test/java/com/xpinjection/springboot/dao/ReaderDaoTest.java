package com.xpinjection.springboot.dao;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.xpinjection.springboot.domain.Reader;
import org.junit.Test;
import org.springframework.test.annotation.Commit;

import static org.junit.Assert.assertNotNull;

public class ReaderDaoTest extends AbstractDaoTest<ReaderDao> {

	@Test
	@ExpectedDataSet("stored-reader.xml")
	@Commit
	public void readerShouldBeStoredInDB() {
		Reader reader = new Reader("Name", 20);
		reader.setFavouriteAuthor("Author");
		Reader stored = dao.save(reader);
		assertNotNull(stored.getId());
	}
}
