package tests;

import static org.junit.Assert.*;
import hardware.components.HLRegister;
import hardware.components.Register;

import org.junit.Test;

public class UnitTests {
	
	@Test
	public void testLoading() {
		Register r = new Register(32);
		r.setEnable(true);
		r.setInputBuffer(21);
		Register.clockCycleAll();
		assertEquals(21, r.getInt());
	}
	
	@Test
	public void testPassingOfValues() {
		Register r1 = new Register(32);
		r1.setEnable(true);
		Register r2 = new Register(32);
		r2.setEnable(true);
		r1.setInputBuffer(23);
		r2.setInputBuffer(r1.getOutputBuffer());
		Register.clockCycleAll();
		Register.clockCycleAll();
		assertEquals(r1.getInt(), r2.getInt());
		assertEquals(r1.getBits(), r2.getBits());
		assertEquals(r1.getHex(), r2.getHex());
	}
	
	@Test
	public void testHLRegisterLoading() {
		HLRegister r = new HLRegister(16, 32);
		r.setEnableLow(true);
		r.setInputBuffer(14);
		Register.clockCycleAll();
		assertEquals(14, r.getInt());
	}

}
