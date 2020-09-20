/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.*;

/**
 *
 * @author lenovo
 */
public class NONPSA {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<PSAPCB> beReadyPCB = new LinkedList<>();
        LinkedList<PSAPCB> PSAPCBS = new LinkedList<>();
        LinkedList<PSAPCB> block =new LinkedList<>();
        LinkedList<String> strings = new LinkedList<>();

//        while (true) {
//            System.out.println("依次输入进程名，到达时间，服务时间，优先权");
//            try {
//                String name = input.next();
//                int arrive_time = input.nextInt();
//                int need_time = input.nextInt();
//                int priority = input.nextInt();
////               int startblock=input.nextInt();
////               int blocktime=input.nextInt();
//                PSAPCBS.add(new PSAPCB(name, arrive_time, need_time, priority));
//                System.out.println("是否继续？（1继续，0结束）");
//                int i = input.nextInt();
//                if (i == 0) {
//                    PSAPCBS.sort((o1, o2) -> (o1.arrive_time - o2.arrive_time));
//                    break;
//                }
//            } catch (Exception e) {
//                System.out.println("输入有误，请重新运行程序");
//                break;
//            }
//        }

        System.out.println("*******************************************进程运行初信息************************************************");
        System.out.println("*\t\t进程名\t\t到达时间\t\t优先权\t\t服务时间");
        for (int i = 0; i < PSAPCBS.size(); i++) {
            System.out.println("*\t\t" + PSAPCBS.get(i).information() + "\t\t*");
        }
        System.out.println("*********************************************************************************************************");
        int runtime = 0;

        while (!PSAPCBS.isEmpty() || !beReadyPCB.isEmpty()) {
            runtime++;
            System.out.println("*******************时刻为：" + runtime + "*************************");
            while (!PSAPCBS.isEmpty() && PSAPCBS.get(0).arrive_time <= (runtime - 1)) {
                beReadyPCB.add(PSAPCBS.get(0));
                PSAPCBS.remove(0);
            }
//            if(!block.isEmpty()){
//                for(int i=0;i<block.size();i++){
//                    if(block.get(i).blocktime<=0){
//                        System.out.print("时间初从堵塞队列转入就绪队列的进程有：");
//                        System.out.print(block.get(i).pname);
//                        beReadyPCB.add(block.get(i));
//                        block.remove(i);
//                    }
//                }
//                System.out.println();
//                if(!block.isEmpty()) {
//                    System.out.print("进入堵塞队列的进程有：");
//                    for (int i = 0; i < block.size(); i++) {
//                        System.out.print(block.get(i).pname + " ");
//                    }
//                }
//                System.out.println();
//                for(int i=0;i<block.size();i++){
//                    block.get(i).blocktime--;
//                }
//            }
            if (beReadyPCB.isEmpty()) {
                System.out.println("当前没有程序运行");
            }
            if (!beReadyPCB.isEmpty()) {
                beReadyPCB.sort(PSAPCB::compareTo);
                System.out.println("当前正在进行的进程为：");
                System.out.println("进程名\t\t运行时间\t\t优先权\t\t状态");
                beReadyPCB.get(0).runtime++;
                beReadyPCB.get(0).startblock--;
                beReadyPCB.get(0).state = "r";
                System.out.println(beReadyPCB.get(0).messages());
                if (beReadyPCB.size() != 1) {
                    System.out.println("进入就绪队列的进程为：");
                    System.out.println("进程名\t\t运行时间\t\t优先权\t\t状态");
                    for (int i = 1; i < beReadyPCB.size(); i++) {
                        beReadyPCB.get(i).state = "w";
                        System.out.println(beReadyPCB.get(i).messages());
                    }
                }
                if (beReadyPCB.get(0).runtime == beReadyPCB.get(0).need_time || beReadyPCB.get(0).startblock == 0) {
                    if (beReadyPCB.get(0).runtime == beReadyPCB.get(0).need_time) {
                        strings.add(beReadyPCB.get(0).name);
                        beReadyPCB.remove(0);
                    }
//                    if(!beReadyPCB.isEmpty()&&beReadyPCB.get(0).startblock==0&&beReadyPCB.get(0).blocktime!=0){
//                        block.add(beReadyPCB.get(0));
//                        beReadyPCB.remove(0);
//                    }
                }
            }
//            if (!strings.isEmpty()){
//                System.out.print("时间末结束的进程有：");
//                for(String s:strings){
//                    System.out.print(s.toString()+"   ");
//                }
//                System.out.println();
//            }
        }
    }
}
