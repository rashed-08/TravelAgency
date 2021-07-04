package com.web.travelagency.model;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String location;

    public Location() {

    }

    public Location(Integer id, String location) {
        Id = id;
        this.location = location;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Id=" + Id +
                ", location='" + location + '\'' +
                '}';
    }
}
