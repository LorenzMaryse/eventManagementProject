/*
 * Class : DIT/FT/1B/03
 * Admission No. : 2026792
 * Name : Maryse Lorenzo 
 * Project Name: Assignment2
 */
package JPRG;

public class FaceToFace extends Event {

    public FaceToFace() {

    }

    public FaceToFace(String name, String organiser, String datetime, double fees, int availability) {
        super(name, organiser, datetime, fees, availability);
    }

    public String getDetails() {
        String details = "This event is a face to face event. \nThere is a " + getAvail() + " person limit to this event. \nRemember to bring masks and social distance.";

        return details;
    }

}
