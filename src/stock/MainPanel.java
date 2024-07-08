package stock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JFrame{
	
	public MainPanel() {	
        initComponents();
       // this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
     }
	
     private void initComponents() {
    	JFrame next = new JFrame(" Menu Page ");
    	 
    	ImageIcon icon = new ImageIcon(getClass().getResource("/stock/123kk.png"));
		JLabel logo = new JLabel(icon);
 		JLabel l1 = new JLabel(" Welcome to HealthCare Medical Store");
 		
 		JButton b1 = new JButton();
 		JButton b2 = new JButton();
 		JButton b3 = new JButton();
 		
 		b1.setIcon(new ImageIcon(getClass().getResource("/stock/Items_Stock.png")));
 		b2.setIcon(new ImageIcon(getClass().getResource("/stock/Supplier.png")));
 		b3.setIcon(new ImageIcon(getClass().getResource("/stock/Issue_Items.png")));

 		logo.setBounds(0, 0, 150, 120);
		l1.setBounds(175, 0, 1050, 115);
		l1.setFont(new Font("Serif", Font.BOLD, 48));
		l1.setForeground(Color.white);
		l1.setOpaque(true);
		l1.setBackground(Color.darkGray);
		b1.setBackground(Color.white);
		b2.setBackground(Color.white);
		b3.setBackground(Color.white);
		b1.setBounds(40, 150, 340, 270);
		b2.setBounds(430, 150, 340, 270);
		b3.setBounds(810, 150, 340, 270);
			
		next.add(logo);
		next.add(l1);
		next.add(b1);
		next.add(b2);
		next.add(b3);
			
		next.setLayout(null);
		next.setVisible(true);
		next.setBounds(350, 125, 1200, 550);
		next.setResizable(false);
	
		b1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	               medicineBtnActionPerformed(evt);
	        }
	    });
		
		b3.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	               issueItemBtnActionPerformed(evt);
	        }
		});
			
		b2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	               supplierBtnActionPerformed(evt);
	        }
		});
     }
     
     private void medicineBtnActionPerformed(ActionEvent evt) {
         MedicinesPanel mp = new MedicinesPanel();
     }
     
     private void supplierBtnActionPerformed(ActionEvent evt) {
         SupplierPanel mp = new SupplierPanel();
     }
     
     private void issueItemBtnActionPerformed(ActionEvent evt) {
    	 IssueMedicine mp = new IssueMedicine();
     }
  }
