package com.contact.controller;

import com.contact.functions.EDIST;
import java.util.*;
import jakarta.servlet.http.HttpSession;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.entity.Contacts;
import com.contact.entity.Groupe;
import com.contact.entity.UserAccount;
import com.contact.repository.ContactRepository;
import com.contact.repository.GroupRepository;
import com.contact.repository.UserAccountRepository;
import com.contact.service.ContactService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ContactController {
     
	@Autowired
	private GroupRepository grouperepository;
	@Autowired 
	private UserAccountRepository useraccountrepository;
	@Autowired
	private ContactRepository contactrepository;
	
	
	
	@Autowired 
	 private ContactService contactservice;
	
	@GetMapping("/")
	public String home(Model m) {
		List<Contacts> list=contactrepository.findAll(Sort.by("name").ascending());
		m.addAttribute("all_contacts",list);
		return "index";
		
	}
	
	@PostMapping("/search_contacts")
	public String search(@RequestParam("name") String name, Model m) {
		List<Contacts> contacts=contactrepository.findAll();
		List<String> names=new ArrayList<>();
		for (Contacts cont:contacts) {
			names.add(cont.getName());
			
		}
				
        String regex = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean isMatch = matcher.matches();
        List<Contacts> list=new ArrayList<>();
        if(isMatch) {
         list=contactservice.findAllBytele( name);
        	
        }else {
        	 list=contactrepository.findAllByName(name);
        	 
        	 if(list.isEmpty()) {
        		 String nearName=new EDIST().nearWord(names, name);
        		 list=contactrepository.findAllByName(nearName); 
        		 
        		 
        	 }
        	 }
        
		m.addAttribute("searched_contacts",list);
		return "search";
		
	}
	
	

	
	@GetMapping("/load_form")
	public String loadform() {
		return "add";
	}

	@GetMapping("/edit_form/{id}")
	public String editForm(@PathVariable(value = "id") long id, Model m) {

		Contacts cont = contactrepository.findById(id);

		
		m.addAttribute("contact", cont);

		return "edit";
	}

	@PostMapping("/update_products")
	public String updateProducts(@ModelAttribute Contacts contacts, HttpSession session) {

		contactrepository.save(contacts);
		

		return "redirect:/load_form";
	}
	
	@PostMapping("/save_products")
	public String saveProducts(@ModelAttribute Contacts contacts, HttpSession session) {

		contactrepository.save(contacts);
		
		
		
		return "redirect:/load_form";
	}
	@GetMapping("/delete/{id}")
	public String deleteProducts(@PathVariable(value = "id") long id, HttpSession session) {
		Contacts cont=contactrepository.findById(id);
		UserAccount user=useraccountrepository.findByContact(cont)  ;
		System.out.println(cont.getPrenom());
		if(user!=null) {
			
			useraccountrepository.deleteById(user.getId());
			
		}
		contactrepository.deleteById(id);
		

		return "redirect:/";

	}

	
	@GetMapping("/invite_cont/{nom}")
	public String invite(@PathVariable(name="nom") String nom,Model model,HttpSession session) {
		List<Contacts> list=contactrepository.findAll(Sort.by("name").ascending());
		session.setAttribute("nom_group", nom ) ;  
		model.addAttribute("contacts_invited",list);
		
		return "invited_people";
	}
 @GetMapping("/invite_contacts/{id}")
 public String invite_people(@PathVariable(value="id")  long id,Model model,HttpSession session) {
	
	 String nom_group=(String)session.getAttribute("nom_group");
	 Contacts contact=contactrepository.findById(id);
	
	 Groupe groupe=grouperepository.findByNom(nom_group);
	 
	 
	 UserAccount account=new UserAccount(contact.getName(),groupe,contact);
	 System.out.println(account.getGroupe());
	 
	 List<UserAccount> Allusers=useraccountrepository.findAll();
	 int index=0;
	 for(UserAccount user:Allusers) {
//		 if(user.getGroupe()!=null && user.getContact()!=null) {
		
		 if(user.getContact().equals(account.getContact()) && user.getGroupe().equals(account.getGroupe()) ) {
			 index++;
			    
		 }
		 
	 }
	 if(index==0) {
		 useraccountrepository.save(account);
	 }
	 
	 List<Contacts> list=contactrepository.findAll(Sort.by("name").ascending());

		model.addAttribute("contacts_invited",list);

	 return "invited_people";
 }

 
 
 
 @GetMapping("/show_cont/{id}")
 public String show(@PathVariable(value="id")  long id,Model model, HttpSession session) {
	session.setAttribute("group_id", id);
	 Groupe group=grouperepository.findById(id);
//	 System.out.println(group);
	List<UserAccount> list=useraccountrepository.findByGroupe(group);
//	System.out.println(list_accounts.get(0).getContact());
	model.addAttribute("list",list);

	 return "group_members";
 }
 @GetMapping("/show_cont_group/{id}")
 public String show_conts(@PathVariable(value="id")  long id,Model model, HttpSession session) {
	 UserAccount account=useraccountrepository.findById(id);
	 model.addAttribute("contact",account.getContact());
	 return "show_contact";
 }
 

 

	
	
	
	
	
}
