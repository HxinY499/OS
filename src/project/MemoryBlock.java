/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


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
        
        @Override
	public String toString(){
		return ("[state: "+state+", startAddress: "+startAddress+", size: "+size+"]");
	}
}

