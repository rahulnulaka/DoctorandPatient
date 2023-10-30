package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findAllDoctorByCity(City city);
}
