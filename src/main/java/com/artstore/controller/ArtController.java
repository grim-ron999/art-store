package com.artstore.controller;

import com.artstore.model.Art;
import com.artstore.repository.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ArtController {

    @Autowired
    private ArtRepository artRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("arts", artRepository.findAll());
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Art> art = artRepository.findById(id);
        art.ifPresent(a -> model.addAttribute("art", a));
        return "detail";
    }

    @GetMapping("/about")
    public String about() { return "about"; }

    @GetMapping("/contact")
    public String contact() { return "contact"; }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("arts", artRepository.findAll());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String addForm(Model model) {
        model.addAttribute("art", new Art());
        return "admin-form";
    }

    @PostMapping("/admin/save")
    public String save(@ModelAttribute Art art) {
        artRepository.save(art);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        artRepository.findById(id).ifPresent(a -> model.addAttribute("art", a));
        return "admin-form";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id) {
        artRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String login() { return "login"; }
}
