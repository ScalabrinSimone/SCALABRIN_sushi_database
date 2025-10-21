import javax.xml.crypto.Data;

public class App {
    public static void main(String[] args) throws Exception {
        Database db = null;
        try
        {
            db = Database.getInstance(); //Pattern singleton.
        }
        catch(Exception e)
        {
            System.err.println("Impossibile connettersi al database: \n" + e);
            System.exit(-1);
        }

        System.out.println(db.selectAll());
    }
}
