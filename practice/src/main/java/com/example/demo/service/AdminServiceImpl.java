package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin register(AdminForm form) {
        Admin admin = new Admin();
        admin.setLastName(form.getLastName());
        admin.setFirstName(form.getFirstName());
        admin.setEmail(form.getEmail());
        admin.setPassword(passwordEncoder.encode(form.getPassword()));
        return adminRepository.save(admin);
    }
}
