package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("from Customer where name = ?1")
	List<Customer> findByNameQuery(String name);
	
	@Query("from Customer where name = :n")
	List<Customer> findByNameQuery2(@Param("n") String name);
	
	@Query("from Customer where name like %:n%")
	List<Customer> findByNameLikeQuery(@Param("n") String name);

	@Query(value="select top(2) * from customer where name = :n", nativeQuery = true)
	List<Customer> findByNameNativeQuery(@Param("n") String name);
	
	@Transactional
	@Modifying
	@Query("update Customer set name = :n where id = :id")
	Integer updateNameById(@Param("n") String newName, @Param("id") Integer id);
	
	List<Customer> findByName(String name);
	
	List<Customer> findByLevelOrderByIdDesc(Integer level);
}
