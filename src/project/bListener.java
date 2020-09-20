/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class bListener implements ActionListener {

    static public Login l;
    public secondUI s = new secondUI();

    public bListener(Login l) {
        this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("首次适应算法")) {
            s.memoryAllocationAlgorithm = 1;
            JOptionPane jo1 = new JOptionPane("输入");
            try {
                s.memory = Integer.parseInt(jo1.showInputDialog(null, "请输内存初始大小："));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "请输入正确的内存初始大小");
                return;
            }
            l.dispose();
            s.init();
        } else if (e.getActionCommand().equals("最佳适应算法")) {
            s.memoryAllocationAlgorithm = 2;
            JOptionPane jo1 = new JOptionPane("输入");
            try {
                s.memory = Integer.parseInt(jo1.showInputDialog(null, "请输内存初始大小："));
            }catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "请输入正确的内存初始大小");
                return;
            }
            l.dispose();
            s.init();
        } else if (e.getActionCommand().equals("最坏适应算法")) {
            s.memoryAllocationAlgorithm = 3;
            JOptionPane jo1 = new JOptionPane("输入");
            try {
                s.memory = Integer.parseInt(jo1.showInputDialog(null, "请输内存初始大小："));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "请输入正确的内存初始大小");
                return;
            }
            l.dispose();
            s.init();
        }
    }
}
