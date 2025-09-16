package Repository;

import dbConnection.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetraitRepo {
    private final Connection connection = Dbconnection.getInstance().getConnection();
    private static final int DECOUVERT = -10000;

    // Retirer un montant
    public void retirer(String code, double montant) {
        String sql = "SELECT solde, type_compte FROM compte WHERE code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double solde = rs.getDouble("solde");
                String typeCompte = rs.getString("type_compte");

                double newSolde;

                if ("courant".equalsIgnoreCase(typeCompte)) {
                    if (solde - montant >= DECOUVERT) {
                        newSolde = solde - montant;
                        updateSolde(code, newSolde);
                        System.out.println("Retrait réussi. Nouveau solde : " + newSolde);
                    } else {
                        System.out.println("Solde insuffisant (dépasse le découvert autorisé).");
                    }
                } else {
                    if (solde - montant >= 0) {
                        newSolde = solde - montant;
                        updateSolde(code, newSolde);
                        System.out.println("Retrait réussi. Nouveau solde : " + newSolde);
                    } else {
                        System.out.println("Solde insuffisant.");
                    }
                }
            } else {
                System.out.println("Compte introuvable.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL lors du retrait : " + e.getMessage());
        }
    }

    private void updateSolde(String code, double newSolde) {
        String sql = "UPDATE compte SET solde = ? WHERE code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newSolde);
            stmt.setString(2, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du solde : " + e.getMessage());
        }
    }


    public void enrgister(String code, double montant ) {
            String sql = "insert into operation(id,date_operation,montant,type_operation,destination,compte_id) VALUES (?,?,?,?,?,?)";

            try {

            }catch{

        }
    }
}
