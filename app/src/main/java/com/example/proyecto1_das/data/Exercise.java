package com.example.proyecto1_das.data;

public class Exercise {
    private String id;
    private String name;
    private String des;
    private Integer numSeries;
    private Integer numReps;
    private Double kg;

    public Exercise(String id, String name, String des, Integer numSeries, Integer numReps, Double numKgs) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.numSeries = numSeries;
        this.numReps = numReps;
        this.kg = numKgs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public Integer getNumSeries() {
        return numSeries;
    }

    public Integer getNumReps() {
        return numReps;
    }

    public Double getNumKgs() {
        return kg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setNumSeries(Integer numSeries) {
        this.numSeries = numSeries;
    }

    public void setNumReps(Integer numReps) {
        this.numReps = numReps;
    }

    public void setNumKgs(Double kgs) {
        this.kg = kgs;
    }
}
