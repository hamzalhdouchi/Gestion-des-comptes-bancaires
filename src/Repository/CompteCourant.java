package Repository;
import Entity.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbConnection.Dbconnection;

public class CompteCourant extends Compte {

    private  static final  Integer Découvert = -10000;
    private Connection connection = Dbconnection.getInstance().getConnection();

    public CompteCourant(String Code, Double Sold) {
        super(Sold, Code);
    }

    public String créeCompte(){
        String sql = "INSERT INTO Compte(code, solde, type_compte) VALUES (?, ?, ?)";
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1, this.Code);
            valuer.setDouble(2,this.Solde);
            valuer.setString(3,"Courant");
            valuer.executeUpdate();


        }catch (Exception e){
            System.out.println("Erreur de insertion");
        }
    }
    public void afficherDetails() {
        System.out.println("Code : " + this.Code);
        System.out.println("votre sold " + this.Solde);
        System.out.println("les operation "+ this.listeOperations);
    }
    public double calculerInteret(){
        return 0;
    }

    public void retirer(String Code,Double montant){
        String sql = "select * from compte where Code = ?";
        Compte val = null;
        try{
            PreparedStatement sold =this.connection.prepareStatement(sql);
            sold.setString(1, Code);
            ResultSet rs = sold.executeQuery();
            Double solde = rs.getDouble("solde");
            if (montant <= solde - Découvert){
                Double newSolde = solde - montant;
                this.Solde  = newSolde;
                sql = "update compte set solde = ? where Code = ?";
                try
                    {
                    PreparedStatement update = this.connection.prepareStatement(sql);
                    update.setDouble(1, newSolde);
                    }catch(Exception e){
                    System.out.println("update field");
                }
            }else {
                System.out.println("votre solde éte unsatesfusent");
            }
        }catch(Exception e){
            System.out.println("recuperation de la compte field");
        }

    }
}