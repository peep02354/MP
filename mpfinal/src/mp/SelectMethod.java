package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static mp.TestUI.DBURL;

public class SelectMethod {
    
    public static void selectAll() {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE_NO,OE_NAME,company,C_PRICE,D_PRICE,G_PRICE,R_PRICE from OE");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDouble(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
            }
            p = conn.prepareCall("select OEM_NO,OEM_NAME,company,C_PRICE,D_PRICE,G_PRICE,R_PRICE from OEM");
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDouble(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
