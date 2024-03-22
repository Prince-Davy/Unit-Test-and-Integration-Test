package org.popinsky.unittest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.popinsky.unittest.Gender;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@ToString
@EqualsAndHashCode
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "student_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long Id;

    @NotBlank
    @Column(nullable = false)
    private  String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private  String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  Gender gender;

    public Student(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
