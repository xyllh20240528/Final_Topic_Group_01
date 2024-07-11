package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
	
	@Query(value="select top(1) * from messages order by added desc", nativeQuery = true)
	public Messages findLatest();

}
