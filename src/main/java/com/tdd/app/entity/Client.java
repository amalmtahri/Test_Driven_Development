package com.tdd.app.entity;


import com.tdd.app.enumeration.Sex;
import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(name = "phone", nullable = false, unique=true, length = 10)
    private int phone;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "isActive")
    private Boolean isActive = true;


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
}
