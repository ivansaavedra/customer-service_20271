package com.customer_service.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer_service.api.entity.Region;
import com.customer_service.api.service.SvcRegion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/region")
public class CtrlRegion {

    @Autowired 
    SvcRegion svc;
    
    @GetMapping()
    public List<Region> getRegions(){
	    return svc.getRegions();
    }

    @GetMapping("/active")
    public List<Region> getActiveRegions(){
        return svc.getActiveRegions();
    }
}
