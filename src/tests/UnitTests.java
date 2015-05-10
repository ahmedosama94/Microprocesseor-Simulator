package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import hardware.components.ALU;
import hardware.components.ALUopr;
import hardware.components.HLRegister;
import hardware.components.Multiplexer;
import hardware.components.Register;
import hardware.components.units.RegisterMemory;
import hardware.exceptions.HardwareException;

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

	@Test
	public void testMultiplexer() {
		Register r1 = new Register(32);
		Register r2 = new Register(32);
		Register out = new Register(32);
		out.setEnable(true);
		r1.setInputBuffer(14);
		r1.setEnable(true);
		r2.setInputBuffer(62);
		r2.setEnable(true);
		Register.clockCycleAll();
		r1.setEnable(false);
		r2.setEnable(false);
		ArrayList<Register> inputs = new ArrayList<Register>();
		inputs.add(r1);
		inputs.add(r2);
		try {
			Multiplexer mux = new Multiplexer(inputs);
			mux.setOutTo(out);
			mux.setSelect(0);
			Register.clockCycleAll();
			assertEquals(14, out.getInt());
			mux.setSelect(1);
			Register.clockCycleAll();
			assertEquals(62, out.getInt());
		} catch(HardwareException e) {
			fail(e.getClass().toString() + ": " + e.getMessage());
		}
	}

	@Test
	public void testRegisterMemory() {
		try {
			RegisterMemory rm = new RegisterMemory(32, 16);
			int number = 34;
			rm.setReadWrite(true);
			for(int i = 0; i < rm.getNumberOfRegisters(); i++) {
				rm.setInputBuffer(number + i);
				rm.setSelect(i);
				Register.clockCycleAll();
			}
			rm.setReadWrite(false);
			Register result = new Register(32);
			rm.setOutTo(result);
			result.setEnable(true);
			for(int i = 0; i < rm.getNumberOfRegisters(); i++) {
				rm.setSelect(i);
				Register.clockCycleAll();
				assertEquals(number + i, result.getInt());
			}
		} catch(HardwareException e) {
			fail(e.getClass().toString() + ": " + e.getMessage());
		}
	}
	
	@Test
	public void testALU() {
		ALU alu = new ALU(32);
		Register resultRegister = new Register(32);
		resultRegister.setInputBuffer(13);
		resultRegister.setEnable(true);
		Register.clockCycleAll();
		alu.setOutTo(resultRegister);
		alu.getA().setInputBuffer(14);
		alu.getB().setInputBuffer(51);
		resultRegister.setEnable(false);
		alu.getA().setEnable(true);
		alu.getB().setEnable(true);
		Register.clockCycleAll();
		alu.getA().setEnable(false);
		alu.getB().setEnable(false);
		resultRegister.setEnable(true);
		try{
			alu.setSelect(ALUopr.CLEAR);
			assertEquals(13, resultRegister.getInt());
			Register.clockCycleAll();
			assertEquals(0, resultRegister.getInt());
		} catch(Exception e) {
			fail(e.getClass().toString() + ": " + e.getMessage());
		}
	}

}
