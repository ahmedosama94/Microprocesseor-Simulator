package main;

import hardware.components.*;

public class Tester {
	
	public static void main(String[]args) {
		Register r1 = new Register(32);
		Register r2 = new Register(32);
		r1.setInputBuffer(23);
		r2.setInputBuffer(r1.getOutputBuffer());
		r1.setEnable(true);
		r2.setEnable(true);
		System.out.println("R1: " + r1.getHex());
		System.out.println("R2: " + r2.getHex() + "\n");
		Register.clockCycleAll();
		Register.updateAllOutputs();
		System.out.println("R1: " + r1.getHex());
		System.out.println("R2: " + r2.getHex() + "\n");
		Register.clockCycleAll();
		Register.updateAllOutputs();
		System.out.println("R1: " + r1.getHex());
		System.out.println("R2: " + r2.getHex() + "\n");
	}

}
