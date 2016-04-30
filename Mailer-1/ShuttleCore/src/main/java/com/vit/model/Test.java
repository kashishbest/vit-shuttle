package com.vit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by kashishsinghal on 13/03/16.
 */
@Entity
@Table(name = "test")
@XmlRootElement
public class Test {

    @Id
    @Column(name = "a")
    String a;

    @Column(name = "b")
    String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;

        Test test = (Test) o;

        if (a != null ? !a.equals(test.a) : test.a != null) return false;
        return b != null ? b.equals(test.b) : test.b == null;

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
