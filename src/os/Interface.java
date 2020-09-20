/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import static java.awt.Color.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo
 */
public class Interface extends JFrame implements ActionListener {

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    JTextField text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11;
    JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, jbutton1, jbutton2;
    JLabel label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, lable2_1, lable2_2;
    JRadioButton radiob1, radiob2, radiob3;
    JPanel panel1, panel2;
    JTabbedPane tabbedpane;

    public Interface() {

        setSize(1000, 900);
        setLocation((width - 1000) / 2, (height - 900) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel1 = new JPanel();//进程创建
        panel1.setLayout(null);
        panel2 = new JPanel();//结果预览

        tabbedpane = new JTabbedPane();
        tabbedpane.addTab("进程创建", null, panel1, "进程创建");
        tabbedpane.addTab("结果预览", null, panel2, "结果预览");
        tabbedpane.setFont(new Font("黑体", Font.BOLD, 20));
        add(tabbedpane);

        text1 = new JTextField();
        text1.setBounds(250, 50, 100, 30);
        panel1.add(text1);
        text2 = new JTextField();
        text2.setBounds(550, 50, 100, 30);
        panel1.add(text2);
        text3 = new JTextField();
        text3.setBounds(60, 140, 100, 30);
        panel1.add(text3);
        text4 = new JTextField();
        text4.setBounds(190, 140, 100, 30);
        panel1.add(text4);
        text5 = new JTextField();
        text5.setBounds(320, 140, 100, 30);
        panel1.add(text5);
        text6 = new JTextField();
        text6.setBounds(450, 140, 100, 30);
        panel1.add(text6);
        text7 = new JTextField();
        text7.setBounds(600, 140, 100, 30);
        panel1.add(text7);

        Font font1 = new Font("宋体", Font.BOLD, 18);
        Font font2 = new Font("黑体", Font.BOLD, 16);
        label0 = new JLabel("初始设置");
        label0.setFont(font2);
        label0.setForeground(gray);
        label0.setBounds(20, 20, 100, 30);
        panel1.add(label0);
        label1 = new JLabel("内存");
        label1.setFont(font1);
        label1.setBounds(200, 50, 50, 30);
        panel1.add(label1);
        label2 = new JLabel("MB");
        label2.setFont(font1);
        label2.setBounds(360, 50, 50, 30);
        panel1.add(label2);
        label3 = new JLabel("打印机");
        label3.setFont(font1);
        label3.setBounds(480, 50, 60, 30);
        panel1.add(label3);
        label4 = new JLabel("台");
        label4.setFont(font1);
        label4.setBounds(660, 50, 50, 30);
        panel1.add(label4);
        label5 = new JLabel("新建进程");
        label5.setFont(font2);
        label5.setForeground(gray);
        label5.setBounds(20, 80, 100, 30);
        panel1.add(label5);
        label6 = new JLabel("进程名称");
        label6.setFont(font1);
        label6.setBounds(60, 110, 100, 30);
        panel1.add(label6);
        label7 = new JLabel("提交时间");
        label7.setFont(font1);
        label7.setBounds(190, 110, 100, 30);
        panel1.add(label7);
        label8 = new JLabel("估计执行");
        label8.setFont(font1);
        label8.setBounds(320, 110, 100, 30);
        panel1.add(label8);
        label9 = new JLabel("优先级");
        label9.setFont(font1);
        label9.setBounds(450, 110, 120, 30);
        panel1.add(label9);
        label10 = new JLabel("内存请求大小");
        label10.setFont(font1);
        label10.setBounds(600, 110, 150, 30);
        panel1.add(label10);
        label11 = new JLabel("请选择模拟方法：");
        label11.setFont(new Font("宋体", Font.BOLD, 21));
        label11.setBounds(60, 190, 200, 30);
        panel1.add(label11);
        label12 = new JLabel("当前占用CPU的进程");
        label12.setFont(font2);
        label12.setForeground(gray);
        label12.setBounds(20, 230, 200, 30);
        panel1.add(label12);
        label13 = new JLabel("就绪队列");
        label13.setFont(font2);
        label13.setForeground(gray);
        label13.setBounds(20, 350, 200, 30);
        panel1.add(label13);
        label14 = new JLabel("等待队列");
        label14.setFont(font2);
        label14.setForeground(gray);
        label14.setBounds(20, 650, 200, 30);
        panel1.add(label14);
        label15 = new JLabel("XXX");
        label15.setFont(font1);
        label15.setBounds(60, 390, 150, 30);
        panel1.add(label15);

        JRadioButton radiob1 = new JRadioButton("先来先服务调度算法");
        radiob1.setBounds(240, 195, 150, 20);
        panel1.add(radiob1);
        JRadioButton radiob2 = new JRadioButton("非抢占式优先级调度算法");
        radiob2.setBounds(400, 195, 200, 20);
        panel1.add(radiob2);
        JRadioButton radiob3 = new JRadioButton("抢占式优先级调度算法");
        radiob3.setBounds(600, 195, 200, 20);
        panel1.add(radiob3);
        //单选
        ButtonGroup group = new ButtonGroup();
        group.add(radiob1);
        group.add(radiob2);
        group.add(radiob3);

        button1 = new JButton("初始设置");
        button1.setBounds(730, 50, 120, 30);
        panel1.add(button1);
        button2 = new JButton("新建进程");
        button2.setBounds(800, 125, 120, 40);
        panel1.add(button2);
        button3 = new JButton("开始模拟");
        button3.setBounds(800, 185, 120, 40);
        panel1.add(button3);
        button4 = new JButton("阻塞");
        button4.setBounds(840, 260, 90, 40);
        panel1.add(button4);
        button5 = new JButton("暂停");
        button5.setBounds(740, 310, 90, 25);
        panel1.add(button5);
        button6 = new JButton("继续");
        button6.setBounds(840, 310, 90, 25);
        panel1.add(button6);
        button7 = new JButton("唤醒");
        button7.setBounds(840, 693, 90, 40);
        panel1.add(button7);
        button8 = new JButton("退出");
        button8.setBounds(830, 770, 100, 40);
        panel1.add(button8);
        button9 = new JButton("重置");
        button9.setBounds(700, 770, 100, 40);
        panel1.add(button9);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);

        Vector Vname1 = new Vector();
        Vector Vdate1 = new Vector();
        Vector row1 = new Vector();
        Vname1.add("进程名");
        Vname1.add("提交时间");
        Vname1.add("估计执行");
        Vname1.add("占用内存");
        Vname1.add("打印机数");
        Vname1.add("还需执行时间");
        DefaultTableModel model1 = new DefaultTableModel(Vdate1, Vname1);
        model1.setDataVector(Vdate1, Vname1);
        JTable table1 = new JTable(model1);
        JScrollPane s1 = new JScrollPane(table1);
        s1.setSize(700, 40);
        s1.setLocation(120, 260);
        panel1.add(s1);

        Vector Vname2 = new Vector();
        Vector Vdate2 = new Vector();
        Vector row2 = new Vector();
        Vname2.add("进程名称");
        Vname2.add("提交时间");
        Vname2.add("估计执行时间");
        Vname2.add("占用内存大小");
        Vname2.add("占用打印机数量");
        Vname2.add("还需执行时间");
        DefaultTableModel model2 = new DefaultTableModel(Vdate2, Vname2);
        model2.setDataVector(Vdate2, Vname2);
        JTable table2 = new JTable(model2);
        JScrollPane s2 = new JScrollPane(table2);
        s2.setSize(700, 70);
        s2.setLocation(120, 380);
        panel1.add(s2);

        Vector Vname3 = new Vector();
        Vector Vdate3 = new Vector();
        Vector row3 = new Vector();
        Vname3.add("进程名称");
        Vname3.add("提交时间");
        Vname3.add("估计执行时间");
        Vname3.add("占用内存大小");
        Vname3.add("占用打印机数量");
        Vname3.add("还需执行时间");
        DefaultTableModel model3 = new DefaultTableModel(Vdate3, Vname3);
        model3.setDataVector(Vdate3, Vname3);
        JTable table3 = new JTable(model3);
        JScrollPane s3 = new JScrollPane(table3);
        s3.setSize(700, 70);
        s3.setLocation(120, 680);
        panel1.add(s3);

        //结果预览  
        Font f = new Font("黑体", Font.BOLD, 22);
        lable2_1 = new JLabel("输入进程队列情况：");
        lable2_1.setFont(f);
        lable2_1.setBounds(200, 50, 80, 80);
        panel2.add(lable2_1);

        String[] name2_1 = {"进程名称", "提交时间", "估计执行时间", "内存请求大小", "打印机请求数量"};
        String[][] data1 = new String[50][5];
        JTable jt1 = new JTable(data1, name2_1);
        JScrollPane jsp1 = new JScrollPane(jt1);
        jsp1.setPreferredSize(new Dimension(950, 200));
        panel2.add(jsp1);

        lable2_2 = new JLabel("执行后进程队列的情况：");
        lable2_2.setFont(f);
        lable2_2.setBounds(400, 50, 80, 80);
        panel2.add(lable2_2);

        String[] name2_2 = {"进程名称", "提交时间", "执行时间", "内存请求大小", "打印机请求数量"};
        String[][] data2 = new String[50][5];
        JTable jt2 = new JTable(data2, name2_2);
        JScrollPane jsp2 = new JScrollPane(jt2);
        jsp2.setPreferredSize(new Dimension(950, 200));
        panel2.add(jsp2);

        jbutton1 = new JButton("重置");
        jbutton1.setFont(f);
        jbutton1.setPreferredSize(new Dimension(80, 50));
        panel2.add(jbutton1);
        jbutton2 = new JButton("退出");
        jbutton2.setFont(f);
        jbutton2.setPreferredSize(new Dimension(80, 50));
        panel2.add(jbutton2);

        jbutton1.addActionListener(this);
        jbutton2.addActionListener(this);

        this.setVisible(true);
    }

//    public void paint(Graphics g){
//        super.paint(g);
//        g.drawLine(120,100, 1000,1000);
//    }
    public static void main(String[] args) {
        // TODO code application logic here
        Interface in = new Interface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("退出")) {
            exit(0);
        } else if (e.getActionCommand().equals("重置")) {

        } else if (e.getActionCommand().equals("初始设置")) {

        } else if (e.getActionCommand().equals("新建进程")) {
            String name;
            int arrive_time; 
            int need_time;
            int priority;
            name = text3.getText();
            arrive_time = Integer.parseInt(text4.getText());
            need_time = Integer.parseInt(text5.getText());
            priority = Integer.parseInt(text6.getText());
            
        } else if (e.getActionCommand().equals("开始模拟")) {

        } else if (e.getActionCommand().equals("阻塞")) {

        } else if (e.getActionCommand().equals("暂停")) {

        } else if (e.getActionCommand().equals("就绪")) {

        } else if (e.getActionCommand().equals("唤醒")) {

        }
    }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
