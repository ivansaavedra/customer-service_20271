package com.customer_service.api.service;

import java.util.List;
import com.customer_service.api.entity.Region;
import com.customer_service.api.repository.RepoRegion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SvcRegionImp implements SvcRegion {

    @Autowired
    RepoRegion repo;

    @Override
    public List<Region> getRegions(){
        return repo.getRegions();
    }

    @Override
    public List<Region> getActiveRegions(){
        return repo.getActiveRegions();
    }

}
