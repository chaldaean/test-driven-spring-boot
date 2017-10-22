package com.xpinjection.springboot.dao;

import com.xpinjection.springboot.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderDao extends JpaRepository<Reader, Long>{
}
