package os;

/**
 * 
 * @author lenovo
 */
public class PCB {
    String name;
    int arrive_time;
    int need_time;
    int runtime=0;
    String state="1";
    int priority=0;
    int startblock;
    int blocktime;
//    PCB(){
//    }
    /**
     * 用于非优先权进程调度的PCB构造函数，不包含优先权的构造，默认其为0
     */
    public PCB(String pname, int arrive_time, int need_time) {
        this.name = pname;
        this.arrive_time = arrive_time;
        this.need_time = need_time;
//        this.startblock=startblock;
//        this.blocktime=blocktime;
    }
    /**
     *用于优先权调度的构造函数。
     */
    public PCB(String name, int arrive_time, int need_time,int priority) {
        this.name = name;
        this.arrive_time = arrive_time;
//        this.startblock=startblock;
//        this.blocktime=blocktime;
        this.need_time = need_time;
        this.priority = priority;
    }
    /**
     * 返回PCB原始信息
     * @return 
     */
    public String information(){
        return name+"\t\t\t"+arrive_time+"\t\t\t"+priority+"\t\t\t"+need_time+"\t\t\t\t"+startblock+"\t\t\t\t\t"+blocktime;
    }
    /**
     * 返回PCB当前信息
     * @return 
     */
    public String messages(){
        return name+"\t\t\t"+runtime+"\t\t\t"+priority+"\t\t\t"+state;
    }
}