package dsrv.orablob;


import java.sql.*;

import oracle.jdbc.OracleDriver;

public class JdbcOracleConnection {

    Connection conn = null;

    public Connection connect(String jpdcUrl, String user, String pwd) throws Exception {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(jpdcUrl, user, pwd);
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
