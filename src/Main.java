import Repository.CompteCourant;
import Repository.CompteEpargne;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        CompteCourant CompteCourant = new CompteCourant();
        CompteEpargne compteEpargne = new CompteEpargne();
        boolean exit = false;
        while (!exit){
            System.out.println("***************************************");
            System.out.println("                 Menu                  ");
            System.out.println("***************************************");
            System.out.println("1. Créer un compte");
            System.out.println("2. Effectuer un versement");
            System.out.println("3. Effectuer un retrait");
            System.out.println("4. Effectuer un virement");
            System.out.println("5. Consulter le solde d'un compte");
            System.out.println("6. Consulter les opérations d'un compte");
            System.out.println("0. Quitter");
            System.out.println("choix : ");
            int choise = input.nextInt();
            input.nextLine();

            switch (choise){
                case 1 :
                    System.out.println("Type de compte (courant/epargne) : ");
                    String compte = input.nextLine();
                    if (compte.equals("courant")){
                        CompteCourant.créeCompte();
                    }else if (compte.equals("epargne")){

                    }
            }
            }


        }
}