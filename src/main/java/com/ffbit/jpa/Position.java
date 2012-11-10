package com.ffbit.jpa;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Position of Employee
 *
 * @author Dmytro Chyzhykov
 */
@Entity
@Table(name = "positions")
@Access(AccessType.PROPERTY)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;

    public Position() {
        super();
    }

    public Position(String title) {
        this();
        this.title = title;
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass())
                .add("id", id)
                .add("title", title)
                .toString();
    }

}
