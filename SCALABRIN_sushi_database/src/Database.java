import java.sql.Connection;
import java.sql.DriverManager;

import javax.xml.crypto.Data;

public class Database {
    private Connection connection;
    private static Database instance;

    public Database() throws Exception
    {
        String url = "database/sushi.db";
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
}
