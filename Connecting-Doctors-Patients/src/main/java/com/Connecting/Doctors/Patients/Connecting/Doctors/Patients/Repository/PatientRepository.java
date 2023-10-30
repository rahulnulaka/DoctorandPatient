package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
