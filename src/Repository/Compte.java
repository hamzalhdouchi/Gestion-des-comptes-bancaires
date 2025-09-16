package Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Compte {


    protected String Code;
    protected  Double Solde;
    protected List<String> listeOperations;

    public abstract String  retirer();

    public abstract double calculerInteret();

    public abstract List<String> afficherDetails();
}
