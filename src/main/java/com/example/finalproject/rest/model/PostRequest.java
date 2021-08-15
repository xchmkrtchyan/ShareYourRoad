package com.example.finalproject.rest.model;

import com.example.finalproject.persistence.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "posts")
public class PostRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String startPoint;

    private String endPoint;

    private String startPointDate;

    private String endPointDate;

    private Long passengers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getStartPointDate() {
        return startPointDate;
    }

    public void setStartPointDate(String startPointDate) {
        this.startPointDate = startPointDate;
    }

    public String getEndPointDate() {
        return endPointDate;
    }

    public void setEndPointDate(String endPointDate) {
        this.endPointDate = endPointDate;
    }

    public Long getPassengers() {
        return passengers;
    }

    public void setPassengers(Long passengers) {
        this.passengers = passengers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

