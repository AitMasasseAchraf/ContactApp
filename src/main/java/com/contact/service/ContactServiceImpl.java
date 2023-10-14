package com.contact.service;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.contact.entity.Contacts;
import com.contact.repository.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService {
	
	
	
	@Autowired
	ContactRepository contactrepository;
	
	 public  List<Contacts> findAllBytele( String name){
		 List<Contacts> list=new ArrayList<>();
		   list=contactrepository.findAllBytelephone1(name);
		  if( list.isEmpty()) {
			 list=contactrepository.findAllBytelephone2(name);
		  }
		  
		  return list;
	  }

	



	@Override
	public Contacts saveContact(Contacts contact) {
		// TODO Auto-generated method stub
		return null;
	}



	


	@Override
	public Contacts updateContact(Contacts contact, Long contactId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteContactById(Long contactId) {
		// TODO Auto-generated method stub
		
	}























	


	










	
	



	
	
}
