package Operating;

/**
 * 功能描述：PCB的父类，包含PCB的主要信息，以及方法。
 * @date 2018.11.5
 * @version 2.0
 */
class PCB {
    //int id;
    String pname;
    int daodatime;
    int fuwutime;
    int runtime=0;
    String state="w";
    int priority=0;
    int startblock;
    int blocktime;
    PCB(){
    }
    /**
     * 用于非优先权进程调度的PCB构造函数，不包含优先权的构造，默认其为0
     */
    PCB(String pname, int daodatime, int fuwutime) {
        this.pname = pname;
        this.daodatime = daodatime;
        this.fuwutime = fuwutime;
//        this.startblock=startblock;
//        this.blocktime=blocktime;
    }
    /**
     *用于优先权调度的构造函数。
     */
    PCB(String pname, int daodatime, int fuwutime,int priority) {
        this.pname = pname;
        this.daodatime = daodatime;
//        this.startblock=startblock;
//        this.blocktime=blocktime;
        this.fuwutime = fuwutime;
        this.priority = priority;
    }
    /**
     * 返回PCB原始信息
     */
    String information(){
        return pname+"\t\t\t"+daodatime+"\t\t\t"+priority+"\t\t\t"+fuwutime+"\t\t\t\t"+startblock+"\t\t\t\t\t"+blocktime;
    }
    /**
     * 返回PCB当前信息
     */
    String messages(){
        return pname+"\t\t\t"+runtime+"\t\t\t"+priority+"\t\t\t"+state;
    }
}