package com.vit.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kashishsinghal on 10/04/16.
 */
@Entity
@Table(name = "somya")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Somya {

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Somya)) return false;

        Somya somya = (Somya) o;

        if (age != somya.age) return false;
        return name != null ? name.equals(somya.name) : somya.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Somya{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
