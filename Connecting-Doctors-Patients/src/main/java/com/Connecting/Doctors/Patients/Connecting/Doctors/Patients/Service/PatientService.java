package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Patient;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Enums.City;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Exceptions.DoctorUnavailableAtTheLocation;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Exceptions.NoDoctorAvailableForTheSymptomAtPatientsLocation;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Exceptions.PatientNotFound;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Exceptions.UnknownSymptomException;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository.DoctorRepository;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Repository.PatientRepository;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.ResponseObjects.DoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public String addPatient(Patient patient)throws Exception{
            patientRepository.save(patient);
            return "Patient saved successfully to the DataBase";
    }

    public List<DoctorDTO> getDoctors(Integer patientId) throws Exception{
        Optional<Patient> optionalPatient=patientRepository.findById(patientId);
        if(optionalPatient.isEmpty()){
            throw new PatientNotFound("Patient ID is Invalid Enter Valid PatientId");
        }
        Patient patient=optionalPatient.get();
        List<Doctor> doctorsWithPatientLocation=doctorRepository.findAllDoctorByCity(City.valueOf(patient.getCity()));
        if(doctorsWithPatientLocation.isEmpty()){
            throw new DoctorUnavailableAtTheLocation("We are still waiting to expand to your location");
        }
        String requiredSpeciality=null;
        String patientSymptom= String.valueOf(patient.getSymptom());
        requiredSpeciality = switch (patientSymptom) {
            case "Arthritis", "BackPain", "TissueInjuries" -> "Orthopedic";
            case "Dysmenorrhea" -> "Gynecology";
            case "SkinInfection", "SkinBurn" -> "Dermatology";
            case "EarPain" -> "ENTSpecialist";
            default -> throw new UnknownSymptomException("Unknow symptom identified");
        };
        List<DoctorDTO> suggestedDoctorForPatientSymptom=new ArrayList<>();
        for(Doctor doctor:doctorsWithPatientLocation){
            String doctorSpeciality= String.valueOf(doctor.getSpeciality());
            if(doctorSpeciality.equals(requiredSpeciality)){
                DoctorDTO doctorDTO=new DoctorDTO();
                doctorDTO.setDoctorName(doctor.getDoctorName());
                doctorDTO.setEmail(doctor.getEmail());
                doctorDTO.setPhoneNo(doctor.getPhoneNo());
                doctorDTO.setSpeciality(String.valueOf(doctor.getSpeciality()));
                suggestedDoctorForPatientSymptom.add(doctorDTO);
            }
        }
        if(suggestedDoctorForPatientSymptom.isEmpty()){
            throw new NoDoctorAvailableForTheSymptomAtPatientsLocation("There isn’t any doctor present at your location for your symptom");
        }
        return suggestedDoctorForPatientSymptom;
    }
}
