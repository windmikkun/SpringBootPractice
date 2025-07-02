package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ContactService;


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
}
