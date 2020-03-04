package chrisg;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class ParkingTicket implements Serializable {

    private LocalTime timeIn;
    private LocalTime timeOut;
    private boolean lostTicket = false;

    public boolean isLostTicket() {
        return lostTicket;
    }

    public void setLostTicket(boolean lostTicket) {
        this.lostTicket = lostTicket;
    }

    public int getTimeIn() {
        return timeIn.getHour();
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public int getTimeOut() {
        return timeOut.getHour() - 12;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }


    public double getAmount() {
        double amount;
        Duration hoursParked = Duration.between(timeIn, timeOut);
//Amount is calculated
        double hour = (double)(hoursParked.getSeconds()/60)/60;

        if(hour <= 3){
            amount = 5;
        } else {
            amount = 5 + (hour - 3);
            if(amount > 15){
                //setting maximum amount
                amount = 15;
            }
        }

        return amount;
    }
}
