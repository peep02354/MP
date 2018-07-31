package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static mp.TestUI.DBURL;

public class SelectMethod {

    public static DefaultTableModel clearTable() {
        TestUI.select_table.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) TestUI.select_table.getModel();
        TestUI.select_table.setDefaultEditor(Object.class, null);
        model.addColumn("OE/OEM No.");
        model.addColumn("OE/OEM Name");
        model.addColumn("Capital Price");
        model.addColumn("Retail Price");
        return model;
    }

    public static void setData(DefaultTableModel m, int row, ResultSet rs) throws SQLException {
        m.setValueAt(rs.getString(1), row, 0);
        m.setValueAt(rs.getString(2), row, 1);
        m.setValueAt(rs.getString(3), row, 2);
        m.setValueAt(rs.getString(4), row, 3);
    }

    public static void all() {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement p = conn.prepareStatement("select OE_NO,OE_NAME,C_PRICE,R_PRICE from OE");
            ResultSet rs = p.executeQuery();
            DefaultTableModel m = clearTable();
            int row = 0;
            while (rs.next() && rs != null) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
                //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" ");
            }
            p = conn.prepareStatement("select OEM_NO,OEM_NAME,C_PRICE,R_PRICE from OEM");
            rs = p.executeQuery();
            while (rs.next()) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
                //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" ");
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void selectPart() {
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            String where = "";
            String realWhere = "";
            String from = "from oe";
            if(!TestUI.oe_no.getText().equals("")) where += " and oe.oe_no = " + TestUI.oe_no.getText();
            if(!TestUI.body.getText().equals("")){
                where += " and body = " + TestUI.body.getText()+" and oe.oe_no = oe_body.oe_no";
                from += ", oe_body";
            }
            if(!TestUI.engine.getText().equals("")){
                where += " and engine = " + TestUI.engine.getText()+" and oe.oe_no = oe_engine.oe_no";
                from += ", oe_engine";
            }
            if(!TestUI.model.getText().equals("")){
                where += " and model = " + TestUI.model.getText()+" and oe.oe_no = oe_model.oe_no";
                from += ", oe_model";
            }
            if(!TestUI.part_name.getText().equals("")) where += " and oe.oe_name = " + TestUI.part_name.getText();
            if(!TestUI.company.getText().equals("")) where += " and oe.company = " + TestUI.company.getText();
            if(!where.equals("")) realWhere = "where true " + where;
            String sql = "select OE_NO,OE_NAME,C_PRICE,R_PRICE "+from+" "+realWhere;
            System.out.println(TestUI.oe_no.getText());
            System.out.println(TestUI.oe_no.getText().isEmpty());
            System.out.println(TestUI.oe_no.getText()==null);
            System.out.println(TestUI.oe_no.getText().equals(""));
            System.out.println(sql);
            /*PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            DefaultTableModel m = clearTable();
            int row = 0;
            while (rs.next()) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
            }
            String sql2 = "select oem.OEM_NO,oem.OEM_NAME,oem.C_PRICE,oem.R_PRICE from OEM,OE ";
            p = conn.prepareStatement(sql2);
            rs = p.executeQuery();
            while (rs.next()) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
            }
            rs.close();*/
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
