package Repository;

import Util.Helper;
import dbConnection.Dbconnection;
import Entity.Versement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class versementRepo {

    private final Connection connection = Dbconnection.getInstance().getConnection();

    public void verser(String codeCompte, double montant, String source) {
        try {
            String sqlSelect = "SELECT solde FROM compte WHERE code = ?";
            PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
            stmtSelect.setString(1, codeCompte);
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                double solde = rs.getDouble("solde");
                double newSolde = solde + montant;

                String sqlUpdate = "UPDATE compte SET solde = ? WHERE code = ?";
                PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);
                stmtUpdate.setDouble(1, newSolde);
                stmtUpdate.setString(2, codeCompte);
                stmtUpdate.executeUpdate();

                System.out.println("Versement réussi. Nouveau solde : " + newSolde);

                String sqlInsert = "INSERT INTO operation (id, date_operation, montant, type_operation, source, compte_source, compte_dest) " +
                        "VALUES (?, ?, ?, 'versement', ?, NULL, ?)";

                String UUID = Helper.genererUUID();
                String Date = Helper.genererUUID();
                PreparedStatement stmtInsert = connection.prepareStatement(sqlInsert);
                stmtInsert.setString(1, UUID);
                stmtInsert.setString(2, Date);
                stmtInsert.setDouble(3, montant);
                stmtInsert.setString(4, source);
                stmtInsert.setString(5, codeCompte);
                stmtInsert.executeUpdate();

                System.out.println("Opération de versement enregistrée.");
            } else {
                System.out.println("Compte introuvable !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du versement : " + e.getMessage());
        }
    }
}
