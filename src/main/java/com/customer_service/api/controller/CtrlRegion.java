package com.customer_service.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer_service.api.entity.Region;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/region")
public class CtrlRegion {
    
    @GetMapping()
    public List<Region> getRegions(){
        
        List<Region> regions = new ArrayList<Region>();
	    regions.add(new Region(1,"Norte","N",1));
	    regions.add(new Region(2,"Sur","S",1));
	    regions.add(new Region(3,"Este","E",0));
	
	    return regions;

    }

    @GetMapping("/active")
    public List<Region> getActiveRegions(){
        
        List<Region> regions = new ArrayList<Region>();
	    regions.add(new Region(1,"Norte","N",1));
	    regions.add(new Region(2,"Sur","S",1));
	
	    return regions;

    }
}
