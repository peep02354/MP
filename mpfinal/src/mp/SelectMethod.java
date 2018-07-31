package mp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import static mp.TestUI.DBURL;

public class SelectMethod {

    public static DefaultTableModel clearTable() {
        TestUI.select_table.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) TestUI.select_table.getModel();
        //model.setRowCount(1);
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
            DefaultTableModel m = clearTable();
            String where = "";
            String realWhere = "";
            String from = "from oe";
            ArrayList<String> arr = new ArrayList<String>();
            if (!TestUI.oe_no.getText().equals("")) {
                //where += " and oe.oe_no = " + TestUI.oe_no.getText();
                where += " and oe.oe_no = ?";
                arr.add(TestUI.oe_no.getText());
            }
            if (!TestUI.part_name.getText().equals("")) {
                //where += " and oe.oe_name = " + TestUI.part_name.getText();
                where += " and oe.oe_name = ?";
                arr.add(TestUI.part_name.getText());
            }
            if (!TestUI.company.getText().equals("")) {
                //where += " and oe.company = " + TestUI.company.getText();
                where += " and oe.company = ?";
                arr.add(TestUI.company.getText());
            }
            if (!TestUI.body.getText().equals("")) {
                //where += " and body = " + TestUI.body.getText() + " and oe.oe_no = oe_body.oe_no";
                where += " and body = ? and oe.oe_no = oe_body.oe_no";
                from += ", oe_body";
                arr.add(TestUI.body.getText());
            }
            if (!TestUI.engine.getText().equals("")) {
                //where += " and engine = " + TestUI.engine.getText() + " and oe.oe_no = oe_engine.oe_no";
                where += " and engine = ? and oe.oe_no = oe_engine.oe_no";
                from += ", oe_engine";
                arr.add(TestUI.engine.getText());
            }
            if (!TestUI.model.getText().equals("")) {
                //where += " and model = " + TestUI.model.getText() + " and oe.oe_no = oe_model.oe_no";
                where += " and model = ? and oe.oe_no = oe_model.oe_no";
                from += ", oe_model";
                arr.add(TestUI.model.getText());
            }
            if (!where.equals("")) {
                realWhere = "where true" + where;
            }
            String sql = "select OE_NO,OE_NAME,C_PRICE,R_PRICE " + from + " " + realWhere;
            PreparedStatement p = conn.prepareStatement(sql);
            for(int i = 0; i < arr.size();i++){
                p.setString(i+1, arr.get(i));
            }
            ResultSet rs = p.executeQuery();
            int row = 0;
            while (rs.next()) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
            }
            
            arr = new ArrayList<>();
            where = "";
            realWhere = "";
            from = "from oem";
            if (!TestUI.oem_no.getText().equals("")){
                where += " and oem.oem_no = ?";
                arr.add(TestUI.oem_no.getText());
            }
            if (!TestUI.oe_no.getText().equals("")) {
                //where += " and oe.oe_no = " + TestUI.oe_no.getText();
                where += " and oem.oe_no = ?";
                arr.add(TestUI.oe_no.getText());
            }
            if (!TestUI.part_name.getText().equals("")) {
                //where += " and oem.oe_name = " + TestUI.part_name.getText();
                where += " and oem.oem_name = ?";
                arr.add(TestUI.part_name.getText());
            }
            if (!TestUI.company.getText().equals("")) {
                //where += " and oe.company = " + TestUI.company.getText();
                where += " and oem.company = ?";
                arr.add(TestUI.company.getText());
            }
            if (!TestUI.body.getText().equals("")) {
                //where += " and body = " + TestUI.body.getText() + " and oe.oe_no = oe_body.oe_no";
                where += " and body = ? and oem.oe_no = oe_body.oe_no";
                from += ", oe_body";
                arr.add(TestUI.body.getText());
            }
            if (!TestUI.engine.getText().equals("")) {
                //where += " and engine = " + TestUI.engine.getText() + " and oe.oe_no = oe_engine.oe_no";
                where += " and engine = ? and oem.oe_no = oe_engine.oe_no";
                from += ", oe_engine";
                arr.add(TestUI.engine.getText());
            }
            if (!TestUI.model.getText().equals("")) {
                //where += " and model = " + TestUI.model.getText() + " and oe.oe_no = oe_model.oe_no";
                where += " and model = ? and oem.oe_no = oe_model.oe_no";
                from += ", oe_model";
                arr.add(TestUI.model.getText());
            }
            if (!where.equals("")) {
                realWhere = "where true" + where;
            }
            String sql2 = "select oem.OEM_NO,oem.OEM_NAME,oem.C_PRICE,oem.R_PRICE " + from + " " + realWhere;
            p = conn.prepareStatement(sql2);
            for(int i = 0; i < arr.size();i++){
                p.setString(i+1, arr.get(i));
            }
            rs = p.executeQuery();
            while (rs.next()) {
                m.addRow(new Object[0]);
                setData(m, row++, rs);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
