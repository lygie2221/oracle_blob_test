package dsrv.orablob;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        if(args.length != 4){
            System.out.println("usage: java -jar orablob <username> <password> <pruefid> <filename>");
        }
        else{
            Connection conn = null;
            JdbcOracleConnection connector = new JdbcOracleConnection();
            PHL_BLOB phl_blob = new PHL_BLOB();
            try {
                conn = connector.connect(args[0], args[1]);
                conn.setAutoCommit(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                phl_blob.saveBlob(Integer.parseInt(args[2]), args[3],conn);
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                connector.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}