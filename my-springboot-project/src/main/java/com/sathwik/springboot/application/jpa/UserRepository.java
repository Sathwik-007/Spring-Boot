package com.sathwik.springboot.application.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * url : http://localhost:8080/questions
 * */
@RepositoryRestResource(path="questions", collectionResourceRel="questions")
public interface UserRepository extends PagingAndSortingRepository<MyUser, Long>{
	/**
	 * findBy{Column_Name}
	 * A free search method syntax offered by spring data provides the implementation for this
	 * */
	List<MyUser> findByRole(String role);
}
