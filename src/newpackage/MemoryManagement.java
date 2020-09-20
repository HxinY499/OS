/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;
import java.util.*;

/**
 *
 * @author lenovo
 */
public class MemoryManagement{

	public ArrayList<MemoryBlock> usedMemoryBlockList;//已分配内存块表
	public ArrayList<MemoryBlock> unusedMemoryBlockList;//未分配内存块表
//	public int threshold;//门限值
	public int memoryAllocationAlgorithm;//内存分配算法。1--首次适应，2--最佳适应，3--最坏适应	
	

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		/*
		 *完成初始化
		*/
		MemoryManagement mm = new MemoryManagement();
		mm.init();
		mm.menu();
		System.out.print("请选择内存分配算法： ");
		mm.memoryAllocationAlgorithm = scan.nextInt();
//		System.out.print("请输入门限值： ");
//		mm.threshold = scan.nextInt();
		/*
		 *开始实验
		*/
		String allocateOrRecover;//分配或回收
		String programName;//程序名
		int programSize;//程序大小
		while(true){
			System.out.println("===============================================================");
			System.out.println("\n输入格式：[allocate 程序名 程序大小]或[recover 程序名]");
			allocateOrRecover = scan.next();
			if(allocateOrRecover.equals("allocate")){
				programName = scan.next();
				programSize = scan.nextInt();
				mm.runAllocate(programName, programSize);
			}else if(allocateOrRecover.equals("recover")){
				programName = scan.next();
				mm.recover(programName);
			}else{
				System.out.println("输入有误，请按照[allocate 程序名 程序大小]或[recover 程序名]格式重新输入");
			}
			System.out.println("===============================================================");
		}
		
	}

	//初始化，并打印给出某中间状况的内存快照
	public void init(){
		usedMemoryBlockList = new ArrayList<MemoryBlock>();
		unusedMemoryBlockList = new ArrayList<MemoryBlock>();
	
		usedMemoryBlockList.add(new MemoryBlock("programA",0,500));
		usedMemoryBlockList.add(new MemoryBlock("programB",5500,500));
		usedMemoryBlockList.add(new MemoryBlock("programC",2500,1500));
		usedMemoryBlockList.add(new MemoryBlock("programD",500,800));
			
		unusedMemoryBlockList.add(new MemoryBlock("unused",1300,1200));
		unusedMemoryBlockList.add(new MemoryBlock("unused",4000,1500));

		print();
	}
	//打印内存快照
	public void print(){
		//为了打印出图形化界面，这里开一个临时的ArrayList
		ArrayList<MemoryBlock> aList = new ArrayList<MemoryBlock>();
		
		//先按照内存块首址排序，之后再打印
		unusedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
		usedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
		System.out.println("-----------------------------------");
		System.out.println("已分配内存块表");
		System.out.println("起始地址\t大小\t程序名");
		for(int i = 0; i < usedMemoryBlockList.size(); i++){
			System.out.println(usedMemoryBlockList.get(i).getStartAddress()+"\t\t"+usedMemoryBlockList.get(i).getSize()+"\t"+usedMemoryBlockList.get(i).getState());
			aList.add(usedMemoryBlockList.get(i));
		}
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("未分配内存块表");
		System.out.println("起始地址\t大小\t状态");
		for(int i = 0; i < unusedMemoryBlockList.size(); i++){
			System.out.println(unusedMemoryBlockList.get(i).getStartAddress()+"\t\t"+unusedMemoryBlockList.get(i).getSize()+"\t"+unusedMemoryBlockList.get(i).getState());
			aList.add(unusedMemoryBlockList.get(i));
		}
		System.out.println("-----------------------------------");
		System.out.println();
		
		System.out.println("图形化如下");
		aList.sort(new MyComparatorFirstAdaptation());
		for(int i = 0;i < aList.size();i++) {
			System.out.printf("%10d ------------------\n",aList.get(i).getStartAddress());
			boolean first = true;
			for(int j=0;j<(aList.get(i).getSize()+1000)/1000;j++) {
				
				if(first){
					System.out.println("          |                 |"+aList.get(i).getState());
					
					first = false;
				}else{
					System.out.println("          |                 |");
				}
			}
			if(aList.get(i).getStartAddress()+aList.get(i).getSize() == 6000){
				System.out.printf("%10d ------------------\n",aList.get(i).getStartAddress()+aList.get(i).getSize());
			}
		}
		
	}
	//内存分配
	public void runAllocate(String programName,  int programSize){
		boolean flag = false;//如果没有找到满足的内存块
		switch(memoryAllocationAlgorithm){
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
		for(int i = 0 ; i < unusedMemoryBlockList.size(); i++){
			MemoryBlock mbTmp = unusedMemoryBlockList.get(i);
			if(mbTmp.getSize() >= programSize){
				
				flag = true;
				if(mbTmp.getSize() == programSize){
					usedMemoryBlockList.add(new MemoryBlock(programName, mbTmp.getStartAddress(), mbTmp.getSize()));
					
				}else{
					usedMemoryBlockList.add(new MemoryBlock(programName, mbTmp.getStartAddress(), programSize));
					unusedMemoryBlockList.add(new MemoryBlock("unused",mbTmp.getStartAddress()+programSize,mbTmp.getSize() - programSize));
				}
				unusedMemoryBlockList.remove(i);
				break;
			}
		}
		if(flag == false){
			System.out.println("没有空闲块可以满足。");
		}
		print();
	}
	
	//回收
	public void recover(String programName){
		usedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
		unusedMemoryBlockList.sort(new MyComparatorFirstAdaptation());
		boolean flag = false;
		int loc=0;
		for(int i = 0; i < usedMemoryBlockList.size(); i++){
			if(usedMemoryBlockList.get(i).getState().equals(programName)){
				flag = true;
				loc = i;
				break;
			}
		}
		//如果没找到这个程序，就报告没有该程序，无法回收
		if(flag == false){
			System.out.println("找不到该程序，无法回收该程序。");
		}else{
			MemoryBlock mbTmp = usedMemoryBlockList.get(loc);
			//先判断上邻、下邻、上下邻、上下都不邻
			boolean up = false;
			boolean down = false;
			int upLoc=0, downLoc=0;
			for(int j = 0; j < unusedMemoryBlockList.size(); j++){
				if(mbTmp.getStartAddress() == unusedMemoryBlockList.get(j).getStartAddress()+unusedMemoryBlockList.get(j).getSize()){
					upLoc = j;
					up = true;
				}
				if(mbTmp.getStartAddress() + mbTmp.getSize()== unusedMemoryBlockList.get(j).getStartAddress()){
					downLoc = j;
					down = true;
				}
			}
			if(up==true && down ==true){
				MemoryBlock mb = new MemoryBlock("unused",unusedMemoryBlockList.get(upLoc).getStartAddress(),mbTmp.getSize()+unusedMemoryBlockList.get(upLoc).getSize()+unusedMemoryBlockList.get(downLoc).getSize());
				
				usedMemoryBlockList.remove(loc);
				unusedMemoryBlockList.remove(upLoc);//删除了upLoc，导致数组个数少1
				unusedMemoryBlockList.remove(downLoc-1);//由于数组个数少1，这里注意是downLoc-1
				unusedMemoryBlockList.add(mb);
				
			}else if(up == true && down == false){
				unusedMemoryBlockList.add(new MemoryBlock("unused",unusedMemoryBlockList.get(upLoc).getStartAddress(),mbTmp.getSize()+unusedMemoryBlockList.get(upLoc).getSize()));
				usedMemoryBlockList.remove(loc);
				unusedMemoryBlockList.remove(upLoc);
			}else if(up == false && down == true){
				unusedMemoryBlockList.add(new MemoryBlock("unused",mbTmp.getStartAddress(),mbTmp.getSize()+unusedMemoryBlockList.get(downLoc).getSize()));
				usedMemoryBlockList.remove(loc);
				unusedMemoryBlockList.remove(downLoc);
			}else{
				unusedMemoryBlockList.add(new MemoryBlock("unused",mbTmp.getStartAddress(),mbTmp.getSize()));
				usedMemoryBlockList.remove(loc);
				
			}
		}
		print();
	}

	//用户选择菜单
	public void menu(){
		System.out.println("*******************menu*******************");
		System.out.println("1-----------------首次适应");
		System.out.println("2-----------------最佳适应");
		System.out.println("3-----------------最坏适应");
		System.out.println();
	}

	//自定义排序比较器
	class MyComparatorFirstAdaptation implements Comparator<MemoryBlock>{
		public int compare(MemoryBlock mb1, MemoryBlock mb2){
			return mb1.getStartAddress() - mb2.getStartAddress();
		}
	}
	class MyComparatorBestAdaptation implements Comparator<MemoryBlock>{
		public int compare(MemoryBlock mb1, MemoryBlock mb2){
			return mb1.getSize() - mb2.getSize();
		}
	}
	class MyComparatorWorstAdaptation implements Comparator<MemoryBlock>{
		public int compare(MemoryBlock mb1, MemoryBlock mb2){
			return mb2.getSize() - mb1.getSize();
		}
	}
}

