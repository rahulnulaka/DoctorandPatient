package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.ResponseObjects;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorName;

    private String email;

    private String phoneNo;

    private String speciality;
}
