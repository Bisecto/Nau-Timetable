package com.example.nautimetable;

public class Timetab {
    String Day;
    String Time;
    String Course;
    String Hall;

    public Timetab(){

    }
    public  Timetab ( String Day, String Time, String Course, String Hall){
        this.Day= Day;
        this.Time=Time;
        this.Course=Course;
        this.Hall= Hall;
    }

    public String getDay() {
        return Day;
    }

    public String getTime() {
        return Time;
    }

    public String getCourse() {
        return Course;
    }

    public String getHall() {
        return Hall;
    }
}
