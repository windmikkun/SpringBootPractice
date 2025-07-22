package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("adminForm", new AdminForm());
        return "admin/signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute @Valid AdminForm adminForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/signup";
        }
        adminService.register(adminForm);
        return "redirect:/admin/signin";
    }

    @PostMapping("/signin")
    public String processSignin(@RequestParam String email, @RequestParam String password, Model model){
        //認証処理は最終課題４の内容なので一旦保留しました。
        return "admin/signin";
    }
    @GetMapping("/signin")
    public String showSigninForm(){
        return "admin/signin";
    }
    
}
