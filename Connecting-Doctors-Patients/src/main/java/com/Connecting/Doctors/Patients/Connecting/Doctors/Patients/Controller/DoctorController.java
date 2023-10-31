package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Controller;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@Valid @RequestBody Doctor doctor, BindingResult result)throws Exception{
        if(result.hasErrors()){

            String errorMessage="";

            if(result.getFieldError("doctorName")!=null)
                errorMessage.concat(result.getFieldError("doctorName").getDefaultMessage() + "\n");

            if(result.getFieldError("email")!=null)
                errorMessage.concat(result.getFieldError("email").getDefaultMessage() + "\n");

            if(result.getFieldError("phoneNo")!=null)
                errorMessage.concat(result.getFieldError("phoneNo").getDefaultMessage() + "\n");

            return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        }

        String responseMessage=doctorService.addDoctor(doctor);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @DeleteMapping("/deleteDoctor")
    public ResponseEntity<String> deleteDoctor(@RequestParam("doctorId") Integer doctorId){
        try{
            String responseMessage=doctorService.deleteDoctor(doctorId);
            return new ResponseEntity<>(responseMessage,HttpStatus.OK);
        }
        catch(Exception error){
           return new ResponseEntity<>(error.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
