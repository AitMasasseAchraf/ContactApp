package com.contact.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entity.Groupe;
import com.contact.entity.UserAccount;
import com.contact.entity.Contacts;
import java.util.*;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long>{
	 UserAccount findById(long id);
	List<UserAccount> findByGroupe(Groupe group);
	List<UserAccount> findByUsername(String username);
	UserAccount findByContact(Contacts cont);

}
