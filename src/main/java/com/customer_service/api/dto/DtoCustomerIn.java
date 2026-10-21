package com.customer_service.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoCustomerIn {

    @JsonProperty("name")
    @Pattern(regexp="[a-zA-ZÁÉÍÓÚáéíóúñÑ\s]*", message="El name contiene caracteres inválidos")
    @NotNull(message="El nombre es obligatorio")
    private String name;

    @JsonProperty("surname")
    @Pattern(regexp="[a-zA-ZÁÉÍÓÚáéíóúñÑ\s]*", message="El surname contiene caracteres inválidos")
    @NotNull(message="El apellido es obligatorio")
    private String surname;

    @JsonProperty("rfc")
    @Pattern(regexp = "^(?i)[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$", message = "El rfc tiene un formato inválido")
    @NotNull(message="El RFC es obligatorio")
    private String rfc;

    @JsonProperty("mail")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="El mail tiene un formato inválido")
    @NotNull(message="El correo es obligatorio")
    private String mail;

    @JsonProperty("phone_number")
    @NotNull(message="El telefono es obligatorio")
    private String phone_number;

    @JsonProperty("address")
    // @NotNull(message="La direccion es obligatoria")
    private String address;

    @JsonProperty("user_id")
    @NotNull(message="El usuario es obligatorio")
    private Integer user_id;

    @JsonProperty("region_id")
    @NotNull(message="La region es obligatoria")
    private Integer region_id;

    public DtoCustomerIn(){

    }

    public DtoCustomerIn(String name, String surname, String rfc, String mail, String phone_number, String address,
            Integer user_id, Integer region_id){
        this.name = name;
        this.surname = surname;
        this.rfc = rfc;
        this.mail = mail;
        this.phone_number = phone_number;
        this.address = address;
        this.user_id = user_id;
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

}
