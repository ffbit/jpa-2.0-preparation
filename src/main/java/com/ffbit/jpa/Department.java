package com.ffbit.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "departmens")
@Access(AccessType.PROPERTY)
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Set<Employee> employees;

    protected Department() {
        employees = new HashSet<Employee>();
    }

    public Department(String name) {
        this();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public boolean addEmployee(Employee employee) {
        employee.setDepartment(this);
        return getEmployees().add(employee);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;

        return Objects.equal(name, other.name);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass()).add("id", id)
                .add("name", name).toString();
    }

}
