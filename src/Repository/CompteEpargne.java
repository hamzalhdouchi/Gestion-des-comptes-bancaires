package Repository;

import Entity.Compte;
import Util.Helper;
import dbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteEpargne extends Compte {

    private final Double tauxInteret = 2.5;
    private Connection connection = Dbconnection.getInstance().getConnection();

    public void créeCompte(){
        String sql = "INSERT INTO Compte(code, solde, type_compte) VALUES (?, ?, ?)";
        this.Code = Helper.genererCodeCompte();
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1, this.Code);
            valuer.setDouble(2,this.Solde);
            valuer.setString(3,"Epargne");
            valuer.executeUpdate();
            System.out.println("Code : " + this.Code);

        }catch (Exception e){
            System.out.println("Erreur de insertion");
        }
    }
    public void afficherDetails() {
        String sql = "SELECT * FROM Compte WHERE code = ?";
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1,this.Code);
            ResultSet rs = valuer.executeQuery();

            Double val1 = rs.getDouble("solde");
            Double val2 = rs.getDouble("type_compte");

            System.out.println("votre compte has code : " + this.Code);
            System.out.println("votre solde :"+ val2);
            System.out.println("le type de votre compte" + val2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public double calculerInteret(){
        String sql = "select * from compte where Code = ?";
        Double lastrs = null;
        try {
            PreparedStatement sold = this.connection.prepareStatement(sql);
            sold.setString(1, Code);
            ResultSet rs = sold.executeQuery();
            Double solde = rs.getDouble("solde");
            lastrs = solde * (tauxInteret /100);
        }catch (Exception e){
            System.out.println("Erreur de insertion");
        }
        return lastrs;
    }

    public void retirer(String Code,Double montant){
        setCode(Code);
        String sql = "select * from compte where Code = ?";
        Compte val = null;
        try{
            PreparedStatement sold =this.connection.prepareStatement(sql);
            sold.setString(1, Code);
            ResultSet rs = sold.executeQuery();
            Double solde = rs.getDouble("solde");
            if (montant <= solde){
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
