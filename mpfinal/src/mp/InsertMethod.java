package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static mp.TestUI.DBURL;

public class InsertMethod {

    //insert
    public static void addOe(String oe, String name, String bc, String comp,String location, double c, double d, double g, double r) {
        try {
            Properties props = new Properties();
            props.put("charSet", "UTF-8");
            Connection conn = DriverManager.getConnection(DBURL);
            String sql = "insert into oe (oe_no,oe_name,barcode,company,location,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, comp);
            p.setString(5, location);
            p.setDouble(6, c);
            p.setDouble(7, d);
            p.setDouble(8, g);
            p.setDouble(9, r);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addOem(String oem, String name, String bc, String oe,String comp,String location, double c, double d, double g, double r) {
        try {
            Properties props = new Properties();
            props.put("charSet", "UTF-8");
            Connection conn = DriverManager.getConnection(DBURL);
            String sql = "insert into oem (oem_no,oem_name,barcode,oe_no,company,location,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oem);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, oe);
            p.setString(5, comp);
            p.setString(6, location);
            p.setDouble(7, c);
            p.setDouble(8, d);
            p.setDouble(9, g);
            p.setDouble(10, r);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addModel(String oe, String model) {
        try {
            Properties props = new Properties();
            props.put("charSet", "UTF-8");
            Connection conn = DriverManager.getConnection(DBURL);
            String sql = "insert into oe_model (oe_no,model) values(?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, model);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBody(String oe, String body) {
        try {
            Properties props = new Properties();
            props.put("charSet", "UTF-8");
            Connection conn = DriverManager.getConnection(DBURL);
            String sql = "insert into oe_body (oe_no, body) values(?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, body);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEngine(String oe, String engine) {
        try {
            Properties props = new Properties();
            props.put("charSet", "UTF-8");
            Connection conn = DriverManager.getConnection(DBURL);
            String sql = "insert into oe_engine (oe_no,engine) values(?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, engine);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
