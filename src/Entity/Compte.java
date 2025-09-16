package Entity;

import java.util.List;

public abstract class Compte {


    protected String Code;
    protected  Double Solde;
    protected List<String> listeOperations;

    public abstract String  retirer(Double montant);

    public abstract double calculerInteret(Double montant);

    public abstract List<String> afficherDetails();

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
