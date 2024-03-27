package org.popinsky.unittest.repositiory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popinsky.unittest.Gender;
import org.popinsky.unittest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepositoryTest;

    @AfterEach
    void tearDown() {
        studentRepositoryTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.MALE);
        studentRepositoryTest.save(student);
        //when
        boolean existsEmail = studentRepositoryTest.selectExistsEmail(email);
        //then
        assertThat(existsEmail).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        //given
        String email = "jamila@gmail.com";
        //when
        boolean existsEmail = studentRepositoryTest.selectExistsEmail(email);
        //then
        assertThat(existsEmail).isFalse();
    }
}