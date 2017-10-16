package com.kaluzny.qrok.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REWARDS")
@AttributeOverride(name = "id", column = @Column(name = "reward_id"))
public class Reward extends BaseEntity {

    private String title;
    private Integer year;

    @Column(name = "REWARD_TITLE", length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "REWARD_YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reward)) return false;
        if (!super.equals(o)) return false;

        Reward reward = (Reward) o;

        if (!getTitle().equals(reward.getTitle())) return false;
        return getYear().equals(reward.getYear());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getYear().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
