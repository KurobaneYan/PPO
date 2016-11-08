package com.kurobane.yan.ppo;

class Task {
    private int id;
    private boolean isImportant;
    private boolean isDaily;
    private String name;
    Task() {

    }

    Task(String name) {
        setName(name);
    }

    boolean isImportant() {
        return isImportant;
    }

    void setImportant(boolean important) {
        isImportant = important;
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

    @Override
    public String toString() {
        return "id " + id + " name " + name + " D: " + isDaily + " I: " + isImportant;
    }
}
