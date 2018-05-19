package com.catalog.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private static List<Student> students = new ArrayList<>();
    private static int idNo = 1;

    static {
        students.add(new Student(idNo++, "Ion", "Ion", 10));
        students.add(new Student(idNo++, "Vasile", "Popescu", 5));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> getStudent(@RequestParam final int id) {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/student/add")
    public ResponseEntity<Student> addStudent(@RequestParam final String firstName, @RequestParam final String lastName, @RequestParam final int grade) {
        Student student = new Student(idNo++, firstName, lastName, grade);
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/student/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam int id) {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (student != null) {
            students.remove(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
