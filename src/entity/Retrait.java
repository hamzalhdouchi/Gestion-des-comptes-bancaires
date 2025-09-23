package entity;

import java.util.Date;

public class Retrait extends Operation {

    private String destination;

    public Retrait(String compteId, Date date , double montant, String destination) {
        super(compteId,date, montant);
        this.destination = destination;
    }

    public String  getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
       this.destination = destination;
    }
    public String getType() {
        return "retrait";
    }


}
