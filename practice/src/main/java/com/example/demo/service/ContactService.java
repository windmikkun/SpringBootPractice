package com.example.demo.service;

import com.example.demo.form.ContactForm;

import java.util.List;

import com.example.demo.entity.Contact;


public interface ContactService {

	//おといあわせを全て取得する。
	List<Contact> findAllContacts();

	void saveContact(ContactForm contactForm);
	
	
}
