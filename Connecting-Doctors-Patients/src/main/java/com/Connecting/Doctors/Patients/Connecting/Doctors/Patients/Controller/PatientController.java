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
            String errorMessage="";

            if(result.getFieldError("patientName")!=null)
                errorMessage.concat(result.getFieldError("patientName").getDefaultMessage() + "\n");

            if(result.getFieldError("city")!=null)
                errorMessage.concat(result.getFieldError("city").getDefaultMessage() + "\n");

            if(result.getFieldError("email")!=null)
                errorMessage.concat(result.getFieldError("email").getDefaultMessage() + "\n");

            if(result.getFieldError("phoneNo")!=null)
                errorMessage.concat(result.getFieldError("phoneNo").getDefaultMessage() + "\n");

            return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        }
            String responseMessage= patientService.addPatient(patient);
            return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }
    @DeleteMapping("/deletePatient")
    public ResponseEntity<String> deletePatient(@RequestParam("patientId") Integer patientId){
        try{
            String responseMessage=patientService.deletePatient(patientId);
            return new ResponseEntity<>(responseMessage,HttpStatus.OK);
        }
        catch(Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.BAD_REQUEST);
        }
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
