package com.ffbit.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Access(AccessType.PROPERTY)
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private BigDecimal salary;
    private Department department;
    private ParkingPlace parkingPlace;

    protected Employee() {
        super();
    }

    public Employee(String name, BigDecimal salary, Department department) {
        this();
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToOne(optional = true)
    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

}
