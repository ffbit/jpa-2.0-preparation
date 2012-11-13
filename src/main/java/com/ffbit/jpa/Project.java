package com.ffbit.jpa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.common.base.Objects;

/**
 * Represents a project which an employee is working on
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

    @Access(AccessType.FIELD)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Calendar createdAt;

    private Set<Employee> participants;

    protected Project() {
        // TODO: Find a way to get timestamp from DB, not from Java code
        createdAt = Calendar.getInstance();
        participants = new HashSet<Employee>();
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

    @Transient
    public Date getCreatedAt() {
        return createdAt.getTime();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt.setTime(createdAt);
    }

    @ManyToMany(mappedBy = "projects")
    public Set<Employee> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Employee> participants) {
        this.participants = participants;
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
        return Objects.toStringHelper(getClass())
                .add("id", id)
                .add("name", name)
                .toString();
    }

}
