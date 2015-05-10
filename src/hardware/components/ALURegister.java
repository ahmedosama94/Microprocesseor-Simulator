package hardware.components;

public class ALURegister extends Register{
	
	public ALU alu;	
	
	public ALURegister(int size, ALU alu) {
		super(size);
		this.alu = alu;
	}
	
	protected void clockCycle() {
		super.clockCycle();
		alu.update();
	}
	
}
