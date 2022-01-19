package com.hb.todo.pojos;

import java.io.Serializable;

public class Todos implements Serializable {
    private int id;
    private String name;
    private String urgency;

    public Todos(String name, String urgency){
        this.name = name;
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urgency='" + urgency + '\'' +
                '}';
    }
}
