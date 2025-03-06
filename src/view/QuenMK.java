/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import entity.RandomSNN;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import repository.taiKhoanRepository;

/**
 *
 * @author default
 */
public class QuenMK extends javax.swing.JFrame {

    public QuenMK() {
        initComponents();
        LamMoi();
    }

    public void hienThiGoiY() {
        txtEmail.setText("Email...");
        txtEmail.setForeground(Color.red);
        txtCacha.setText("Capcha...");
        txtCacha.setForeground(Color.red);
        txtMaCapCha.setEditable(false);
        txtMaCapCha.requestFocus();
    }

    public boolean kiemTraLoi() {
        if (txtEmail.getText().equalsIgnoreCase("email....")) {
            txtEmail.requestFocus();
            JOptionPane.showMessageDialog(this, "Không Được Để Trống Email");
            return false;
        }
        if (txtCacha.getText().equalsIgnoreCase("Capcha...")) {
            txtCacha.requestFocus();
            JOptionPane.showMessageDialog(this, "Không Được Để Trống Email");
            return false;
        }
        if (!txtMaCapCha.getText().equalsIgnoreCase(txtMaCapCha.getText())) {
            txtMaCapCha.requestFocus();
            JOptionPane.showMessageDialog(this, "Mã CapCha Không Khớp");
        }
        return true;
    }

    public void maCapCha() {
        String capcha = new RandomSNN().soNgauNhienString(5);
        txtMaCapCha.setText(capcha);
        txtMaCapCha.setFont(new Font(capcha, Font.HANGING_BASELINE, 14));
    }

    public void LamMoi() {
        hienThiGoiY();
        maCapCha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCacha = new javax.swing.JTextField();
        btnLogi = new javax.swing.JButton();
        txtMaCapCha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");

        jPanel2.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(54, 172, 225));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel3.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FiveK Group");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("anhem2024@gmail.com");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/z5614029457732_7a082dc5e00a2cc7db3597abacded4a5-removebg-preview.png"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(97, 97, 97)
                .addComponent(jLabel8)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 0, 390, 390);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(330, 500));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(54, 172, 225));
        jLabel2.setText("Quên Mật Khẩu");

        jLabel4.setText("Email");

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        jLabel5.setText("Mã");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(330, 60));

        btnLogin.setBackground(new java.awt.Color(54, 172, 225));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Trở Về");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setText("I don't have an account");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnLogin))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        txtCacha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCachaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCachaFocusLost(evt);
            }
        });

        btnLogi.setBackground(new java.awt.Color(54, 172, 225));
        btnLogi.setForeground(new java.awt.Color(255, 255, 255));
        btnLogi.setText("Gửi");
        btnLogi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtCacha)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtMaCapCha, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnLogi, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCacha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaCapCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnLogi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(390, 0, 310, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login loginFrame = new login();
        loginFrame.setVisible(true);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLogiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogiActionPerformed
        // TODO add your handling code here:
        if (kiemTraLoi()) {
            taiKhoanRepository g = new taiKhoanRepository();
            try {
                if (g.checkUser(txtEmail.getText())) {
                    g.Update(txtEmail.getText());
                    LamMoi();
                    return;
                }
                JOptionPane.showMessageDialog(this, "Gửi yêu cầu thất bại");
                LamMoi();
                return;
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnLogiActionPerformed

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
        if (txtEmail.getText().equalsIgnoreCase("email....")) {
            txtEmail.setText("");
            txtEmail.setForeground(Color.red);
        }


    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        // TODO add your handling code here:
        if (txtEmail.getText().equalsIgnoreCase("")) {
            txtEmail.setText("email....");
            txtEmail.setForeground(Color.red);
        }

    }//GEN-LAST:event_txtEmailFocusLost

    private void txtCachaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCachaFocusGained
        // TODO add your handling code here:
        if (txtCacha.getText().equalsIgnoreCase("Capcha...")) {
            txtCacha.setText("");
            txtCacha.setForeground(Color.black);
        }

    }//GEN-LAST:event_txtCachaFocusGained

    private void txtCachaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCachaFocusLost
        // TODO add your handling code here:
        if (txtCacha.getText().equalsIgnoreCase("")) {
            txtCacha.setText("Capcha...");
            txtCacha.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCachaFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogi;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtCacha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaCapCha;
    // End of variables declaration//GEN-END:variables

}
