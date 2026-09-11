package com.customer_service.api.service;

import java.util.List;

import com.customer_service.api.entity.Region;

public interface SvcRegion {
    
    public List<Region> getRegions();
    public List<Region> getActiveRegions();

}
