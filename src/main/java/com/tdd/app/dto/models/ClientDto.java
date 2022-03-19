package com.tdd.app.dto.models;


import com.tdd.app.enumeration.Sex;

public class ClientDto {

    private long id;

    private String email;

    private int phone;

    private String name;

    private int age;

    private Sex sex;

    private Boolean isActive;

    public ClientDto() {
    }

    public ClientDto(long id, String email, int phone, String name, int age, Sex sex, Boolean isActive) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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
