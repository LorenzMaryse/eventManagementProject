/*
 * Class : DIT/FT/1B/03
 * Admission No. : 2026792
 * Name : Maryse Lorenzo 
 * Project Name: Assignment2
 */
package JPRG;

public class Online extends Event {

    public Online() {

    }

    public Online(String name, String organiser, String datetime, double fees, int availability) {
        super(name, organiser, datetime, fees, availability);
    }

    public String getDetails() {
        String details = "This event is online.\nThere is a " + getAvail() + " person limit to this event.";

        return details;
    }

}
