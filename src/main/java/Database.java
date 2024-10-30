import org.sql2o.Sql2o;

public class Database {
    public static Sql2o db;
    static {
        db = new Sql2o("jdbc:mysql://localhost:3306/tokobajubudi", "root", "123456");
    }
}
