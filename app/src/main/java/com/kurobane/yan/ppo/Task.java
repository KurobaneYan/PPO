package com.kurobane.yan.ppo;

import java.util.Date;

class Task {
    private int id;
    private boolean IsImportant;
    private boolean isDaily;
    private String name;
    Task() {

    }

    Task(String name) {
        setName(name);
    }

    boolean isImportant() {
        return IsImportant;
    }

    void setImportant(boolean important) {
        IsImportant = important;
    }

    boolean isDaily() {
        return isDaily;
    }

    void setDaily(boolean daily) {
        isDaily = daily;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
