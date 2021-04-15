/*
 * Class : DIT/FT/1B/03
 * Admission No. : 2026792
 * Name : Maryse Lorenzo 
 * Project Name: Assignment2
 */
package JPRG;

import java.io.*;
import java.util.ArrayList;

public class IO {

    public IO() {

    }

    //Read from event.txt
    public Event[] readData() {
        BufferedReader br;
        String s;
        Event[] events = new Event[3];
        try {
            Event Tmp;
            int count = 0;

            br = new BufferedReader(new FileReader("events.txt"));

            while ((s = br.readLine()) != null) {
                String[] props = s.split(";");

                double fees = Double.parseDouble(props[3]);
                int avail = Integer.parseInt(props[5]);

                if (props[4].equals("Online Event")) {
                    Tmp = new Online(props[0], props[1], props[2], fees, avail);
                } else if (props[4].equals("Face-To-Face Event")) {
                    Tmp = new FaceToFace(props[0], props[1], props[2], fees, avail);
                } else {
                    Tmp = new Event(props[0], props[1], props[2], fees, avail);
                }

                events[count] = Tmp;

                count++;
                //System.out.println("count: " + count);
            }

            br.close(); //Must close the file after reading everything

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find");
        } catch (IOException e) {
            System.out.println("Got problem");
        }

        return events;
    }

    //Save events into events.dat
    public void processData(Event[] events) {
        File f = new File("events.dat");

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            for (int i = 0; i < events.length; i++) {
                os.writeObject(events[i]);
            }
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Read from events.dat
    public ArrayList<Event> readObj() {
        ArrayList<Event> events = new ArrayList<>();

        boolean Complete = false;
        File f = new File("events.dat");
        try {
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(fos);

            while (!Complete) {
                Event test = (Event) inStream.readObject();
                events.add((Event) test);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found");
            return null;
        } catch (EOFException e) {
            Complete = true;
        } catch (Exception ex) {
            System.out.println("Unknown Error");
            ex.printStackTrace();
            return null;
        }
        return events;
    }

}
