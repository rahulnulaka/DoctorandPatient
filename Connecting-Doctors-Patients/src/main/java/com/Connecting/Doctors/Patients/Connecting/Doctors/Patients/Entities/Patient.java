package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer patientId;

    @Size(min=3,message = "Name should be atleast of 3 characters in size")
    String patientName;

    @Size(max=20,message = "City name should be  maximum of 20 characters in length")
    String city;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "Invalid email address")
    String email;

    @Pattern(regexp="([0-9]{10})",message = "Phone number should contain only digits and it should be 10 characters in length")
    String phoneNo;

    @Enumerated(value = EnumType.STRING)
    Symptom symptom;

}
