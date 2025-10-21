import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

public class Database {
    private Connection connection;
    private static Database instance;

    public Database() throws Exception
    {
        String url = "jdbc:sqlite:database/sushi.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connsesso al database");
    }

    public static Database getInstance() throws Exception //Pattern singleton.
    {
        if (instance == null) 
        {
            instance = new Database();   
        }

        return instance;
    }

	public String selectAll() {
		String result = "";
        
        //Controllo che il database sia ancora collegato (va fatto per ogni metodo che fa richeista a database).
        try
        {
            if (connection != null || !connection.isValid(5))
            {
                System.err.println("Connection is null or invalid");
                
                return null;
            }
        } 
        catch (SQLException e)
        {
            System.err.println("Connection is null or invalid: " + e);
            
            return null;
        }
        
        String query = "SELECT * FROM menu";
        try  
        {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                result += rs.getString("id") + "\t";
                result += rs.getString("piatto") + "\t";
                result += rs.getString("prezzo") + "\t";
                result += rs.getString("quantita") + "\n";
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Error in query: " + e);
        }

        return result;
	}

    public boolean insert(String piatto, double prezzo, int quantita)
    {
        try
        {
            if (connection != null || !connection.isValid(5))
            {
                System.err.println("Connection is null or invalid");
                
                return true;
            }
        } 
        catch (SQLException e)
        {
            System.err.println("Connection is null or invalid: " + e);
            
            return false;
        }

        //String query = "INSERT INTO menu(piatto, prezzo, quantita) VALUES ('" + piatto +", " + prezzo +", " + quantita+ "')"; //? = Placeholder per i valori. Qua si può fare un sql injection, ovvero che un utente può droppare la tabella; attenzione! Per questo usiamo i placeholders.
        String query = "INSERT INTO menu(piatto, prezzo, quantita) VALUES (?, ?, ?)";   
        try
        {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, piatto); //Da qui in poi per evitare il sql injection (mette il carattere di escape).
            statement.setDouble(2, prezzo);
            statement.setInt(3, quantita);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Error in query: " + query + e);

            return false;
        }

        return true;
    }
}
