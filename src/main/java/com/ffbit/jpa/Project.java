package com.ffbit.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * Represents a project which an employee working on
 *
 * @author Dmytro Chyzhykov
 */
@Entity
@Table(name = "projects")
@Access(AccessType.PROPERTY)
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Set<Employee> employees;

    protected Project() {
        employees = new HashSet<Employee>();
    }

    public Project(String name) {
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

    @ManyToMany(mappedBy = "projects")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
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
        Project other = (Project) obj;

        return Objects.equal(name, other.name);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass()).add("id", id)
                .add("name", "name").toString();
    }

}
