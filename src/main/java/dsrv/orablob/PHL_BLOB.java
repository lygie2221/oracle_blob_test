package dsrv.orablob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PHL_BLOB {
    public void saveBlob(Integer pk, String filename, Connection conn) throws SQLException, FileNotFoundException {

        if(null==pk){
            pk=1;
        }

        PreparedStatement pstmt =
                conn.prepareStatement("update PHL_BLOB set PHL_BLOB = ? where pruefid = ?");
        File blob = new File(filename);
        FileInputStream in = new FileInputStream(blob);

        // the cast to int is necessary because with JDBC 4 there is
        // also a version of this method with a (int, long)
        // but that is not implemented by Oracle
        pstmt.setBinaryStream(1, in, (int)blob.length());

        pstmt.setInt(2, pk);  // set the PK value
        pstmt.executeUpdate();
        conn.commit();
        pstmt.close();

    }
}
