package dsrv.orablob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PHL_BLOB {
    public void saveBlob(String table,
                         String keycolumn,
                         Integer keyvalue,
                         String blobcolumn,
                         String filename,
                         Connection conn) throws SQLException, FileNotFoundException {

        if(null==keyvalue){
            keyvalue=1;
        }

        if(!isSecureTableOrColumnName(table)){
            throw new SQLException("Table " + table + " not valid");
        }
        if(!isSecureTableOrColumnName(blobcolumn)){
            throw new SQLException("Table " + blobcolumn + " not valid");
        }
        if(!isSecureTableOrColumnName(keycolumn)){
            throw new SQLException("Table " + keycolumn + " not valid");
        }

        PreparedStatement pstmt =
                conn.prepareStatement("update " + table + " set " + blobcolumn + " = ? where " + keycolumn + " = ?");
        File blob = new File(filename);
        FileInputStream in = new FileInputStream(blob);


        // the cast to int is necessary because with JDBC 4 there is
        // also a version of this method with a (int, long)
        // but that is not implemented by Oracle
        pstmt.setBinaryStream(1, in, (int)blob.length());

        pstmt.setInt(2, keyvalue);  // set the PK value
        pstmt.executeUpdate();
        conn.commit();
        pstmt.close();
    }

    private boolean isSecureTableOrColumnName(String argument){
        Pattern p = Pattern.compile("[a-zA-Z0-9_]{0,127}");
        Matcher m = p.matcher(argument);
        return m.matches();
    }
}
