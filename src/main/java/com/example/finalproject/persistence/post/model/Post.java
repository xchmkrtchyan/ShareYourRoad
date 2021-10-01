package com.example.finalproject.persistence.post.model;


import com.example.finalproject.persistence.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "posts",schema = "final_project")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String startPoint;

    private String endPoint;

    private String startPointDate;

    private String endPointDate;

    private Long passengers;


    public Post() {
    }

    public Post(String username, String startPoint, String endPoint, String startPointDate, String endPointDate, Long passengers) {
        this.username = username;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startPointDate = startPointDate;
        this.endPointDate = endPointDate;
        this.passengers = passengers;
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
}
