package org.popinsky.unittest.repositiory;

import org.popinsky.unittest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(" " +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Student s " +
            "WHERE s.email = :email"
    )
    Boolean selectExistsEmail(@Param("email") String email);

}
