/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;


public class secondUI extends JFrame implements ActionListener{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    JButton jbutton1, jbutton2, jbutton3,jbutton4, jbutton5, jbutton6,jbutton7,jbutton8;//定义按钮
    JLabel jLabel1, jLabel2,jLabel3,thread_label1,thread_label2,lab1; //定义标签
    JTextPane jTextPane1, jTextPane2, thread_text1,thread_text2;// 文本组件 
    static JTextArea pic_text;
    JScrollPane scrollPane = new JScrollPane();
    public static JTable jTable1,jTable2; //定义静态类型的标签
    JFrame set_frame,fr;
    JPanel cp,pel;
    final String[] table_title1 = {"编 号",  "起始地址（K）","长 度（K）", "占用进程名"}; //头标签的字符串
    final String[] table_title2 = {"编 号",  "起始地址（K）","长 度（K）", "状 态"};
    static Object[][] table_data1 = new Object[100][4]; //标签表的大小 已分配
    static Object[][] table_data2 = new Object[100][4]; //标签表的大小 未分配
    public static ArrayList<MemoryBlock> usedMemoryBlockList;//已分配内存块表
    public static ArrayList<MemoryBlock> unusedMemoryBlockList;//未分配内存块表
    public static int memory;//内存大小
    public int memoryAllocationAlgorithm=0;//内存分配算法。1--首次适应，2--最佳适应，3--最坏适应	
    boolean flag = false;//如果没有找到满足的内存
    String programName;
    int programSize;
    
    void paint() {
        setTitle("动态分区存储器管理方案的模拟系统");
        setSize(1000, 800); //调整窗口组件的大小  
        setLocation((width - 800) / 2, (height - 800) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); //清空布局管理器，之后添加组件
        setResizable(false); //程序窗口大小固定不变
        
        cp = new JPanel();
        add(cp);
        
        jLabel1 = new JLabel("已分配分区表：");
        jLabel1.setBounds(50, 30, 120, 30); //第一个编辑框
        add(jLabel1);
        
        jTable1 = new JTable(table_data1, table_title1);
        JScrollPane scrollPane1 = new JScrollPane(jTable1);
        scrollPane1.setBounds(50, 80, 550,200);
        add(scrollPane1);
        jTable1.setEnabled(false);
        
        jLabel2 = new JLabel("空闲分区表：");
        jLabel2.setBounds(50, 300, 120, 30); //第一个编辑框 内存的使用情况列表
        add(jLabel2);
        
        jTable2 = new JTable(table_data2, table_title2);
        JScrollPane scrollPane2 = new JScrollPane(jTable2);
        scrollPane2.setBounds(50, 350, 550,200);
        add(scrollPane2);
        jTable2.setEnabled(false);
        
        jLabel3 = new JLabel("图形化显示：");
        jLabel3.setBounds(650, 30, 120, 30); //第一个编辑框 内存的使用情况列表
        add(jLabel3);
        
        scrollPane.setBounds(650, 70, 300, 550);
        add(scrollPane);
        pic_text=new JTextArea();
        pic_text.setBounds(650, 70, 300, 550);
        scrollPane.setViewportView(pic_text);       
        
        jbutton1 = new JButton("创 建 进 程");
        add(jbutton1); //构建按钮
        jbutton1.addActionListener(this);// 添加指定的动作侦听器，以接收发自此按钮的动作事件
        jbutton1.setBounds(50, 600, 120, 30);//按钮的大小位置
        jbutton1.setCursor(new Cursor(12));//按钮上的光标
        
        jbutton2 = new JButton("撤 消 进 程");
        add(jbutton2);
        jbutton2.addActionListener(this);
        jbutton2.setBounds(200, 600, 120, 30);
        jbutton2.setCursor(new Cursor(12));
        //按钮
        jbutton3 = new JButton("退 出 程 序");
        add(jbutton3);
        jbutton3.addActionListener(this);
        jbutton3.setBounds(500, 600, 120, 30);
        jbutton3.setCursor(new Cursor(12)); //按钮

        jbutton8 = new JButton("重 新 选 择");
        add(jbutton8);
        jbutton8.addActionListener(this);
        jbutton8.setBounds(350, 600, 120, 30);
        jbutton8.setCursor(new Cursor(12));  
        
        setVisible(true); //显示组件
        set_table();//输出存储器初始情况
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); //程序关闭
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbutton1) {//当点击创建进程按钮响应以下程序
            createProgram();
        }
        else if(e.getSource () == jbutton2) {
            destroyProgram();
        } //点击撤销进程按钮摧毁进程
        else if(e.getSource () == jbutton3) {
            System.exit(0);
        } //点击按钮退出程序
        else if(e.getSource () == jbutton4) {
            programName=thread_text1.getText();//程序名
            try {
                programSize = Integer.parseInt(thread_text2.getText());//程序大小
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "进程大小请输入数字");
                return;
            }
            runAllocate(programName,programSize);
            set_frame.setVisible(false);
            set_table();
        }//创建确定
        else if(e.getSource () == jbutton5) {
            set_frame.setVisible(false);
        }//取消
        else if(e.getSource () == jbutton6) {
            String programName=thread_text1.getText();//程序名
            recover(programName);
            set_frame.setVisible(false);
            set_table();
        }//撤销确定
        else if(e.getSource () == jbutton7) {
            fr.setVisible(false);
        }//错误提示确定
        else if (e.getSource() == jbutton8) {
            usedMemoryBlockList.clear();
            unusedMemoryBlockList.clear();
            set_table();
            this.dispose();
            Login l = new Login();
            l.init();
        }//返回主菜单
    }
    public void init() {
        usedMemoryBlockList = new ArrayList<MemoryBlock>();
        unusedMemoryBlockList = new ArrayList<MemoryBlock>();
        unusedMemoryBlockList.add(new MemoryBlock("unused", 0, memory));
        paint();
    }
    public void createProgram() {
        set_frame = new JFrame("创建进程");
        set_frame.setSize(350, 270);
        set_frame.setLayout(null);
        set_frame.setLocationRelativeTo(null);
        set_frame.setResizable(false);
        thread_label1 = new JLabel("请输入想要创建的进程编号：");
        set_frame.add(thread_label1); //构建编辑框
        thread_label1.setBounds(20,20,180,25); //大小和位置
        
        thread_text1 = new JTextPane();
        set_frame.add(thread_text1);
        thread_text1.setBounds(200,20,100,20);
        
        thread_label2 = new JLabel("请输入想要创建的进程大小：");
        set_frame.add(thread_label2); //构建进程框
        thread_label2.setBounds(20,80,180,25); //进程框的大小位置
        
        thread_text2 = new JTextPane();
        set_frame.add(thread_text2);
        thread_text2.setBounds(200,80,100,20);
        
        jbutton4 = new JButton("确定");
        set_frame.add(jbutton4);
        jbutton4.addActionListener(this);
        jbutton4.setBounds(40,150,80,30);
        jbutton4.setCursor(new Cursor(12)); //按钮
        jbutton5 = new JButton("取消");
        set_frame.add(jbutton5);
        jbutton5.addActionListener(this);
        jbutton5.setBounds(160,150,80,30);
        jbutton5.setCursor(new Cursor(12)); //按钮
        set_frame.setVisible(true); //显示对话框
    }

    public void runAllocate(String programName, int programSize) {
        flag = false;
        int totalFree = 0;
        switch (memoryAllocationAlgorithm) {
            case 1:
                unusedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
                break;
            case 2:
                unusedMemoryBlockList.sort(new MyComparatorBestAdaptation());
                break;
            case 3:
                unusedMemoryBlockList.sort(new MyComparatorWorstAdaptation());
                break;
        }
        for (int i = 0; i < unusedMemoryBlockList.size(); i++) {
            MemoryBlock mbTmp = unusedMemoryBlockList.get(i);
            totalFree = totalFree + mbTmp.getSize();
            if (mbTmp.getSize() >= programSize) {
                flag = true;
                if (mbTmp.getSize() == programSize) {
                    usedMemoryBlockList.add(new MemoryBlock(programName, mbTmp.getStartAddress(), mbTmp.getSize()));
                } else {
                    usedMemoryBlockList.add(new MemoryBlock(programName, mbTmp.getStartAddress(), programSize));
                    unusedMemoryBlockList.add(new MemoryBlock("unused", mbTmp.getStartAddress() + programSize, mbTmp.getSize() - programSize));
                }
                unusedMemoryBlockList.remove(i);
                break;
            }
        }    
        /*调用"紧凑"算法*/
        if(flag == false && totalFree >= programSize){
            jinCou(totalFree);
        }
        if (flag == false && totalFree <= programSize) {
            notice("free");
        }
    }

    /*"紧凑"算法*/
    public void jinCou(int totalFree) {
        int temp = memory;
        for (int i = usedMemoryBlockList.size() - 1; i >= 0; i--) {
            MemoryBlock mb = usedMemoryBlockList.get(i);
            mb.setStartAddress(temp - mb.getSize());
            temp = temp - mb.getSize();
        }
        unusedMemoryBlockList.clear();
        unusedMemoryBlockList.add(new MemoryBlock("unused", 0, totalFree));
        runAllocate(programName,programSize);

    }

    public void destroyProgram() {
        set_frame = new JFrame("撤消进程");
        set_frame.setSize(400, 150);
        set_frame.setLayout(null);
        set_frame.setLocationRelativeTo(null);
//        set_frame.setLocation(370, 330);
        set_frame.setResizable(false);
        thread_label1 = new JLabel("请输入想要撤消的进程名：");
        set_frame.add(thread_label1); //构建编辑框
        thread_label1.setBounds(20, 20, 180, 25); //大小和位置
        thread_text1 = new JTextPane();
        set_frame.add(thread_text1);
        thread_text1.setBounds(200, 20, 100, 20);
        
        jbutton6 = new JButton("确定");
        set_frame.add(jbutton6);
        jbutton6.addActionListener(this);
        jbutton6.setBounds(80, 70, 80, 30);
        jbutton6.setCursor(new Cursor(12)); //按钮
        
        jbutton5 = new JButton("取消");
        set_frame.add(jbutton5);
        jbutton5.addActionListener(this);
        jbutton5.setBounds(220, 70, 80, 30);
        jbutton5.setCursor(new Cursor(12)); //按钮
        set_frame.setVisible(true); //显示对话框
    }
    public void recover(String programName) {
        usedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
        unusedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
        flag = false;
        int loc = 0;
        for (int i = 0; i < usedMemoryBlockList.size(); i++) {
            if (usedMemoryBlockList.get(i).getState().equals(programName)) {
                flag = true;
                loc = i;
                break;
            }
        }
        //如果没找到这个程序，就报告没有该程序，无法回收
        if (flag == false) {
            notice("find");
        } else {
            MemoryBlock mbTmp = usedMemoryBlockList.get(loc);
            //先判断上邻、下邻、上下邻、上下都不邻
            boolean up = false;
            boolean down = false;
            int upLoc = 0, downLoc = 0;
            for (int j = 0; j < unusedMemoryBlockList.size(); j++) {
                if (mbTmp.getStartAddress() == unusedMemoryBlockList.get(j).getStartAddress() + unusedMemoryBlockList.get(j).getSize()) {
                    upLoc = j;
                    up = true;
                }
                if (mbTmp.getStartAddress() + mbTmp.getSize() == unusedMemoryBlockList.get(j).getStartAddress()) {
                    downLoc = j;
                    down = true;
                }
            }
            if (up == true && down == true) {
                MemoryBlock mb = new MemoryBlock("unused", unusedMemoryBlockList.get(upLoc).getStartAddress(), mbTmp.getSize() + unusedMemoryBlockList.get(upLoc).getSize() + unusedMemoryBlockList.get(downLoc).getSize());
                usedMemoryBlockList.remove(loc);
                unusedMemoryBlockList.remove(upLoc);//删除了upLoc，导致数组个数少1
                unusedMemoryBlockList.remove(downLoc - 1);//由于数组个数少1，这里注意是downLoc-1
                unusedMemoryBlockList.add(mb);

            } else if (up == true && down == false) {
                unusedMemoryBlockList.add(new MemoryBlock("unused", unusedMemoryBlockList.get(upLoc).getStartAddress(), mbTmp.getSize() + unusedMemoryBlockList.get(upLoc).getSize()));
                usedMemoryBlockList.remove(loc);
                unusedMemoryBlockList.remove(upLoc);
            } else if (up == false && down == true) {
                unusedMemoryBlockList.add(new MemoryBlock("unused", mbTmp.getStartAddress(), mbTmp.getSize() + unusedMemoryBlockList.get(downLoc).getSize()));
                usedMemoryBlockList.remove(loc);
                unusedMemoryBlockList.remove(downLoc);
            } else {
                unusedMemoryBlockList.add(new MemoryBlock("unused", mbTmp.getStartAddress(), mbTmp.getSize()));
                usedMemoryBlockList.remove(loc);
            }
        }
    }
    
    public static void getEmptyStringArray(Object[][] stringarray) {
        for (int i = 0; i < stringarray.length; i++) {
            for (int j = 0; j < stringarray[i].length; j++) {
                stringarray[i][j] = null;
            }
        }
    }
    /*设置表格*/
    public static void set_table() {
        //为了打印出图形化界面，这里开一个临时的ArrayList
        ArrayList<MemoryBlock> aList = new ArrayList<MemoryBlock>();
        //先按照内存块首址排序，之后再打印
        unusedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
        usedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
        getEmptyStringArray(table_data1);
        getEmptyStringArray(table_data2);
        for (int i = 0; i < usedMemoryBlockList.size(); i++) {
            table_data1[i][0] = i+1;//编号
            table_data1[i][1] = usedMemoryBlockList.get(i).getStartAddress();//起始地址
            table_data1[i][2] = usedMemoryBlockList.get(i).getSize();//大小
            table_data1[i][3] = usedMemoryBlockList.get(i).getState();//程序名
            aList.add(usedMemoryBlockList.get(i));
        }
        for (int i = 0; i < unusedMemoryBlockList.size(); i++) {
            table_data2[i][0] = i+1;//编号
            table_data2[i][1] = unusedMemoryBlockList.get(i).getStartAddress();//起始地址
            table_data2[i][2] = unusedMemoryBlockList.get(i).getSize();//大小
            table_data2[i][3] = unusedMemoryBlockList.get(i).getState();//程序名
            aList.add(unusedMemoryBlockList.get(i));
        }
        
        Font x = new Font("Serif",0,18);
        pic_text.setFont(x);
        pic_text.setText("");
        aList.sort(new MyComparatorFirstAdaptation());
        for (int i = 0; i < aList.size(); i++) {
            pic_text.append("          ------------------"+aList.get(i).getStartAddress()+"\n");
            boolean first = true;
            for (int j = 0; j < (aList.get(i).getSize() + 1000) / 1000; j++) {
                if (first) {
                    pic_text.append("          |                     |   " + aList.get(i).getState()+"\n");
                    first = false;
                }else {
                    pic_text.append("          |                     |   \n");
                }
            }
            if (aList.get(i).getStartAddress() + aList.get(i).getSize() == memory) {
                pic_text.append("          ------------------"+String.valueOf(aList.get(i).getStartAddress() + aList.get(i).getSize())+"\n");
            }
        }
        pic_text.repaint();
        jTable1.repaint(); //重绘
        jTable2.repaint(); //重绘
    }
    
    /*错误提示信息窗口*/
    public void notice(String success) {
        fr = new JFrame("注意");
        fr.setSize(530, 150);
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
//        fr.setLocation(260, 280);
        fr.setResizable(false);
        if (success == "free") {
            lab1 = new JLabel("系统内存的容量不足，请撒消某些进程！");
            fr.add(lab1);
            lab1.setBounds(130, 35, 400, 25);
        } else if (success == "find") {
            lab1 = new JLabel("找不到该程序，无法回收该程序。");
            fr.add(lab1);
            lab1.setBounds(130, 35, 400, 25);
        }
        pel = new JPanel();
        pel.setLayout(new BorderLayout());
        fr.add(pel);
        pel.setBounds(15, 10, 100, 100);
        jbutton7 = new JButton("确定");
        fr.add(jbutton7);
        jbutton7.setBounds(410, 35, 90, 30);
        jbutton7.addActionListener(this);
        jbutton7.setCursor(new Cursor(12));
        fr.setVisible(true);
    }
}

//自定义排序比较器
class MyComparatorFirstAdaptation implements Comparator<MemoryBlock> {

    @Override
    public int compare(MemoryBlock mb1, MemoryBlock mb2) {
        return mb1.getStartAddress() - mb2.getStartAddress();
    }
}

class MyComparatorBestAdaptation implements Comparator<MemoryBlock> {

    @Override
    public int compare(MemoryBlock mb1, MemoryBlock mb2) {
        return mb1.getSize() - mb2.getSize();
    }
}

class MyComparatorWorstAdaptation implements Comparator<MemoryBlock> {

    @Override
    public int compare(MemoryBlock mb1, MemoryBlock mb2) {
        return mb2.getSize() - mb1.getSize();
    }
}
