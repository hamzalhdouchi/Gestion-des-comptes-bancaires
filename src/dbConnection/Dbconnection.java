package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    private static Dbconnection instance = null;
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/gestion_des_compte_bancaires?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "37533753";

    private Dbconnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
        }
    }

    public static synchronized Dbconnection getInstance() {
        if (instance == null) {
            instance = new Dbconnection();
        }
        return instance;
    }


}
