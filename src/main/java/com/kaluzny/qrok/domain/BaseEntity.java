package com.kaluzny.qrok.domain;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity {

    private int id;

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        return getId() == that.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
