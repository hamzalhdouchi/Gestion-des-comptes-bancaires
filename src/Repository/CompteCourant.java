package Repository;
import Entity.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Operation;
import Entity.Retrait;
import Entity.Versement;
import Util.Helper;
import dbConnection.Dbconnection;

public class CompteCourant extends Compte {

    private Connection connection = Dbconnection.getInstance().getConnection();

    public double Sold(String CodeCompte){
        String sql = "SELECT * FROM Compte WHERE code = ?";
        Double Solde = 0.0;
        try {
            PreparedStatement solders = this.connection.prepareStatement(sql);
            solders.setString(1, CodeCompte);
            ResultSet rs = solders.executeQuery();
            if (rs.next()) {
                 Solde = rs.getDouble("solde");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return  Solde;
    }
    public void créeCompte(){
        String sql = "INSERT INTO Compte(code, solde, type_compte) VALUES (?, ?, ?)";
        this.Code = Helper.genererCodeCompte();
        try {
            PreparedStatement valuer = this.connection.prepareStatement(sql);
            valuer.setString(1, String.valueOf(this.Code));
            valuer.setDouble(2,this.Solde);
            valuer.setString(3,"Courant");
            valuer.executeUpdate();
            System.out.println("Votre Code : " + this.Code);

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
    public List<Operation> getOperationsByCompte(String codeCompte) {
        List<Operation> operations = new ArrayList<>();
        setCode(codeCompte);
        try {
            String sql = "SELECT * FROM operations WHERE compte_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, codeCompte);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date date = null;
                Operation op = null;
                String id = rs.getString("id");
                double montant = rs.getDouble("montant");
                String type = rs.getString("type_operation");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                String dateStr = rs.getString("date_operation");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                try {
                    date = formatter.parse(dateStr);
                    if ("versement".equalsIgnoreCase(type)) {
                        op = new Versement(id, date, montant, source);
                    } else if ("retrait".equalsIgnoreCase(type)) {
                        op = new Retrait(id, date, montant, destination);
                    } else {
                        op = new Operation(id, date, montant) {
                            public String getType() {
                                return type;
                            }
                        };
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                operations.add(op);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des opérations : " + e.getMessage());
        }
        return operations;
    }

}