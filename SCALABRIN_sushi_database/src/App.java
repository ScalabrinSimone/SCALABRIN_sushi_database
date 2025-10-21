public class App {
    public static void main(String[] args) throws Exception {
        try
        {
            Database db = Database.getInstance(); //Pattern singleton.
            
        }
        catch(Exception e)
        {
            System.err.println("Impossibile connettersi al database: \n" + e);
            System.exit(-1);
        }
    }
}
