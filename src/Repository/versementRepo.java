package Repository;

import Util.Helper;
import dbConnection.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class versementRepo {

    private final Connection connection = Dbconnection.getInstance().getConnection();

    // Constante découvert
    public static final double DECOUVERT = -200.0;

    public void verserToCompte(String codeExpediteur, String codeBeneficiaire, double montant, String source, String typeOperation) {
        if (codeExpediteur != null && codeExpediteur.equals(codeBeneficiaire)) {
            System.out.println("Tu ne peux pas envoyer de l'argent à toi-même.");
            return;
        }

        try {
            // Vérifier expéditeur
            String sqlSelect = "SELECT solde, type_compte FROM compte WHERE code = ?";
            PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
            stmtSelect.setString(1, codeExpediteur);
            ResultSet rs = stmtSelect.executeQuery();

            if (!rs.next()) {
                System.out.println("Compte expéditeur introuvable !");
                return;
            }

            double soldeExp = rs.getDouble("solde");
            String typeExp = rs.getString("type_compte");

            // Vérifier règles de solde
            if (typeExp.equals("courant")) {
                if (soldeExp - montant < DECOUVERT) {
                    System.out.println("Solde insuffisant (courant).");
                    return;
                }
            } else if (typeExp.equals("epargne")) {
                if (soldeExp - montant < 0) {
                    System.out.println("Solde insuffisant (épargne).");
                    return;
                }
            }

            double newSoldeExp = soldeExp - montant;
            String sqlUpdateExp = "UPDATE compte SET solde = ? WHERE code = ?";
            PreparedStatement stmtUpdateExp = connection.prepareStatement(sqlUpdateExp);
            stmtUpdateExp.setDouble(1, newSoldeExp);
            stmtUpdateExp.setString(2, codeExpediteur);
            stmtUpdateExp.executeUpdate();

            System.out.println("Débit expéditeur : " + montant + " | Nouveau solde : " + newSoldeExp);

            saveOperation(montant, source, codeExpediteur, "versement");

            creditBeneficiary(codeBeneficiaire, montant, source,typeOperation);

        } catch (SQLException e) {
            System.out.println("Erreur lors du versement : " + e.getMessage());
        }
    }

    public void creditBeneficiary(String codeBeneficiaire, double montant, String source,String typeOperation) throws SQLException {
        String sqlSelect = "SELECT solde FROM compte WHERE code = ?";
        PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
        stmtSelect.setString(1, codeBeneficiaire);
        ResultSet rs = stmtSelect.executeQuery();

        if (!rs.next()) {
            System.out.println("Compte bénéficiaire introuvable !");
            return;
        }

        double soldeBen = rs.getDouble("solde");
        double newSoldeBen = soldeBen + montant;

        String sqlUpdate = "UPDATE compte SET solde = ? WHERE code = ?";
        PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);
        stmtUpdate.setDouble(1, newSoldeBen);
        stmtUpdate.setString(2, codeBeneficiaire);
        stmtUpdate.executeUpdate();

        System.out.println("bénéficiaire : " + montant + " | Nouveau solde : " + newSoldeBen);

        saveOperation(montant, source, codeBeneficiaire, "retrait");
    }

    private void saveOperation(double montant, String source, String codeCompte, String typeOperation) throws SQLException {
        String sqlInsert = "INSERT INTO operations (id, date_operation, montant, type_operation, source, compte_id) VALUES (?, ?, ?, ?, ?, ?)";
        String UUID = Helper.genererUUID();
        String Date = Helper.genererDateOperation();

        PreparedStatement stmtInsert = connection.prepareStatement(sqlInsert);
        stmtInsert.setString(1, UUID);
        stmtInsert.setString(2, Date);
        stmtInsert.setDouble(3, montant);
        stmtInsert.setString(4, typeOperation);
        stmtInsert.setString(5, source);
        stmtInsert.setString(6, codeCompte);
        stmtInsert.executeUpdate();
    }
}
