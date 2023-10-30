package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Controller;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Patient;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.ResponseObjects.DoctorDTO;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@Valid @RequestBody Patient patient, BindingResult result) throws Exception {
        if(result.hasErrors()){
            String patientNameErrorMessage= (result.getFieldError("patientName")==null)?" ":result.getFieldError("patientName").getDefaultMessage();
            String patientLocationErrorMessage= (result.getFieldError("city")==null)?" ":result.getFieldError("city").getDefaultMessage();
            String patientEmailErrorMessage= (result.getFieldError("email")==null)?" ":result.getFieldError("email").getDefaultMessage();
            String patientPhoneNoErrorMessage= (result.getFieldError("phoneNo")==null)?" ":result.getFieldError("phoneNo").getDefaultMessage();
            String errorMessage=patientNameErrorMessage+"\n"+patientLocationErrorMessage+"\n"+patientEmailErrorMessage+"\n"+ patientPhoneNoErrorMessage;
            return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        }
            String responseMessage= patientService.addPatient(patient);
            return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }
    @GetMapping("/getDoctors")
    public ResponseEntity<?> getDoctors(@RequestParam("patientId")Integer patientId){
        try{
            List<DoctorDTO> suggestedDoctors=patientService.getDoctors(patientId);
            return new ResponseEntity<>(suggestedDoctors,HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
