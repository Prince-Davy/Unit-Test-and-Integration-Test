package org.popinsky.unittest.repositiory;

import org.junit.jupiter.api.Test;
import org.popinsky.unittest.Gender;
import org.popinsky.unittest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository  studentRepositoryTest;

    @Test
    void itShouldCheckIfStudentExistsEmail() {
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
}