package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
		private ContactRepository contactRepository;

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}	

	@Override
	public Contact findContactById(Long id) {
		return contactRepository.findById(id).orElse(null);
	}
	@Override
	public void saveContact(ContactForm contactForm) {
		// TODO 自動生成されたメソッド・スタブ
		Contact contact = getContact(contactForm);
		contactRepository.save(contact);
	}

	@Override
	public void updateContact(Long id, ContactForm contactForm){
		Contact contact = contactRepository.findById(id).orElse(null);
		if (contact == null){
			return;
		}
		updateContactValues(contact, contactForm);
		contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
	

	private Contact getContact(ContactForm contactForm){
		Contact contact = new Contact();
		copyContactValues(contact, contactForm);
		return contact;
	}

	private void updateContactValues(Contact contact, ContactForm contactForm){
		copyContactValues(contact, contactForm);
	}
	
    //updateとsave共通化
	private void copyContactValues(Contact contact, ContactForm contactForm){
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());
	}
}
