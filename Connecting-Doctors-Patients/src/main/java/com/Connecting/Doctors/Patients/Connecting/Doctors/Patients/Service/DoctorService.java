package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public String addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "Doctor has been saved successfully";
    }
}
