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
		Register rr = new Register(32);
		rr.setInputBuffer(13);
		rr.setEnable(true);
		Register.clockCycleAll();
		alu.setOutTo(rr);
		alu.getA().setInputBuffer(14);
		alu.getB().setInputBuffer(51);
		rr.setEnable(false);
		alu.getA().setEnable(true);
		alu.getB().setEnable(true);
		Register.clockCycleAll();
		alu.getA().setEnable(false);
		alu.getB().setEnable(false);
		rr.setEnable(true);
		Register a = alu.getA();
		Register b = alu.getB();
		try{
			alu.setSelect(ALUopr.A);
			Register.clockCycleAll();
			assertEquals(a.getInt(), rr.getInt());
			alu.setSelect(ALUopr.ADD);
			Register.clockCycleAll();
			assertEquals(a.getInt() + b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.ADDINC);
			Register.clockCycleAll();
			assertEquals(a.getInt() + b.getInt() + 1, rr.getInt());
			alu.setSelect(ALUopr.AND);
			Register.clockCycleAll();
			assertEquals(a.getInt() & b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.B);
			Register.clockCycleAll();
			assertEquals(b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.CLEAR);
			Register.clockCycleAll();
			assertEquals(0, rr.getInt());
			alu.setSelect(ALUopr.EQ);
			Register.clockCycleAll();
			assertEquals(~(a.getInt() ^ b.getInt()), rr.getInt());
			alu.setSelect(ALUopr.INCA);
			Register.clockCycleAll();
			assertEquals(a.getInt() + 1, rr.getInt());
			alu.setSelect(ALUopr.INCB);
			Register.clockCycleAll();
			assertEquals(b.getInt() + 1, rr.getInt());
			alu.setSelect(ALUopr.INCNOTA);
			Register.clockCycleAll();
			assertEquals(~a.getInt() + 1, rr.getInt());
			alu.setSelect(ALUopr.INCNOTB);
			Register.clockCycleAll();
			assertEquals(~b.getInt() + 1, rr.getInt());
			alu.setSelect(ALUopr.NAND);
			Register.clockCycleAll();
			assertEquals(~(a.getInt() & b.getInt()), rr.getInt());
			alu.setSelect(ALUopr.NOT_A_OR_B);
			Register.clockCycleAll();
			assertEquals(~a.getInt() | b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.NOT_B_OR_A);
			Register.clockCycleAll();
			assertEquals(a.getInt() | ~b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.NOTA);
			Register.clockCycleAll();
			assertEquals(~a.getInt(), rr.getInt());
			alu.setSelect(ALUopr.NOTB);
			Register.clockCycleAll();
			assertEquals(~b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.OR);
			Register.clockCycleAll();
			assertEquals(a.getInt() | b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.SET1);
			Register.clockCycleAll();
			assertEquals(1, rr.getInt());
			alu.setSelect(ALUopr.SUB_AB_DEC);
			Register.clockCycleAll();
			assertEquals(a.getInt() - b.getInt() - 1, rr.getInt());
			alu.setSelect(ALUopr.SUB_BA_DEC);
			Register.clockCycleAll();
			assertEquals(a.getInt() - b.getInt() - 1, rr.getInt());
			alu.setSelect(ALUopr.SUBAB);
			Register.clockCycleAll();
			assertEquals(a.getInt() - b.getInt(), rr.getInt());
			alu.setSelect(ALUopr.SUBBA);
			Register.clockCycleAll();
			assertEquals(b.getInt() - a.getInt(), rr.getInt());
			alu.setSelect(ALUopr.XOR);
			Register.clockCycleAll();
			assertEquals(a.getInt() ^ b.getInt(), rr.getInt());
		} catch(Exception e) {
			fail(e.getClass().toString() + ": " + e.getMessage());
		}
	}

}
