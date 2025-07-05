package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.service.ContactService;
import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ContactService contactService;

    //お問い合わせ一覧ページ
    @GetMapping("/contacts")
    public String listContacts(Model model) {
        model.addAttribute("contacts",contactService.findAllContacts());
        return "admin/contactList";
    }

    //お問い合わせ詳細ページ
    @GetMapping("/contact/{id}")
    public String showContact(@PathVariable Long id, Model model) {
        Contact contact = contactService.findContactById(id);
        if (contact == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"該当するIDがありません");
        }
        model.addAttribute("contact", contact);
        return "admin/contactDetail";
    }

    //編集
    @GetMapping("/contact/{id}/edit")
    public String editContact(@PathVariable Long id, Model model) {
        Contact contact = contactService.findContactById(id);
        if (contact == null){
            return "admin/contacts";
        }
        Contact contactForm = new Contact();
        contactForm.setLastName(contact.getLastName());
        contactForm.setFirstName(contact.getFirstName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhone(contact.getPhone());
        contactForm.setZipCode(contact.getZipCode());
        contactForm.setAddress(contact.getAddress());
        contactForm.setBuildingName(contact.getBuildingName());
        contactForm.setContactType(contact.getContactType());
        contactForm.setBody(contact.getBody());
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", id);
        return "admin/contactEdit";
    }

    //更新
    @PostMapping("/contact/{id}/edit")
    public String updateContact(@PathVariable Long id, @ModelAttribute @Valid ContactForm contactForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contactForm", contactForm);
            model.addAttribute("contactId", id);
            return "admin/contactEdit";
        }
        contactService.updateContact(id, contactForm);
        return "redirect:/admin/contact/" + id;
    } 

    //削除
    @PostMapping("/contact/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contacts";
    }
}
