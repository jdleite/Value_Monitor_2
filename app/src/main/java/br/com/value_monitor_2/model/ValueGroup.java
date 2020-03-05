package br.com.value_monitor_2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ValueGroup implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String annotation;
    private String value;
    private String date;

    @Ignore
    public ValueGroup(String annotation, String value, String date) {
        this.annotation = annotation;
        this.value = value;
        this.date = date;
    }

    public ValueGroup(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean validId() {
        return id > 0;
    }
}
