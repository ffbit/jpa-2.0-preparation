package com.ffbit.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking_places")
@Access(AccessType.PROPERTY)
public class ParkingPlace implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer lot;
    private String location;

    protected ParkingPlace() {
        super();
    }

    public ParkingPlace(Integer lot, String location) {
        this();
        this.lot = lot;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    @Column(nullable = false, length = 40)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
