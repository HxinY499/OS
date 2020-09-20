/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author lenovo
 */
public class MemoryBlock{
	private int startAddress;//内存块起始地址	
	private int size;//内存块大小
	private String state;//内存块状态，要么是未分配，要么是程序名

	public MemoryBlock(){}

	public MemoryBlock(String programName, int startAddress, int size){
		this.state = programName;
		this.startAddress = startAddress;
		this.size = size;
	}

	public void setStartAddress(int startAddress){
		this.startAddress = startAddress;
	}
	public int getStartAddress(){
		return this.startAddress;
	}
	public void setSize(int size){
		this.size = size;
	}
	public int getSize(){
		return this.size;
	}
	public void setState(String programName){
		this.state = programName;
	}
	public String getState(){
		return this.state;
	}


	public String toString(){
		return ("[state: "+state+", startAddress: "+startAddress+", size: "+size+"]");
	}
	//测试
	public static void main(String[] args){
		MemoryBlock mb = new MemoryBlock();
		mb.setStartAddress(1000);
		mb.setSize(200);
		mb.setState("p1");
		System.out.println(mb.getStartAddress());
		System.out.println(mb.getSize());
		System.out.println(mb.getState());
		System.out.println(mb.toString());

		System.out.println(new MemoryBlock("programName", 2000, 1000));
	}
}
