package chrisg;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {

        HashMap<Integer, ParkingTicket> Tickets = new HashMap<>();
        ReadTicket readTicket = new ReadTicket();
        Tickets = readTicket.readTicketFile("tickets.txt");

        Scanner key = new Scanner(System.in);
        String input = "";
        boolean exit = false;

        while (!exit) {
            System.out.println("Best Value Parking Garage");
            System.out.println("==========================");
            System.out.println("Go to: ");
            System.out.println("1 - Check In Machine");
            System.out.println("2 - Check Out Machine");
            System.out.println("3 - Exit");
            System.out.print("=>");
            input = key.next();
            if (input.compareTo("1") == 0) {
                Tickets = checkIn(Tickets);
            } else if (input.compareTo("2") == 0) {
                Tickets = checkOut(Tickets);
            } else if (input.compareTo("3") == 0) {
                writeFile(Tickets);
                double total = 0;
                int numcount = 0;
                int count = 0;
                for (HashMap.Entry<Integer, ParkingTicket> entry : Tickets.entrySet()){

                    if(entry.getValue().isLostTicket()){
                        numcount++;
                    }
                    total += entry.getValue().getAmount();



                    count++;
                }
                double lost = 25 * numcount;
                System.out.println("$" + total + "0 was collected from " + count + " Check-Ins");
                System.out.println("$" + lost + "0 was collected from " + numcount + " Lost Tickets");
                double alltotal = lost + total;
                System.out.println("$" + alltotal + "0 was collected overall");
                exit = true;
            } else {
                System.out.println("Input not accepted. Try again.\n\n");
            }
        }
        //write to file
        System.out.println("Garage has been closed.");
    }

    public static HashMap<Integer, ParkingTicket> checkIn(HashMap<Integer, ParkingTicket> Tickets){
        Scanner key = new Scanner(System.in);
        String input;
        boolean close = false;
        while (!close) {
            //Checking in
            System.out.println("Best Value Parking Garage");
            System.out.println("==========================");
            System.out.println("1 - Check/In");
            System.out.println("2 - Back");
            System.out.print("=>");
            input = key.nextLine();
            if (input.compareTo("1") == 0) {
                try {
                    Tickets = addTicket(Tickets);
                    System.out.println("Successfully checked in.");
                } catch (Exception e) {
                    System.out.println("The following error has occured: " + e);
                }
            } else if (input.compareTo("2") == 0) {
                close = true;
            } else {
                System.out.println("Input not accepted. Try again.\n\n");
            }
        }
        return Tickets;
    }
    public static HashMap<Integer, ParkingTicket> checkOut(HashMap<Integer, ParkingTicket> Tickets){
        Scanner key = new Scanner(System.in);
        String input;
        boolean close = false;
        int vehicleId = 0;
        ParkingTicket info = null;

        for (HashMap.Entry<Integer, ParkingTicket> entry : Tickets.entrySet()){

            if(vehicleId == entry.getKey()){
                vehicleId = entry.getKey();
                info = entry.getValue();
            } else {
                vehicleId = entry.getKey();
                info = entry.getValue();
                break;
            }
//            vehicleId = entry.getKey();
//            info = entry.getValue();
//            break;
        }

        System.out.println("Best Value Parking Garage");
        System.out.println("==========================");
        System.out.println("1 - Check/Out");
        System.out.println("2 - Lost Ticket");
        System.out.print("=>");
        input = key.nextLine();
        if(input.compareTo("1") == 0) {
            try {
                assert info != null;
                System.out.println("Receipt for a vehicle id " + vehicleId);
                System.out.println("\n\n");
                System.out.println("Hours parked   " + info.getTimeIn() + "AM - " + info.getTimeOut() + "PM     $"
                + info.getAmount() + "0");
                System.out.println("Checked out.");
            } catch (Exception e) {
                System.out.println("The following error has occurred: " + e);
            }
        } else if(input.compareTo("2") == 0){
            System.out.println("Receipt for a vehicle id " + vehicleId);
            System.out.println("\n\n");
            System.out.println("Lost Ticket");
            info.setLostTicket(true);
            System.out.println("$25.00");
        } else {
            System.out.println("Input not accepted. Try again.\n\n");
        }
//        Tickets.remove(vehicleId);
        return Tickets;
    }

    public static void writeFile(HashMap<Integer, ParkingTicket> t) throws IOException {
        WriteTicket writeTicket = new WriteTicket();
        writeTicket.writeFile("tickets.txt", t);
    }

    private static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public static HashMap<Integer, ParkingTicket> addTicket(HashMap<Integer, ParkingTicket> t){
        ParkingTicket ticket = new ParkingTicket();
// 13 - 23 represents 1pm to 11pm
        ticket.setTimeIn(LocalTime.of(randomNumber(7, 12),0));
        ticket.setTimeOut(LocalTime.of(randomNumber(13, 23),0));


        t.put(randomNumber(100, 999), ticket);

        return t;
    }
}
