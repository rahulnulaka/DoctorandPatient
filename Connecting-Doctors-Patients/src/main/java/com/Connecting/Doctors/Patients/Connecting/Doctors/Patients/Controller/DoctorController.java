package com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Controller;

import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Entities.Doctor;
import com.Connecting.Doctors.Patients.Connecting.Doctors.Patients.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@Valid @RequestBody Doctor doctor, BindingResult result)throws Exception{
        if(result.hasErrors()){
            String doctorNameErrorMessage= (result.getFieldError("doctorName")==null)?" ":result.getFieldError("doctorName").getDefaultMessage();
            String doctorEmailErrorMessage= (result.getFieldError("email")==null)?" ":result.getFieldError("email").getDefaultMessage();
            String doctorPhoneNoErrorMessage= (result.getFieldError("phoneNo")==null)?" ":result.getFieldError("phoneNo").getDefaultMessage();
            String errorMessage=doctorNameErrorMessage+"\n"+doctorEmailErrorMessage+"\n"+doctorPhoneNoErrorMessage;
            return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        }

        String responseMessage=doctorService.addDoctor(doctor);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
