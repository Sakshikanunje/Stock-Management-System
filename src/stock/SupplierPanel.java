package stock;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

public class SupplierPanel extends javax.swing.JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String id,name,address,email,mobileNo;
	String tableClicked = "";
    
    public SupplierPanel() {
        initComponents();
        conn = ConnectionToDatabase.connectToDb();
        updateTable();
    }
    private void updateTable(){
   
        try{
          String sql = "SELECT  id as 'Supplier ID',name as 'Supplier Name',address as 'Supplier Address',email as 'Supplier Email-ID',mobileNo as 'Supplier Mobile No' FROM supplier";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            supplierTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private JButton backBtn;
    private JButton delSuppBtn;
    private JButton inserSuppBtn;
    private JScrollPane jScrollPane1;
    private JTable supplierTable;
    private JButton updateSuppBtn;
    
    private void initComponents() {
    	JFrame frame = new JFrame("Third Frame");
        inserSuppBtn = new JButton();
        updateSuppBtn = new JButton();
        delSuppBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        supplierTable = new JTable();
        backBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        inserSuppBtn.setBounds(40, 100, 200, 50);
        updateSuppBtn.setBounds(40, 200, 200, 50);
        delSuppBtn.setBounds(40, 300, 200, 50);
        backBtn.setBounds(40, 600, 200, 50);
        jScrollPane1.setBounds(300, 100, 800, 550);
        
        inserSuppBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        inserSuppBtn.setText("Insert Supplier");
        updateSuppBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        updateSuppBtn.setText("Update Supplier");
        delSuppBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        delSuppBtn.setText("Delete Supplier");
        backBtn.setFont(new java.awt.Font("Calibri", 0, 20)); 
        backBtn.setText("Back");
        
        frame.add(inserSuppBtn);
        frame.add(updateSuppBtn);
        frame.add(delSuppBtn);
        frame.add(backBtn);
        frame.add(jScrollPane1);
        
        frame.setLayout(null);
        frame.setTitle(" Supplier ");
        frame.setVisible(true);
        frame.setBounds(350, 125, 1200, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        inserSuppBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inserSuppBtnActionPerformed(evt);
            }
        });

        updateSuppBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateSuppBtnActionPerformed(evt);
            }
        });

        delSuppBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                delSuppBtnActionPerformed(evt);
            }
        });

        supplierTable.setModel(new DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        
        supplierTable.getTableHeader().setReorderingAllowed(false);
        supplierTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(supplierTable);
       
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
    } 

    private void inserSuppBtnActionPerformed(ActionEvent evt) {
       InsertSupplier mp = new InsertSupplier();
    }

    private void updateSuppBtnActionPerformed(ActionEvent evt) {
            if ("".equals(tableClicked)){
                JOptionPane.showMessageDialog(null, "Please select a Supplier to update!");
            }else{
            	
                UpdateSupplier up = new UpdateSupplier();
                up.id = this.id;
                up.supplierIdTxt.setText(id);
                up.supplierNameTxt.setText(name);
                up.supplieraddTxt.setText(address);
                up.suppliermailTxt.setText(email);
                up.supplierMobTxt.setText(mobileNo);
                up.setTitle("Update Medicine Details");
            }
    }

    private void delSuppBtnActionPerformed(ActionEvent evt) {
         if ("".equals(tableClicked)){
                JOptionPane.showMessageDialog(null, "Please select a product to delete!");
         }else{
             int des = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Supplier?","Delete",JOptionPane.YES_NO_OPTION);
             if (des==0){
                   try{
                    String sql = "DELETE FROM supplier WHERE id='"+tableClicked+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    pst.close();
                    rs.close();
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Supplier has been deleted!");
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

    private void supplierTableMouseClicked(MouseEvent evt) {
         try{
           int row = supplierTable.getSelectedRow();
            tableClicked = (supplierTable.getModel().getValueAt(row, 0).toString());
           String sql = "SELECT * FROM supplier WHERE id =  '"+ tableClicked +"'";
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           if(rs.next()){
               id = rs.getString("id");
               name = rs.getString("name");
               address = rs.getString("address");
               email = rs.getString("email");
               mobileNo = rs.getString("mobileNo");           
           }
         
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
         
    }
}
