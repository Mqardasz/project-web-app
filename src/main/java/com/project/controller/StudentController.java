package com.project.controller;

import java.net.URI;
import java.util.Optional;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.project.model.Student;
import com.project.service.StudentService;



public class StudentController {

	private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    	logger.info("zainicjowano klase");
    }
    
    @GetMapping("/studenci/{studentId}")
    ResponseEntity<Student> getStudentbyId(@PathVariable("studentId") Integer studentId ) {
    	return ResponseEntity.of(studentService.getStudentById(studentId));
    }
    
    @PostMapping(path = "/studenci")
    ResponseEntity<Void> createStudent(@RequestBody Student student) {
    	Student createStudent = studentService.setStudent(student);
    	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{studentId}").buildAndExpand(createStudent.getStudentId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/studenci/{studentId}")
    public ResponseEntity<Void> updateStudent(@Valid @RequestBody Student student,
    											@PathVariable("studentId") Integer studentId) {
    	return studentService.getStudentById(studentId)
    			.map(p -> {
    				studentService.setStudent(student);
    				return new ResponseEntity<Void>(HttpStatus.OK);
    			})
    			.orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) {
        Optional<Student> studentOptional = studentService.getStudentById(studentId);
        if (studentOptional.isPresent()) {
            studentService.deleteStudentById(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/studenci/all")
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping(params = "nazwisko")
    public Page<Student> findStudentsByNazwisko(@RequestParam String nazwisko, Pageable pageable) {
        return studentService.findStudentsByNazwisko(nazwisko, pageable);
    }

    @PostMapping("/{studentId}/projects/{projectId}")
    public ResponseEntity<Void> assignProjectToStudent(@PathVariable Integer studentId, @PathVariable Integer projectId) {
        try {
            studentService.assignProjektToStudent(studentId, projectId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
