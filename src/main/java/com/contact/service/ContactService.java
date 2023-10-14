package com.contact.service;

import java.util.List;



import com.contact.entity.Contacts;

public interface ContactService {
	Contacts saveContact(Contacts contact);
	  
    // Read operation

    
    List<Contacts> findAllBytele( String name);
  
    // Update operation
    Contacts updateContact(Contacts contact,
                                Long contactId);
      
    // Delete operation
    void deleteContactById(Long contactId);
  

}
