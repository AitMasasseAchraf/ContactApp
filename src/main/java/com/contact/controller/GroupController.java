package com.contact.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.entity.Groupe;
import com.contact.entity.UserAccount;
import com.contact.repository.GroupRepository;
import com.contact.repository.UserAccountRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class GroupController {
	
	@Autowired
	GroupRepository grouperepository;
	@Autowired
	UserAccountRepository useraccountrepository;
	@GetMapping("/groups")
	public String show_groups( @ModelAttribute Groupe groupe,Model model){
		List<Groupe> list=grouperepository.findAll(Sort.by("nom").ascending());
		model.addAttribute("all_groups",list);
		return "group";
	}
	
	@PostMapping("/save_groups")
	public String saveGroups(@ModelAttribute Groupe groupe,Model model,HttpSession session) {
		grouperepository.save(groupe);
		List<Groupe> list=grouperepository.findAll(Sort.by("nom").ascending());
		model.addAttribute("all_groups",list);
		return "group";
		
	}
	
	@PostMapping("/search_groups")
	public String search_group(@RequestParam("nom") String nom, Model m) {
		
   
   
      List<Groupe>  list=grouperepository.findAllByNom(nom);
        
		m.addAttribute("searched_groups",list);
		return "searched_group";}
		
	@GetMapping("/delete_group/{id}")
	public String deleteGroups(@PathVariable(value = "id") long id, HttpSession session ,Model model) {
		
		Groupe groupe=grouperepository.findById(id);
		
		List<UserAccount> list_accounts=useraccountrepository.findByGroupe(groupe);
		for(UserAccount user:list_accounts) {
			useraccountrepository.deleteById(user.getId());
			
		}
		grouperepository.deleteById(id);
		
		List<Groupe> list=grouperepository.findAll(Sort.by("nom").ascending());
		model.addAttribute("all_groups",list);
		return "group";

	}
	@GetMapping("/delete_fromGroups/{id}")
	public String delete_fromGroups(@PathVariable(value = "id") long id,Model model,HttpSession session) {
		long group_id=(long)session.getAttribute("group_id");
		useraccountrepository.deleteById(id);
		Groupe group=grouperepository.findById(group_id);
		List<UserAccount> list=useraccountrepository.findByGroupe(group);
		
		model.addAttribute("list",list);
		return "group_members";	
		}
	
	 @PostMapping("/search_ingroup")
	 public String search_ingroup(@RequestParam("name") String name,Model model ,HttpSession session) {
		 long group_id=(long)session.getAttribute("group_id");
		 Groupe group=grouperepository.findById(group_id);
//		 System.out.println(group);
		List<UserAccount> list_accounts=useraccountrepository.findByGroupe(group);
		List<UserAccount> list=new ArrayList<>();
		for(UserAccount user:list_accounts) {
			if(user.getUsername().equals(name)) {
				list.add(user);
				
			}
			
		}
		model.addAttribute("list",list);
		 
		 return "group_members";
	 }
	 
	
}
