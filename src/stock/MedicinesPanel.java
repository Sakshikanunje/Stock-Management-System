package stock;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class MedicinesPanel extends javax.swing.JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String productName,supplierName,productCategory,quantity,price_per_unit;
	String tableClicked = "";
    
    public MedicinesPanel() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
        updateTable();
    }
    
    private void updateTable(){
        try{
          String sql = "SELECT  product_name as 'Product Name',supplier_name as 'Supplier Name',product_category as 'Product Category',quantity as 'Quantity',price_per_unit as 'Price Per Unit' FROM products";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            medicineTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private JButton backBtn;
    private JButton delMedBtn;
    private JButton insertMedBtn;
    private JScrollPane jScrollPane1;
    private JTable medicineTable;
    private JButton updateMedBtn;
    
    private void initComponents() {
    	JFrame frame = new JFrame("Medicine Stock");
        insertMedBtn = new JButton();
        updateMedBtn = new JButton();
        delMedBtn = new JButton();
        backBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        medicineTable = new JTable();
        setResizable(false);
        
        insertMedBtn.setBounds(40, 100, 200, 50);
        updateMedBtn.setBounds(40, 200, 200, 50);
        delMedBtn.setBounds(40, 300, 200, 50);
        backBtn.setBounds(40, 600, 200, 50);
        jScrollPane1.setBounds(300, 100, 800, 550);
        
        frame.add(insertMedBtn);
        frame.add(updateMedBtn);
        frame.add(delMedBtn);
        frame.add(backBtn);
        frame.add(jScrollPane1);
        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(350, 125, 1200, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        insertMedBtn.setFont(new Font("Calibri", 0, 18)); 
        insertMedBtn.setText("Insert Medicines");
        insertMedBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                insertMedBtnActionPerformed(evt);
            }
        });

        updateMedBtn.setFont(new Font("Calibri", 0, 18)); 
        updateMedBtn.setText("Update Medicines");
        updateMedBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateMedBtnActionPerformed(evt);
            }
        });

        delMedBtn.setFont(new Font("Calibri", 0, 18)); 
        delMedBtn.setText("Delete Medicines");
        delMedBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                delMedBtnActionPerformed(evt);
            }
        });

        backBtn.setFont(new Font("Calibri", 0, 18)); 
        backBtn.setText("Back");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
       
        medicineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        medicineTable.getTableHeader().setReorderingAllowed(false);
        medicineTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                medicineTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(medicineTable);   
    }
    
    private void insertMedBtnActionPerformed(ActionEvent evt) {
       InsertMedicines mp = new InsertMedicines();
    }

    private void updateMedBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_updateMedBtnActionPerformed
            if ("".equals(tableClicked)){
                JOptionPane.showMessageDialog(null, "Please select a medicine to update!");
            }else{
                
                UpdateMedicines up = new UpdateMedicines();
                up.productName = this.productName;
                up.productNameTxt.setText(productName);
                up.supplierNameTxt.setText(supplierName);
                up.productCategoryTxt.setText(productCategory);
                up.productQuantityTxt.setText(quantity);
                up.productPriceTxt.setText(price_per_unit);
                up.setTitle("Update Medicine Details");
            }
    }
    
    private void delMedBtnActionPerformed(ActionEvent evt) {
         if ("".equals(tableClicked)){
                JOptionPane.showMessageDialog(null, "Please select a medicine to delete!");
         }else{
             int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this medicine?","Delete",JOptionPane.YES_NO_OPTION);
             if (des==0){
                   try{
                    String sql = "DELETE FROM products WHERE product_name='"+tableClicked+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    pst.close();
                    rs.close();
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Medicine has been deleted!");
               }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                }
             }  
         }
    }

    private void backBtnActionPerformed(ActionEvent evt) {
      MainPanel mp = new MainPanel();
    }

    private void formMouseClicked(MouseEvent evt) {
  
    }

    private void medicineTableMouseClicked(MouseEvent evt) {
         try{
           int row = medicineTable.getSelectedRow();
            tableClicked = (medicineTable.getModel().getValueAt(row, 0).toString());
           String sql = "SELECT * FROM products WHERE product_name =  '"+ tableClicked +"'";
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           if(rs.next()){
               productName = rs.getString("product_name");
               supplierName = rs.getString("supplier_name");
               productCategory = rs.getString("product_category");
               quantity = rs.getString("quantity");
               price_per_unit = rs.getString("price_per_unit");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
         
    }
}
