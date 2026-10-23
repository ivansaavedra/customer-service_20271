package com.customer_service.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer_service.api.dto.DtoCustomerImageIn;
import com.customer_service.api.service.SvcCustomerImage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {
    
    @Autowired
    SvcCustomerImage svc;

    @PatchMapping
    public ResponseEntity<Void> upload(@Valid @RequestBody DtoCustomerImageIn in){
   	    svc.upload(in);
		return ResponseEntity.ok().build();
    }


}
