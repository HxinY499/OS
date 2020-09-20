/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * @author lenovo
 */
public class PSAPCB extends PCB implements Comparable<Object> {

    public PSAPCB(String pname, int arrive_time, int need_time, int priority) {
        super(pname, arrive_time, need_time, priority);
    }

    @Override
    public int compareTo(Object b) {
        PCB st = (PCB) b;
        if (st.priority != this.priority) {
            return (st.priority - this.priority);
        } else {
            return (this.arrive_time - st.arrive_time);
        }
    }
}