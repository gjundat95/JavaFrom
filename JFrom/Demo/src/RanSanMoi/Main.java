package RanSanMoi;

import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame{

    /**
     * Creates new form MainFrame
     */
    public Main() {
        initComponents();
        rbTrungbinh.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnBatdau = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbBando = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        rbCham = new javax.swing.JRadioButton();
        rbTrungbinh = new javax.swing.JRadioButton();
        rbNhanh = new javax.swing.JRadioButton();
        btnHuongdan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        btnBatdau.setText("Bắt đầu");
        btnBatdau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatdauActionPerformed(evt);
            }
        });

        jLabel1.setText("Bản đồ:");

        cmbBando.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bản đồ 1 (Dễ)", "Bản đồ 2 (Trung bình)", "Bản đồ 3 (Khó)" }));
        cmbBando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBandoActionPerformed(evt);
            }
        });

        jLabel2.setText("Mức:");

        buttonGroup1.add(rbCham);
        rbCham.setText("Chậm");
        rbCham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbChamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbTrungbinh);
        rbTrungbinh.setText("Trung bình");
        rbTrungbinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTrungbinhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNhanh);
        rbNhanh.setText("Nhanh");
        rbNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNhanhActionPerformed(evt);
            }
        });

        btnHuongdan.setText("Hướng dẫn");
        btnHuongdan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongdanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuongdan))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbTrungbinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbCham, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addComponent(cmbBando, 0, 130, Short.MAX_VALUE)))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbBando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbCham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbTrungbinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNhanh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuongdan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatdauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatdauActionPerformed
        MyThread mt = new MyThread();
        mt.setBando(cmbBando.getSelectedIndex());
        int tocdo = 200;
        if(rbCham.isSelected()){
            tocdo = 200;
        }
        else if(rbTrungbinh.isSelected()){
            tocdo = 150;
        }
        else if(rbNhanh.isSelected()){
            tocdo = 70;
        }
        mt.setTocdo(tocdo);
        Thread t = new Thread(mt);
        t.start();
    }//GEN-LAST:event_btnBatdauActionPerformed

    private void cmbBandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBandoActionPerformed

    private void rbNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNhanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNhanhActionPerformed

    private void rbChamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbChamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbChamActionPerformed

    private void rbTrungbinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTrungbinhActionPerformed
    }//GEN-LAST:event_rbTrungbinhActionPerformed

    private void btnHuongdanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongdanActionPerformed
        JOptionPane.showMessageDialog(null, "Sử dụng các phím mũi tên lên, xuống, trái, phải để điều khiển rắn sao cho rắn ăn được mồi");
    }//GEN-LAST:event_btnHuongdanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main mf = new Main();
                mf.setVisible(true);
                mf.setLocation(500, 200);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatdau;
    private javax.swing.JButton btnHuongdan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbBando;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbCham;
    private javax.swing.JRadioButton rbNhanh;
    private javax.swing.JRadioButton rbTrungbinh;
    // End of variables declaration//GEN-END:variables

}
