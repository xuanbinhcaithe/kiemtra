package com.codegym.repository;

import com.codegym.model.Classes;
import com.codegym.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    Iterable<Student> findAllByProvince(Classes classes);
}
