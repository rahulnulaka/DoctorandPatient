package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorName;

    private String email;

    private String phoneNo;

    private String speciality;
}
