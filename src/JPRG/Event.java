/*
 * Class : DIT/FT/1B/03
 * Admission No. : 2026792
 * Name : Maryse Lorenzo 
 * Project Name: Assignment2
 */
package JPRG;

import java.io.*;

public class Event implements Serializable {

    //Declare variables
    private String name, organiser, datetime;
    private double fees;
    private int availability;

    //default constructor
    public Event() {
    }

    //constructor
    public Event(String name, String organiser, String datetime, double fees, int availability) {
        this.name = name;
        this.organiser = organiser;
        this.datetime = datetime;
        this.fees = fees;
        this.availability = availability;
    }

    //get method(name)
    public String getName() {
        return name;
    }

    //set method(name)
    public void setName(String newName) {
        name = newName;
    }

    //get method(organiser)
    public String getOrganiser() {
        return organiser;
    }

    //set method(organiser)
    public void setOrganiser(String newOrganiser) {
        organiser = newOrganiser;
    }

    //get method(datetime)
    public String getDateTime() {
        return datetime;
    }

    //set method(datetime)
    public void setDateTime(String newdatetime) {
        datetime = newdatetime;
    }

    //get method(fees)
    public double getFees() {
        return fees;
    }

    //set method(fees)
    public void setFees(double newfees) {
        fees = newfees;
    }
    
    //get method(avalibility)
    public int getAvail() {
        return availability;
    }
    
    //set method(availibility)
    public void setAvail(int avail) {
        availability = avail;
    }
    
    //get method(details) 
    public String getDetails() {
        String details = "This is an event. Availbility: " + getAvail();
        return details;
    }
}
