package com.codegym.formatter;

import com.codegym.model.Classes;
import com.codegym.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class ClassFormatter implements Formatter<Classes> {
    @Autowired
    private ClassService classService;

    public ClassFormatter(ClassService bean) {
    }


    @Override
    public Classes parse(String text, Locale locale) throws ParseException {
        return classService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Classes object, Locale locale) {
        return "[" + object.getId() + object.getName() + "]";
    }
}
