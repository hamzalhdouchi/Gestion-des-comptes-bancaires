package Repository;

import Entity.Compte;

public class CompteEpargne extends Compte {

    private final Double tauxInteret = 2.5;

    public CompteEpargne(Double Sold, String Code) {
        super(Sold, Code);
    }

    public void afficherLesDetails() {
        System.out.println("Code : " + this.Code);
        System.out.println("votre solde : " + this.Solde);
        System.out.println("taux Interet applique sur votre compte : " + this.Code);
    }
    public double calculerInteret() {

    }

}
