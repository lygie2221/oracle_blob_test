package dsrv.orablob;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        if(args.length != 8){
            System.out.println("usage: java -jar orablob <jdpcurl> <username> <password> <table> <keycolumn>  " +
                    "<key_value> <blob_column> <filename>");
        }
        else{
            Connection conn = null;
            JdbcOracleConnection connector = new JdbcOracleConnection();
            PHL_BLOB phl_blob = new PHL_BLOB();
            try {
                conn = connector.connect(args[0], args[1], args[2]);
                conn.setAutoCommit(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                phl_blob.saveBlob(
                        args[3],
                        args[4],
                        Integer.parseInt(args[5]),
                        args[6],
                        args[7],
                        conn);
                conn.commit();
            } catch (SQLException e) {
                System.out.println(args[3] + ":" + args[4]);
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