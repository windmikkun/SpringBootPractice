package com.example.demo.service;

import com.example.demo.form.ContactForm;

import java.util.List;

import com.example.demo.entity.Contact;


public interface ContactService {

	//おといあわせを全て取得する。
	List<Contact> findAllContacts();

	Contact findContactById(Long id);
	void deleteContact(Long id);

	void saveContact(ContactForm contactForm);
	
	void updateContact(Long id, ContactForm contactForm);
}
