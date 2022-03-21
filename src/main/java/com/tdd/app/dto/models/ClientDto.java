package com.tdd.app.dto.models;


import com.tdd.app.enumeration.Sex;

import javax.validation.constraints.Pattern;

public class ClientDto {

    private long id;

    private String email;

    @Pattern(regexp = "(\\+212|1)(\\d){9}")
    private String phone;

    private String name;

    private int age;

    private Sex sex;

    private Boolean isActive;

    public ClientDto() {
    }

    public ClientDto(long id, String email, String phone, String name, int age, Sex sex, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
