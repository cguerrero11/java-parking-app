package chrisg;

import java.io.*;
import java.util.HashMap;

public class ReadTicket implements Serializable {
    private HashMap ticketList;

    public HashMap<Integer, ParkingTicket> readTicketFile(String fileName){
        try {

            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(file);

            ticketList = (HashMap) ois.readObject();
        } catch (Exception e){
            System.out.println("Something went wrong when reading file.");
        }
        return ticketList;
    }

}
