package com.customer_service.api.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DtoRegionIn {
    
    @JsonProperty("region")
    @NotNull(message="La región es obligatoria")
    private String region;

    @JsonProperty("region")
    @NotNull(message="La región es obligatoria")
    private String tag;

    public DtoRegionIn(){

    }

    public DtoRegionIn(String region, String tag){
        this.region = region;
        this.tag = tag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    
}
