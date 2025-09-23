package repository;

import entity.Compte;
import util.Helper;
import dbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteEpargne extends Compte {

    private final Double tauxInteret = 2.5;
    private Connection connection = Dbconnection.getInstance().getConnection();

    public void creeCompte() {
        String sql = "INSERT INTO Compte(code, type_compte, solde) VALUES (?, ?, ?)";
        this.Code = Helper.genererCodeCompte();
        double soldeInitial = this.getSolde();
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1, this.Code);
            valuer.setString(2, "Epargne");
            valuer.setDouble(3, soldeInitial);
            valuer.executeUpdate();
            calculerInteret();

            System.out.println("Compte créé avec succès !");
            System.out.println("Code : " + this.Code);

        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    public double calculerInteret() {
        double interet = this.getSolde() * (tauxInteret / 100);
        double nouveauSolde = this.getSolde() - interet;

        String updateSql = "UPDATE Compte SET solde = ? WHERE code = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(updateSql);
            stmt.setDouble(1, nouveauSolde);
            stmt.setString(2, this.Code);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour du solde : " + e.getMessage());
        }
            return  nouveauSolde;
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
