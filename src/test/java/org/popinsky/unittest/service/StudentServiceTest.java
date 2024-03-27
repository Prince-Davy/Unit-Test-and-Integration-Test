package org.popinsky.unittest.service;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.popinsky.unittest.Gender;
import org.popinsky.unittest.exception.StudentNotFoundException;
import org.popinsky.unittest.model.Student;
import org.popinsky.unittest.repositiory.StudentRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private final Student mockStudent = new Student(
			"Jamila",
            "jamila@gmail.com",
            Gender.MALE);

    private final Student mockStudentWithId = new Student(
			123456L,
		    "Jamila",
            "jamila@gmail.com",
            Gender.MALE);
    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudent() {
        //when
        studentService.getAllStudent();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canGetStudentById() {
        //given
        Long studentId = 123456L;

        mockStudent.setId(studentId); // Assurez-vous que l'objet Student a un ID pour éviter NullPointerException

        // Simuler le comportement de studentRepository pour retourner mockStudent lorsque getReferenceById est appelé avec studentId
        when(studentRepository.getReferenceById(studentId)).thenReturn(mockStudent);

        //when
        studentService.getStudentById(studentId);

        //then
        verify(studentRepository).getReferenceById(studentId);
    }

    @Test
    void canAddStudent() throws BadRequestException {
        //when
        studentService.addStudent(mockStudent);

        //then
        ArgumentCaptor<Student>studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(mockStudent);
    }

    @Test
    void canDeletedStudentById_ThrowsException_WhenStudentNotFound() {
        //given
        Long studentId = 123456L;

        //then and when
        assertThatThrownBy(() -> studentService.deletedStudentById(studentId))
                .isInstanceOf(StudentNotFoundException.class).hasMessageContaining("Student with id : "+ studentId + " does not exists");
    }

    @Test
    void canDeletedStudentById() {
        //given
        Long studentWithId = mockStudentWithId.getId();

        // Simuler le comportement de existsById pour retourner true lorsque l'ID spécifié est passé
        when(studentRepository.existsById(studentWithId)).thenReturn(true);

        //when
        studentService.deletedStudentById(studentWithId);

        //then
        verify(studentRepository).deleteById(studentWithId);

    }
}