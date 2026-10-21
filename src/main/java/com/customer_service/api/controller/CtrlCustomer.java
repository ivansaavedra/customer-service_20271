package com.customer_service.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer_service.api.dto.DtoCustomerIn;
import com.customer_service.api.entity.Customer;
import com.customer_service.api.service.SvcCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer")
public class CtrlCustomer {

    @Autowired
    SvcCustomer svc;

    @GetMapping()
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(svc.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DtoCustomerIn in){
        svc.create(in);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id,
                                        @RequestBody DtoCustomerIn in){
        svc.update(in, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<Void> enable(@PathVariable("id") Integer id){
        svc.enable(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable("id") Integer id){
        svc.disable(id);
        return ResponseEntity.ok().build();
    }

}
