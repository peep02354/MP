package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static mp.TestUI.DBURL;

public class InsertMethod {
    //insert
    public static void addOe(String oe, String name, String bc,String comp, double c, double d, double g, double r,String body,String engine,String model) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            String sql = "insert into oe (oe_no,oe_name,barcode,company,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, comp);
            p.setDouble(5, c);
            p.setDouble(6, d);
            p.setDouble(7, g);
            p.setDouble(8, r);
            p.executeUpdate();
            addBody(oe, body);
            addEngine(oe, engine);
            addModel(oe, model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addOem(String oem, String name, String bc,String comp, String oe, double c, double d, double g, double r) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            String sql = "insert into oem (oem_no,oem_name,barcode,oe_no,company,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oem);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, comp);
            p.setString(5, oe);
            p.setDouble(6, c);
            p.setDouble(7, d);
            p.setDouble(8, g);
            p.setDouble(9, r);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addModel(String oe, String model) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
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
        try (Connection conn = DriverManager.getConnection(DBURL)) {
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
        try (Connection conn = DriverManager.getConnection(DBURL)) {
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
