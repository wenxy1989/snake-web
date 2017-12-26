package com.google;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Student implements Comparable<Student> {
    private Integer id;
    private String name;
    private String degree;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper("Student").add("id", this.id).add("name", this.name).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.name);
    }

    public int compareTo(Student that) {
        return ComparisonChain.start()
                .compare(this.id, that.getId(), Ordering.natural().nullsLast())
                .compare(this.name, this.getName(), Ordering.natural().nullsLast())
                .result();
    }
}