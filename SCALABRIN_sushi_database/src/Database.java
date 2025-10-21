import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;

    public Database() throws Exception
    {
        String url = "jdbc:sqlite:database/sushi.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connsesso al database");
    }
}
