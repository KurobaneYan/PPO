package com.kurobane.yan.ppo;

import java.util.Date;

class Task {
    private int id;
    private boolean IsImportant;
    private boolean isDaily;
    private Date deadline;
    private String name;
    public Task() {

    }

    Task(String name) {
        setName(name);
    }

    public Task(String name, Date deadline) {
        setName(name);
        setDeadline(deadline);
    }

    public boolean isImportant() {
        return IsImportant;
    }

    public void setImportant(boolean important) {
        IsImportant = important;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public Date getDeadline() {
        return deadline;
    }

    private void setDeadline(Date deadline) {
        this.deadline = deadline;
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
