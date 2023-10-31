package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Exceptions.DoctorNotFound;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public String addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "Doctor has been saved successfully";
    }

    public String deleteDoctor(Integer doctorId)throws Exception {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFound("Doctor is not available");
        }
        Doctor doctor=optionalDoctor.get();

        doctorRepository.deleteById(doctorId);
        return "Successfully removed doctor from DataBase";
    }
}
