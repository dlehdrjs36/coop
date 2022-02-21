package com.projectteam.coop.sample.domain;

public class SampleDTO {
    private int sampleSeq;
    private String id;
    private String name;
    private String age;

    public int getSampleSeq() {
        return sampleSeq;
    }

    public void setSampleSeq(int sampleSeq) {
        this.sampleSeq = sampleSeq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
