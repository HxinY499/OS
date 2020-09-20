/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Login extends JFrame{
    JButton jb1=new JButton("首次适应算法");
    JButton jb2=new JButton("最佳适应算法");
    JButton jb3=new JButton("最坏适应算法");
    public void init(){
        this.setTitle("动态分区存储器管理方案的模拟系统");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setLayout(null);
        jb1.setBounds(300, 100, 150, 60);
        jb2.setBounds(300, 300, 150, 60);
        jb3.setBounds(300, 500, 150, 60);
        jb1.setCursor(new Cursor(12));
        jb2.setCursor(new Cursor(12));
        jb3.setCursor(new Cursor(12));
        Font font = new Font("宋体",Font.BOLD,18);
        jb1.setFont(font);
        jb2.setFont(font);
        jb3.setFont(font);
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        
        bListener b=new bListener(this);
	jb1.addActionListener(b);
        jb2.addActionListener(b);
        jb3.addActionListener(b);
        this.setVisible(true);
    }
    public static void main(String args[]) {
        Login l = new Login();
        l.init();
    }
}
