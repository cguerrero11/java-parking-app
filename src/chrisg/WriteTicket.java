package chrisg;

import javax.imageio.IIOException;
import java.io.*;
import java.util.HashMap;

public class WriteTicket implements Serializable {

    public void writeFile(String fileName, HashMap<Integer, ParkingTicket> tk) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream ois = new ObjectOutputStream(file);
            ois.writeObject(tk);
            ois.close();
            file.close();
            System.out.println("Information Saved.");

        } catch (IIOException e){
            System.out.println("Something went wrong when writing to file.");
        }
    }









}
