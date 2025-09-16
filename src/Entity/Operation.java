package Entity;

import java.util.Date;

public abstract class Operation {

    protected String numero;
    protected Date date;
    protected Double montant;

    public Operation(String numero, Date date, Double montant) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
    }



}
