package hardware.components.units;

import hardware.components.Register;

public class ALU {

	
	
	public boolean[] add (Register A , Register B){
		int sum = A.getInt() + B.getInt();
		return convertInt(sum);
	}
	
	public boolean[] subtract (Register A , Register B) {
		int subtraction = A.getInt() - B.getInt();
		return convertInt(subtraction);
	}
	
	public boolean[] AND (Register A , Register B) {
		boolean[] result = new boolean[32];
		for(int i=0;i<A.getOutputBuffer().length;i++){
			result[i]=A.getOutputBuffer()[i] & B.getOutputBuffer()[i];
		}
		return result;
	}
	
	public boolean[] OR (Register A , Register B) {
		boolean[] result = new boolean[32];
		for(int i=0;i<A.getOutputBuffer().length;i++){
			result[i]=A.getOutputBuffer()[i] | B.getOutputBuffer()[i];
		}
		return result;
	}
	
	public boolean[] NOT (Register A) {
		boolean[] result = new boolean[32];
		for(int i=0;i<A.getOutputBuffer().length;i++){
			result[i]=!A.getOutputBuffer()[i];
		}
		return result;
	}
	
	public boolean[] convertInt(int x) {
		boolean[] result = new boolean[32];
		for(int i=0; x>0;i++){
			if(x%2==0){
				result[i]=false;
			}
			else{
				result[i]=true;
			}
			x/=2;
		}
		return result;
	}
	
	
}
