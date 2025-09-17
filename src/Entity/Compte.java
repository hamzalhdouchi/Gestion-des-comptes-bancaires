package Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {


    protected String Code;
    protected  Double Solde = 0.0;
    protected List<String> listeOperations;

    public Compte(Double Solde , String Code) {
        this.Solde = Solde;
        this.Code = Code;
        this.listeOperations = new ArrayList<>();
    }

    public Compte(String Code) {
        this.Solde = Solde;
        this.Code = Code;
    }
    public Compte() {

    }

    public abstract void créeCompte();
    public abstract double calculerInteret();

    public abstract void afficherDetails();

    public String getCode() {
        return Code;
    }
    public void setCode(String Code) {
        this.Code = Code;
    }
    public Double getSolde() {
        return Solde;
    }
    public void setSolde(Double Solde) {
        this.Solde = Solde;
    }
    public List<String> getListeOperations() {
        return listeOperations;
    }
    public void setListeOperations(List<String> listeOperations) {
        this.listeOperations = listeOperations;
    }

}
