package com.contact.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.contact.entity.Groupe;

public interface GroupRepository extends JpaRepository<Groupe,Long> {
	

	@Query("SELECT c FROM Groupe c WHERE c.nom = :nom")
 
List<Groupe> findAllByNom(String nom);
	Groupe findByNom(String nom);
	Groupe findById(long id);

}
