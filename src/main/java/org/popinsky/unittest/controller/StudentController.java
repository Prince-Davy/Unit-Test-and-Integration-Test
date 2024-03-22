package org.popinsky.unittest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.popinsky.unittest.model.Student;
import org.popinsky.unittest.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/all")
    public List<Student>getAllStudents(){
        return studentService.getAllStudent();
    }

    @GetMapping("/id")
    public Long getStudentById(Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student) throws BadRequestException {
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deletedStudentById(studentId);
    }






}
