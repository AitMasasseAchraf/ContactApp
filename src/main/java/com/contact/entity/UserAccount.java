package com.contact.entity;




import jakarta.persistence.*;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="useraccount_tab")
public class UserAccount {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	@OneToOne
   
	@JoinColumn(name = "group_id")
	private Groupe groupe;
	@OneToOne
	@JoinColumn(name = "contact_id")
	private Contacts contact;
	public long getId() {
		return id;
	}
	public UserAccount(String username, Groupe groupe, Contacts contact) {
		super();
		this.username = username;
		this.groupe = groupe;
		this.contact = contact;
	}
	
	public UserAccount() {
		super();
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Contacts getContact() {
		return contact;
	}
	public void setContact(Contacts contact) {
		this.contact = contact;
	}

	
	
	
	
	
	

}
