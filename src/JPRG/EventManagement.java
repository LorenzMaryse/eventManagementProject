/*
 * Class : DIT/FT/1B/03
 * Admission No. : 2026792
 * Name : Maryse Lorenzo 
 * Project Name: Assignment2
 */
package JPRG;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EventManagement {

    //Used to store event objects
    private Event[] eventArr;

    //Used to keep track of number of events in array
    private int NumofEvents;

    //Stores events user has registered for
    private String registeredEvents = "";

    //Stores the total cost of the events user has registered for
    private double costOfEvents;

    private IO inout = new IO();

    //constructor
    public EventManagement() {

        NumofEvents = 3;

        File f = new File("events.dat");

        if (!f.exists()) {
            eventArr = inout.readData();
            inout.processData(eventArr);
        } else {
            eventArr = new Event[NumofEvents];

            ArrayList<Event> p = inout.readObj();

            eventArr = p.toArray(eventArr);
        }
    }

    //Displays all the events in eventArr
    public String displayEvents() {

        String msg = "S/N\tName\tOrganiser\tDate/Time\t\tFees($)\tAvailability\n";

        //Loop to add object properties into String variable 
        for (int i = 0; i < eventArr.length; i++) {
            msg += "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------";
            msg += "\n" + (i + 1) + "\t" + eventArr[i].getName() + "\t" + eventArr[i].getOrganiser() + "\t" + eventArr[i].getDateTime() + "\t" + eventArr[i].getFees() + "\t" + eventArr[i].getAvail() + "\n" + eventArr[i].getDetails() + "\n";
        }

        //return String
        return msg;
    }

    //Adds events to array 
    public void addEvents(String name, String organiser, String datetime, double fees, String type, int avail) throws Exception {
        boolean duplicate = false;
        String msg = "";

        //Loop for going through array to check for match
        for (int m = 0; m < eventArr.length; m++) {
            String checkName = eventArr[m].getName();

            if (checkName.equals(name)) {
                //confirms successful finding of name
                duplicate = true;
            }
        }

        if (duplicate == true) {
            //Error Message (duplicate)
            throw new Exception("Duplicate event! Names have to be unique.");
        }

        //Creation of temporary array
        Event[] newArr = new Event[eventArr.length + 1];

        //loop to add old array into new array
        for (int i = 0; i < eventArr.length; i++) {
            newArr[i] = eventArr[i];
        }

        //Updating number of events and new array with new object
        NumofEvents++;

        //Creation of diff types of Events
        if (type.equals("Online")) {
            newArr[NumofEvents - 1] = new Online(name, organiser, datetime, fees, avail);
        } else if (type.equals("F2F")) {
            newArr[NumofEvents - 1] = new FaceToFace(name, organiser, datetime, fees, avail);
        } else {
            newArr[NumofEvents - 1] = new Event(name, organiser, datetime, fees, avail);
        }

        //Updating old array with new array
        eventArr = newArr;

        //Save Array
        inout.processData(eventArr);
    }

    //deletes events from the array 
    public boolean DeleteEvents(String name) throws Exception {
        boolean isFound = false;

        //Loop to check through array and delete objects from array
        for (int i = 0; i < eventArr.length; i++) {
            //Gets name from objects
            String check = eventArr[i].getName();

            if (check.equals(name)) {
                //Creates new temporary array
                Event[] newArr = new Event[eventArr.length - 1];

                //adds new objects into new array before deleted object
                for (int j = 0; j < i; j++) {
                    newArr[j] = eventArr[j];
                }

                //adds new objects into new array after deleted object
                for (int k = i; k < newArr.length; k++) {
                    newArr[k] = eventArr[k + 1];
                }

                //Updates number of events
                NumofEvents--;

                //Updating old array with new array
                eventArr = newArr;

                inout.processData(eventArr);

                //Confirms that deletion was successful
                isFound = true;
            }
        }

        if (isFound == false) {
            throw new Exception("Event not found");
        }

        return isFound;

    }

    //Gets name for edit 
    public String checkName(String name) throws Exception {
        String category, editValue = null, msg = "";
        int inputNum = 0, index = 0;
        boolean isFound = false;

        //Loop for going through array to check for match
        for (int m = 0; m < eventArr.length; m++) {
            String checkName = eventArr[m].getName();

            if (checkName.equals(name)) {
                //confirms successful finding of name
                isFound = true;

                //transfer index into another variable
                msg += m;

                //breaks out of the loop
                break;
            }
        }

        if (isFound == false) {
            //Displays error message if event was not found
            throw new Exception("Cannot find the event \"" + name + "\"! Please enter again");
        }

        System.out.println("Index : " + msg);
        return msg;
    }

    //edits/updates events from the array 
    public void EditEvents(String inputName, String inputOrganiser, String inputDateTime, String inputFees, String indexTmp, int inputAvail) throws Exception {
        boolean duplicate = false;

        int index = Integer.parseInt(indexTmp);

        //Loop for going through array to check for match
        for (int m = 0; m < eventArr.length; m++) {
            String check = eventArr[m].getName();

            if (check.equals(inputName)) {
                //confirms successful finding of name
                duplicate = true;
            }
        }

        if (duplicate == true) {
            //thriws error
            throw new Exception("Duplicate event! Names have to be unique.");
        }

        if (!inputName.equals(" ")) {
            eventArr[index].setName(inputName);
        }

        if (!inputOrganiser.equals(" ")) {
            eventArr[index].setOrganiser(inputOrganiser);
        }

        if (!inputDateTime.equals(" ")) {
            eventArr[index].setDateTime(inputDateTime);
        }

        if (!inputFees.equals(" ")) {
            double editValueFees = Double.parseDouble(inputFees);
            eventArr[index].setFees(editValueFees);
        }

        if (inputAvail != 0) {
            eventArr[index].setAvail(inputAvail);
        }

        inout.processData(eventArr);
    }

    //displays event with inputted name
    public String displayByName(String name) throws Exception {
        boolean isFound = false;
        String msg;

        msg = "Name\tOrganiser\tDate/Time\t\tFees($)\tAvailability";

        //Loop checks through array and displays relevant information
        for (int m = 0; m < eventArr.length; m++) {
            String check = eventArr[m].getName();

            if (check.equals(name)) {
                //Creates and updates string message
                msg += "\n" + eventArr[m].getName() + "\t" + eventArr[m].getOrganiser() + "\t" + eventArr[m].getDateTime() + "\t" + eventArr[m].getFees() + "\t" + eventArr[m].getAvail();

                msg += "\n\n" + eventArr[m].getDetails();
                //Confirms successful display
                isFound = true;
            }
        }

        //Displays error message
        if (isFound == false) {
            throw new Exception("Cannot find the event \"" + name + "\"! Please enter again");
        }

        //Displays string message
        return msg;
    }

    //displays events by price 
    public String displayByFees(String feesStr) throws Exception {
        String msg = "";
        boolean isFound = false;
        double fees, check;

        //Converts string to double
        fees = Double.parseDouble(feesStr);

        //String message for display
        msg = "Name\tOrganiser\tDate/Time\t\tFees($)\tAvailability";

        //Loop for checking through array and updating string message
        for (int m = 0; m < eventArr.length; m++) {
            check = eventArr[m].getFees();

            //Updates message
            if (check == fees || check < fees) {
                msg += "\n" + eventArr[m].getName() + "\t" + eventArr[m].getOrganiser() + "\t" + eventArr[m].getDateTime() + "\t" + eventArr[m].getFees() + "\t" + eventArr[m].getAvail() + "\n";

                //Confirms successful update
                isFound = true;
            }
        }

        if (isFound == true) {
            //Displays string message
            return msg;
        } else {
            //Displays error message
            throw new Exception("There are no events that are of that price or cheaper.");
        }

    }

    //registers users for certain events
    public String Register(String register, int attendees) throws Exception {
        //Initialising variables
        String check, msg;
        double totalCost = 0;
        boolean isFound = false;
        int count = 0;

        //String message for display
        msg = "You have registered for the following event(s):";

        //Loop to update variables for display
        for (int m = 0; m < eventArr.length; m++) {

            check = eventArr[m].getName();

            if (check.equals(register)) {

                //Check if event is full
                if (eventArr[m].getAvail() == 0) {
                    throw new Exception("Event is full!");
                }

                //Adds to list of events user has registered for and total cost of these events
                registeredEvents += "\n* " + check + "     \n--**" + eventArr[m].getDetails() + "   \n--** You registered " + attendees + " people for this event.\n";
                costOfEvents += eventArr[m].getFees();

                //Confirms successful update
                isFound = true;

                //Subtract availability
                int newAvail = eventArr[m].getAvail() - attendees;
                eventArr[m].setAvail(newAvail);
                break;
            }
        }

        if (isFound == true) {
            //updates display message
            String costStr = "\n\nThe cost is " + costOfEvents + ".";
            msg += registeredEvents + costStr;
        } else {
            throw new Exception("Event not found");
        }

        SaveData();
        return msg;

    }

    //Saving current Events array into data file
    public void SaveData() {
        inout.processData(eventArr);
    }
}
