package dsrv.orablob;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class JdbcOracleConnection {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public Connection connect(String user, String pwd) throws Exception {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
