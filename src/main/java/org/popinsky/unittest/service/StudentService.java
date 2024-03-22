package org.popinsky.unittest.service;

import org.apache.coyote.BadRequestException;
import org.popinsky.unittest.exception.StudentNotFoundException;
import org.popinsky.unittest.model.Student;
import org.popinsky.unittest.repositiory.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student>getAllStudent(){ return  studentRepository.findAll(); }

    public Long getStudentById(Long StudentId){return studentRepository.getReferenceById(StudentId).getId();}

    public void addStudent (Student student) throws BadRequestException {
        boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());

        if(existsEmail){
            throw new BadRequestException(
                    "Email" + student.getEmail() + " taken");
        }
        studentRepository.save(student);
    }

    public void deletedStudentById(Long studentId) throws StudentNotFoundException {
        boolean existsById = studentRepository.existsById(studentId);

        if(!existsById){
            throw new StudentNotFoundException(
                    "Student with id : "+ studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }

}
