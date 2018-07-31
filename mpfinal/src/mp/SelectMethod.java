package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static mp.TestUI.DBURL;

public class SelectMethod {
    
    public static void all() {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE_NO,OE_NAME,C_PRICE,R_PRICE from OE");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                
            }
            p = conn.prepareStatement("select OEM_NO,OEM_NAME,C_PRICE,R_PRICE from OE");
            rs = p.executeQuery();
            while (rs.next()) {
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
