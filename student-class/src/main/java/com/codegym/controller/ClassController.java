package com.codegym.controller;

import com.codegym.model.Classes;
import com.codegym.service.ClassService;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Intercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.ManyToOne;

@Controller
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/classes")
    public ModelAndView showListClass() {
        Iterable<Classes> classes = classService.findAll();
        ModelAndView modelAndView = new ModelAndView("/classes/list");
        modelAndView.addObject("classes",classes);
        return modelAndView;
    }
    @GetMapping("/create-class")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("/classes/create");
        modelAndView.addObject("classes",new Classes());
        return modelAndView;
    }
    @PostMapping("/create-class")
    public ModelAndView createClass(@ModelAttribute("classes") Classes classes) {
    classService.save(classes);
    ModelAndView modelAndView = new ModelAndView("/classes/create");
    modelAndView.addObject("classes",new Classes());
    modelAndView.addObject("message","New class created successfully!");
    return modelAndView;
    }
    @GetMapping("/edit-class/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Classes classes = classService.findById(id);
        if (classes != null) {
            ModelAndView modelAndView = new ModelAndView("/classes/edit");
            modelAndView.addObject("classes",classes);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-class")
    public ModelAndView updateClass(@ModelAttribute("classes") Classes classes) {
    classService.save(classes);

    ModelAndView modelAndView = new ModelAndView("/classes/edit");
    modelAndView.addObject("classes",classes);
    modelAndView.addObject("message","Class updated successfully!");
    return modelAndView;
    }
    @GetMapping("/delete-class/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
    Classes classes = classService.findById(id);
    if (classes != null) {
        ModelAndView modelAndView = new ModelAndView("/classes/delete");
        modelAndView.addObject("classes",classes);
        return modelAndView;
    }else {
        ModelAndView modelAndView = new ModelAndView("/error.404");
        return modelAndView;
    }
    }
    @PostMapping("/delete-class")
    public String deleteClass(@ModelAttribute("classes") Classes classes) {
    classService.remove(classes.getId());
    return "redirect:classes";
    }


}
