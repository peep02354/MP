package mp;

import java.sql.*;

//each select method query from both oe and oem
public class MP {

    final static String DBURL = "jdbc:ucanaccess://C:/Users/mos_s/Desktop/MP/MP.accdb"; //change your msaccess file here
    Statement stmt = null;
    ResultSet result = null;

    //add oe product
    public static void addOe(String oe, String name, String bc, String qr, String comp, double c, double d, double g, double r) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            String sql = "insert into oe (oe_no,oe_name,barcode,company,qrcode,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oe);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, qr);
            p.setString(5, comp);
            p.setDouble(6, c);
            p.setDouble(7, d);
            p.setDouble(8, g);
            p.setDouble(9, r);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add oem product
    public static void addOem(String oem, String name, String bc, String qr, String comp, String oe, double c, double d, double g, double r) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            String sql = "insert into oem (oem_no,oem_name,barcode,qrcode,oe_no,company,c_price,d_price,g_price,r_price) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, oem);
            p.setString(2, name);
            p.setString(3, bc);
            p.setString(4, qr);
            p.setString(5, oe);
            p.setString(6, comp);
            p.setDouble(7, c);
            p.setDouble(8, d);
            p.setDouble(9, g);
            p.setDouble(10, r);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add model of oe
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

    //add body of oe
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

    //add engine of oe
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

    //select all of product
    public static void selectPart() {
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

    //select part from engine
    public static void selectPartEngine(String engine) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE.OE_NO,OE.OE_NAME,OE.C_PRICE,OE.D_PRICE,OE.G_PRICE,OE.R_PRICE "
                    + "from OE, OE_Engine"
                    + "where OE.OE_NO = OE_Engine.OE_NO and OE_Engine.Engine =" + engine);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }
            p = conn.prepareStatement("select OEM.OEM_NO,OEM.OEM_NAME,OEM.C_PRICE,OEM.D_PRICE,OEM.G_PRICE,OEM.R_PRICE "
                    + "from OEM, OE_Engine"
                    + "where OEM.OE_NO = OE_Engine.OE_NO and OE_Engine.Engine =" + engine);
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //select part from model
    public static void selectPartModel(String model) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE.OE_NO,OE.OE_NAME,OE.C_PRICE,OE.D_PRICE,OE.G_PRICE,OE.R_PRICE "
                    + "from OE, OE_Model"
                    + "where OE.OE_NO = OE_Model.OE_NO and OE_Model.Model =" + model);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }

            p = conn.prepareStatement("select OEM.OEM_NO,OEM.OEM_NAME,OEM.C_PRICE,OEM.D_PRICE,OEM.G_PRICE,OEM.R_PRICE "
                    + "from OEM, OE_Model"
                    + "where OEM.OE_NO = OE_Model.OE_NO and OE_Model.Model =" + model);
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //select part from body
    public static void selectPartBody(String body) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE.OE_NO,OE.OE_NAME,OE.C_PRICE,OE.D_PRICE,OE.G_PRICE,OE.R_PRICE "
                    + "from OE, OE_BODY"
                    + "where OE.OE_NO = OE_Body.OE_NO and OE_BODY.body =" + body);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }

            p = conn.prepareStatement("select OEM.OEM_NO,OEM.OEM_NAME,OEM.C_PRICE,OEM.D_PRICE,OEM.G_PRICE,OEM.R_PRICE "
                    + "from OEM, OE_BODY"
                    + "where OEM.OE_NO = OE_Body.OE_NO and OE_BODY.Body =" + body);
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void selectPartCompany(String comp) {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE.OE_NO,OE.OE_NAME,OE.company,OE.C_PRICE,OE.D_PRICE,OE.G_PRICE,OE.R_PRICE "
                    + "from OE"
                    + "where OE.company = " + comp);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getDouble(7));
            }

            p = conn.prepareStatement("select OEM.OEM_NO,OEM.OEM_NAME,company,OEM.C_PRICE,OEM.D_PRICE,OEM.G_PRICE,OEM.R_PRICE "
                    + "from OEM"
                    + "where OEM.company = " + comp);
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getDouble(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String toPrice(String in) {
        String price = "";
        String[] a = new String[2];
        boolean isDot = false;
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '.') {
                a[0] = in.substring(0, i);
                a[1] = in.substring(i + 1);
                isDot = true;
            }
        }
        if (!isDot) {
            a[0] = in;
        }
        a[0] = "" + Integer.parseInt(a[0]);
        while (a[0].length() != 0) {
            int mod = a[0].length() % 3;
            if (mod == 0) {
                mod = 3;
            }
            price = price + a[0].substring(0, mod);
            if (a[0].length() > 3) {
                price += ",";
            }
            a[0] = a[0].substring(mod);
        }
        if (a[1] == null) {
            a[1] = "00";
        }
        return price + "." + a[1].substring(0,2);
    }

}