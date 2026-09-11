package com.customer_service.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer_service.api.entity.Region;

@Repository 
public interface RepoRegion extends JpaRepository<Region, Integer>{

    @Query(value ="SELECT * FROM region ORDER BY region;", nativeQuery = true)
    List<Region> getRegions();

    @Query(value ="SELECT * FROM region WHERE status = 1 ORDER BY region;", nativeQuery = true)
    List<Region> getActiveRegions();
}

