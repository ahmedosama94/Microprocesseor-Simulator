package main;

import hardware.components.*;

public class Tester {
	
	public static void main(String[]args) {
		Register r = new Register(32);
		r.connect(32);
		r.setEnable(true);
		System.out.println(r.getInt());
		r.flipClock();
		System.out.println(r.getInt());
		r.flipClock();
		System.out.println(r.getInt());
		r.flipClock();
		System.out.println(r.getInt());
	}

}
