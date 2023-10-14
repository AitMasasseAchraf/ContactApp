package com.contact.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="contact_tab")
public class Contacts {
	
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;

private String name;
private String prenom;
private String telephone1;
private String telephone2;
private String address;
private String gender;
private String emailpersonnel;
private String emailprofessionnel;


public Contacts() {
	super();
	// TODO Auto-generated constructor stub
}

public Contacts(String name, String prenom, String telephone1, String telephone2, String address, String gender,
		String emailpersonnel, String emailprofessionnel) {
	super();
	this.name = name;
	this.prenom = prenom;
	this.telephone1 = telephone1;
	this.telephone2 = telephone2;
	this.address = address;
	this.gender = gender;
	this.emailpersonnel = emailpersonnel;
	this.emailprofessionnel = emailprofessionnel;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getTelephone1() {
	return telephone1;
}
public void setTelephone1(String telephone1) {
	this.telephone1 = telephone1;
}
public String getTelephone2() {
	return telephone2;
}
public void setTelephone2(String telephone2) {
	this.telephone2 = telephone2;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmailpersonnel() {
	return emailpersonnel;
}
public void setEmailpersonnel(String emailpersonnel) {
	this.emailpersonnel = emailpersonnel;
}
public String getEmailprofessionnel() {
	return emailprofessionnel;
}
public void setEmailprofessionnel(String emailprofessionnel) {
	this.emailprofessionnel = emailprofessionnel;
}
@Override
public String toString() {
	return "Contacts [id=" + id + ", name=" + name + ", prenom=" + prenom + ", telephone1=" + telephone1
			+ ", telephone2=" + telephone2 + ", address=" + address + ", gender=" + gender + ", emailpersonnel="
			+ emailpersonnel + ", emailprofessionnel=" + emailprofessionnel + "]";
}

}
