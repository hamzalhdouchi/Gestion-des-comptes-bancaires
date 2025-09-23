package entity;

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
    public Operation(String numero, Date date) {
        this.numero = numero;
        this.date = date;
    }
    public Operation(String numero) {
        this.numero = numero;
    }
    public Operation() {

    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    public abstract String getType();


}
