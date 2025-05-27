package com.project.service;

import com.project.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {

    Student setStudent(Student student);	// create

    Optional<Student> getStudentById(Integer id); // read

    Optional<Student> getStudentByNrIndeksu(String nrIndeksu); // read

    void deleteStudentById(Integer id); // delete

    Student updateStudent(Integer id, Student updatedStudent); // update

    Page<Student> getAllStudents(Pageable pageable); // read

    Page<Student> findStudentsByNazwisko(String nazwisko, Pageable pageable); // read

    void assignProjektToStudent(Integer studentId, Integer projektId); // create / update
}