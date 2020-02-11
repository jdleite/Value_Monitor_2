package br.com.value_monitor_2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ValueGroup implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String annotation;
    private double value;
    private String date;

    @Ignore
    public ValueGroup(String annotation, double value, String date) {
        this.annotation = annotation;
        this.value = value;
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
