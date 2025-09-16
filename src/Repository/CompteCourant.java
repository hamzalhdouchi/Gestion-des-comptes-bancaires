package Repository;
import Entity.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbConnection.Dbconnection;

public class CompteCourant extends Compte {

    private Connection connection = Dbconnection.getInstance().getConnection();

    public CompteCourant(String Code, Double Sold) {
        super(Sold, Code);
    }

    public void créeCompte(){
        String sql = "INSERT INTO Compte(code, solde, type_compte) VALUES (?, ?, ?)";
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1, this.Code);
            valuer.setDouble(2,this.Solde);
            valuer.setString(3,"Courant");
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
        return 0;
    }


}