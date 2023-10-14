package com.contact.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.contact.entity.Contacts;


public interface ContactRepository extends JpaRepository<Contacts,Long>{
	
	@Query("SELECT c FROM Contacts c WHERE c.name = :name")
    List<Contacts> findAllByName(@Param("name") String name);
	@Query("SELECT c FROM Contacts c WHERE c.telephone1 = :name ")
	List<Contacts> findAllBytelephone1(@Param("name") String name);
	@Query("SELECT c FROM Contacts c WHERE c.telephone2 = :name ")
	List<Contacts> findAllBytelephone2(@Param("name") String name);
	
	
	 
	@Query("SELECT c FROM Contacts c WHERE c.id = :id")
	Contacts findById(long id);
	

	
	
	
	
}
