import Entity.Compte;
import Entity.Operation;
import Entity.Retrait;
import Entity.Versement;
import Repository.CompteCourant;
import Repository.CompteEpargne;
import Repository.RetraitRepo;
import Repository.versementRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void afficherMenu() {
        System.out.println("\n=======================================");
        System.out.println("***********   MENU BANCAIRE   *********");
        System.out.println("=======================================");
        System.out.println(" 1. Créer un compte");
        System.out.println(" 2. Effectuer un versement");
        System.out.println(" 3. Effectuer un retrait");
        System.out.println(" 4. Effectuer un virement");
        System.out.println(" 5. Consulter le solde d'un compte");
        System.out.println(" 6. Consulter les opérations d'un compte");
        System.out.println(" 0. Quitter");
        System.out.println("=======================================");
        System.out.print("Votre choix : ");
    }

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        CompteCourant CompteCourant = new CompteCourant();
        CompteEpargne compteEpargne = new CompteEpargne();
        versementRepo versementRepo = new versementRepo();
        boolean exit = false;

        while (!exit) {
            afficherMenu();

            int choise;
            try {
                choise = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("=======================================");
                System.out.println("*******  Erreur : choix invalide  ******");
                System.out.println("=======================================");
                continue;
            }

            switch (choise) {
                case 1:
                    System.out.println("\n=======================================");
                    System.out.println("*******   CREATION DE COMPTE   ********");
                    System.out.println("=======================================");
                    System.out.print("Type de compte (courant/epargne) : ");
                    String compte = input.nextLine().toLowerCase();

                    System.out.print("Entrez le solde initial : ");
                    double compteSolde;
                    try {
                        compteSolde = Double.parseDouble(input.nextLine());
                        if (compteSolde < 0) {
                            System.out.println("*******  Erreur : solde négatif   ******");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("*******  Erreur : montant invalide ******");
                        break;
                    }

                    if (compte.equals("courant")) {
                        CompteCourant.setSolde(compteSolde);
                        CompteCourant.créeCompte();
                        System.out.println("******* Compte courant créé avec succès *******");
                    } else if (compte.equals("epargne")) {
                        compteEpargne.setSolde(compteSolde);
                        compteEpargne.créeCompte();
                        System.out.println("******* Compte épargne créé avec succès *******");
                    } else {
                        System.out.println("*******  Erreur : type de compte invalide ******");
                    }
                    break;

                case 2:
                    System.out.println("\n=======================================");
                    System.out.println("*******   EFFECTUER UN VERSEMENT   *****");
                    System.out.println("=======================================");

                    boolean userHasAccount = (CompteCourant.getCode() != null || compteEpargne.getCode() != null);
                    String codeCompte = null;
                    String codeBeneficiaire;

                    if (!userHasAccount) {
                        System.out.print("Avez-vous un compte ? (yes/no) : ");
                        String ins = input.nextLine().toLowerCase();
                        if (ins.equals("yes")) {
                            System.out.print("Entrez le code de votre compte : ");
                            codeCompte = input.nextLine();
                        } else {
                            System.out.println("******* Créez un compte d'abord *******");
                            break;
                        }
                    }

                    System.out.print("Entrez le montant : ");
                    double montant;
                    try {
                        montant = Double.parseDouble(input.nextLine());
                        if (montant <= 0) {
                            System.out.println("******* Erreur : montant invalide ******");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("******* Erreur : saisie incorrecte ******");
                        break;
                    }

                    System.out.print("Entrez la source de l'opération : ");
                    String source = input.nextLine();

                    if (codeCompte == null || codeCompte.isEmpty()) {
                        System.out.print("Entrez le code de votre compte : ");
                        codeCompte = input.nextLine();
                    }

                    System.out.print("Entrez le code du compte bénéficiaire : ");
                    codeBeneficiaire = input.nextLine();

                    versementRepo.verserToCompte(codeCompte, codeBeneficiaire, montant, source, "versement");
                    System.out.println("******* Versement effectué avec succès *******");
                    break;

                case 3:
                    System.out.println("\n=======================================");
                    System.out.println("********   EFFECTUER UN RETRAIT   ******");
                    System.out.println("=======================================");

                    RetraitRepo RetraitRepo = new RetraitRepo();
                    boolean userAccount = (CompteCourant.getCode() != null || compteEpargne.getCode() != null);
                    String codeDeCompte = null;

                    if (!userAccount) {
                        System.out.print("Avez-vous un compte ? (yes/no) : ");
                        String ins = input.nextLine().toLowerCase();
                        if (ins.equals("yes")) {
                            System.out.print("Entrez le code de votre compte : ");
                            codeDeCompte = input.nextLine();
                        } else {
                            System.out.println("******* Créez un compte d'abord *******");
                            break;
                        }
                    }

                    System.out.print("Entrez le montant : ");
                    double montantRetrait;
                    try {
                        montantRetrait = Double.parseDouble(input.nextLine());
                        if (montantRetrait <= 0) {
                            System.out.println("******* Erreur : montant invalide ******");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("******* Erreur : saisie incorrecte ******");
                        break;
                    }

                    System.out.print("Entrez la destination de l'opération : ");
                    String destination = input.nextLine();

                    RetraitRepo.retirer(codeDeCompte, montantRetrait, destination);
                    System.out.println("******* Retrait effectué avec succès *******");
                    break;

                case 4:
                    System.out.println("\n=======================================");
                    System.out.println("********   EFFECTUER UN VIREMENT   *****");
                    System.out.println("=======================================");

                    System.out.print("Entrez le code de votre compte : ");
                    String codeExpediteur = input.nextLine();
                    System.out.print("Entrez le code du compte bénéficiaire : ");
                    String codeBenef = input.nextLine();

                    System.out.print("Entrez le montant : ");
                    double montantVirement;
                    try {
                        montantVirement = Double.parseDouble(input.nextLine());
                        if (montantVirement <= 0) {
                            System.out.println("******* Erreur : montant invalide ******");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("******* Erreur : saisie incorrecte ******");
                        break;
                    }

                    versementRepo.verserToCompte(codeExpediteur, codeBenef, montantVirement, "virement", "virement");
                    System.out.println("******* Virement effectué avec succès *******");
                    break;

                case 5:
                    System.out.println("\n=======================================");
                    System.out.println("*******   CONSULTER LE SOLDE   ********");
                    System.out.println("=======================================");
                    System.out.print("Entrez le code de votre compte : ");
                    String codeSold = input.nextLine();
                    Double solde = CompteCourant.Sold(codeSold);
                    if (solde != null) {
                        System.out.println("Votre solde est : " + solde);
                    } else {
                        System.out.println("******* Compte introuvable *******");
                    }
                    break;

                case 6:
                    System.out.println("\n=======================================");
                    System.out.println("****   CONSULTER LES OPERATIONS   ******");
                    System.out.println("=======================================");
                    System.out.print("Entrez le code du compte : ");
                    String codeCompteAcconte = input.nextLine();

                    List<Operation> operations = CompteCourant.getOperationsByCompte(codeCompteAcconte);
                    if (operations.isEmpty()) {
                        System.out.println("******* Aucune opération trouvée *******");
                    } else {
                        for (Operation op : operations) {
                            System.out.println("---------------------------------------");
                            System.out.println("Numéro opération : " + op.getNumero());
                            System.out.println("Date opération   : " + op.getDate());
                            System.out.println("Montant          : " + op.getMontant());
                            System.out.println("Type             : " + op.getType());
                            if (op instanceof Versement) {
                                System.out.println("Source           : " + ((Versement) op).getSource());
                            }
                            if (op instanceof Retrait) {
                                System.out.println("Destination      : " + ((Retrait) op).getDestination());
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("\n=======================================");
                    System.out.println("*******      AU REVOIR !        *******");
                    System.out.println("=======================================");
                    exit = true;
                    break;

                default:
                    System.out.println("=======================================");
                    System.out.println("*******  Erreur : choix invalide  ******");
                    System.out.println("=======================================");
            }
        }
    }
}
