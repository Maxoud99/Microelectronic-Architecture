import java.util.Arrays;

public class InstructionDecode {

	static String[] data = { "0000000000000000", "0000000000000001", "0000000000000010", "0000000000000001",
			"0000000000000001", "0000000000000001", "0000000000000001", "0000000000000100", "0000000000000010",
			"0000000000000010", "0000000000000001", "0000000000000010", "0000000000000001", "0000000000000001",
			"0000000000000001", "0000000000000001" };
	static String[] cont = new String[11];
	static long rs;
	static long rt;
	static String inst;
	static String[] contr;

	public String[] InstDecode(String instruction, int progc) {
		String ReadData1;
		String ReadData2;
		System.out.println();
		System.out.println("Decode Stage: ");
		String[] x = { "", "", "", "", "" };
		String op = instruction.substring(0, 4);
		rs = Long.parseLong(instruction.substring(4, 8), 2);
		rt = Long.parseLong(instruction.substring(8, 12), 2);
		String func = instruction.substring(12, 16);
		long immediate = Long.parseLong(instruction.substring(8, 16), 2);
		long address = Long.parseLong(instruction.substring(4, 16), 2);
		String s_immediate = instruction.substring(8, 16);
		String s_address = instruction.substring(4, 16);
		String s_rs = "";
		String s_rt = "";

		ReadData1 = data[(int) rs];
		ReadData2 = data[(int) rt];

		if (rs == 0 || rt == 0) {
			if (rs == 0)
				s_rs = "$zero";
			if (rt == 0)
				s_rt = "$zero";
		}
		if (rs == 1 || rt == 1) {
			if (rs == 1)
				s_rs = "$at";
			if (rt == 1)
				s_rt = "$at";
		}
		if (rs == 2 || rt == 2) {
			if (rs == 2)
				s_rs = "$v0";
			if (rt == 2)
				s_rt = "$v0";
		}
		if (rs == 3 || rt == 3) {
			if (rs == 3)
				s_rs = "$v1";
			if (rt == 3)
				s_rt = "$v1";
		}
		if (rs == 4 || rt == 4) {
			if (rs == 4)
				s_rs = "$a0";
			if (rt == 4)
				s_rt = "$a0";
		}
		if (rs == 5 || rt == 5) {
			if (rs == 5)
				s_rs = "$a1";
			if (rt == 5)
				s_rt = "$a1";
		}
		if (rs == 6 || rt == 6) {
			if (rs == 6)
				s_rs = "$t0";
			if (rt == 6)
				s_rt = "$t0";
		}
		if (rs == 7 || rt == 7) {
			if (rs == 7)
				s_rs = "$t1";
			if (rt == 7)
				s_rt = "$t1";
		}
		if (rs == 8 || rt == 8) {
			if (rs == 8)
				s_rs = "$t2";
			if (rt == 8)
				s_rt = "$t2";
		}
		if (rs == 9 || rt == 9) {
			if (rs == 9)
				s_rs = "$t3";
			if (rt == 9)
				s_rt = "$t3";
		}
		if (rs == 10 || rt == 10) {
			if (rs == 10)
				s_rs = "$t4";
			if (rt == 10)
				s_rt = "$t4";
		}
		if (rs == 11 || rt == 11) {
			if (rs == 11)
				s_rs = "$t5";
			if (rt == 11)
				s_rt = "$t5";
		}
		if (rs == 12 || rt == 12) {
			if (rs == 12)
				s_rs = "$t6";
			if (rt == 12)
				s_rt = "$t6";
		}
		if (rs == 13 || rt == 13) {
			if (rs == 13)
				s_rs = "$t7";
			if (rt == 13)
				s_rt = "$t7";
		}
		if (rs == 14 || rt == 14) {
			if (rs == 14)
				s_rs = "$sp";
			if (rt == 14)
				s_rt = "$sp";
		}
		if (rs == 15 || rt == 15) {
			if (rs == 15)
				s_rs = "$ra";
			if (rt == 15)
				s_rt = "$ra";
		}

		if (op.equals("0000")) {

			x[4] = func;

			if (func.equals("0001")) {
				inst = "Add " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Add " + s_rs + ", " + s_rt);
			} else if (func.equals("0010")) {
				inst = "Sub " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Sub " + s_rs + ", " + s_rt);
			} else if (func.equals("0011")) {
				inst = "Mul " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Mul " + s_rs + ", " + s_rt);
			} else if (func.equals("0100")) {
				inst = "And " + s_rs + ", " + s_rt;
				System.out.println("Instruction: And " + s_rs + ", " + s_rt);
			} else if (func.equals("0101")) {
				inst = "Shl " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Shl " + s_rs + ", " + s_rt);
			} else if (func.equals("0110")) {
				inst = "Shr " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Shr " + s_rs + ", " + s_rt);
			} else if (func.equals("0111")) {
				inst = "Slt " + s_rs + ", " + s_rt;
				System.out.println("Instruction: Slt " + s_rs + ", " + s_rt);
			}
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);

		}

		else if (op.equals("1000")) { // lw
			System.out.println("Instruction: LW " + s_rs + ", " + s_immediate);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "LW " + s_rs + ", " + s_immediate;

		} else if (op.equals("1010")) { // sw
			System.out.println("Instruction: SW " + s_rs + ", " + s_immediate);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "SW " + s_rs + ", " + s_immediate;

		} else if (op.equals("0001")) { // bne
			System.out.println("Instruction: BNE " + s_rs + ", " + s_rt + ", " + func);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "BNE " + s_rs + ", " + s_rt + ", " + func;

		} else if (op.equals("0010")) { // bgt
			System.out.println("Instruction: BGT " + s_rs + ", " + s_rt + ", " + func);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "BGT " + s_rs + ", " + s_rt + ", " + func;

		} else if (op.equals("1111")) { // addi
			System.out.println("Instruction: Addi " + s_rs + ", " + s_immediate);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "Addi " + s_rs + ", " + s_immediate;

		} else if (op.equals("1110")) { // ori
			System.out.println("Instruction: Ori " + s_rs + ", " + s_immediate);
			System.out.println("rs: " + Long.toBinaryString(rs));
			System.out.println("Read Data 1: " + ReadData1);
			System.out.println("rt: " + Long.toBinaryString(rt));
			System.out.println("Read Data 2: " + ReadData2);
			inst = "Ori " + s_rs + ", " + s_immediate;

		} else if (op.equals("0100")) { // j
			System.out.println("Instruction: j " + s_address);
			inst = "j " + s_address;

		}

		x[0] = op;
		x[1] = ReadData1;
		x[2] = ReadData2;
		x[3] = SignExtend(instruction, progc);
		System.out.println("Decode Outputs: " + Arrays.toString(x) + "\n");
		return x;
	}

	public String SignExtend(String instruction, int pc) {
		String opcode = instruction.substring(0, 4);
		String immvalue = "";
		String extended = "";
		String pc_bin = Integer.toBinaryString(pc);
		char c ;
		if (opcode.equals("1000") || opcode.equals("1111") || opcode.equals("1010") || opcode.equals("1110")) {
			immvalue=instruction.substring(8);
			c=immvalue.charAt(0);
			for (int i = 0; i < 16 - immvalue.length(); i++) {
				extended = extended + c;
			}
			extended = extended + immvalue;
		}
		if( opcode.equals("0001") || opcode.equals("0010")) {
			immvalue=instruction.substring(12);
			c=immvalue.charAt(0);
			for (int i = 0; i < 16 - immvalue.length(); i++) {
				extended = extended + c;
			}
			extended = extended + immvalue;
		}
		if (opcode.equals("0100")) {
			immvalue=instruction.substring(4);
			extended = immvalue + "00";
			extended = pc_bin.substring(0, 2) + extended;
		}
		System.out.println("The sign extended value is : "+extended);
		return extended;
	}

	public String[] ContUnit(String instruction) {

		String[] conti = new String[11];
		String op = instruction.substring(0, 4);
		String func = instruction.substring(12);

		if (op.equals("0000")) {
			if (func.equals("0001")) { // add
				conti[0] = "000";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0010")) { // sub
				conti[0] = "001";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0011")) { // mul
				conti[0] = "010";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0100")) { // and
				conti[0] = "011";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0101")) { // shl
				conti[0] = "101";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0110")) { // shr
				conti[0] = "110";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "0";
			} else if (func.equals("0111")) { // slt
				conti[0] = "001";
				conti[1] = "1";
				conti[2] = "0";
				conti[3] = "1";
				conti[4] = "0";
				conti[5] = "0";
				conti[6] = "0";
				conti[7] = "0";
				conti[8] = "0";
				conti[9] = "0";
				conti[10] = "1";
			}

		} else if (op.equals("1000")) { // lw
			conti[0] = "000";
			conti[1] = "0";
			conti[2] = "1";
			conti[3] = "1";
			conti[4] = "1";
			conti[5] = "0";
			conti[6] = "0";
			conti[7] = "1";
			conti[8] = "0";
			conti[9] = "0";
			conti[10] = "0";

		} else if (op.equals("1010")) { // sw
			conti[0] = "000";
			conti[1] = "0";
			conti[2] = "1";
			conti[3] = "0";
			conti[4] = "0";
			conti[5] = "1";
			conti[6] = "0";
			conti[7] = "0";
			conti[8] = "0";
			conti[9] = "0";
			conti[10] = "0";
		} else if (op.equals("0001")) { // bne
			conti[0] = "001";
			conti[1] = "0";
			conti[2] = "0";
			conti[3] = "0";
			conti[4] = "0";
			conti[5] = "0";
			conti[6] = "1";
			conti[7] = "0";
			conti[8] = "1";
			conti[9] = "0";
			conti[10] = "0";

		} else if (op.equals("0010")) { // bgt
			conti[0] = "001";
			conti[1] = "0";
			conti[2] = "0";
			conti[3] = "0";
			conti[4] = "0";
			conti[5] = "0";
			conti[6] = "1";
			conti[7] = "0";
			conti[8] = "0";
			conti[9] = "1";
			conti[10] = "0";
		} else if (op.equals("1111")) { // addi
			conti[0] = "000";
			conti[1] = "0";
			conti[2] = "1";
			conti[3] = "1";
			conti[4] = "0";
			conti[5] = "0";
			conti[6] = "0";
			conti[7] = "0";
			conti[8] = "0";
			conti[9] = "0";
			conti[10] = "0";
		} else if (op.equals("1110")) { // ori
			conti[0] = "100";
			conti[1] = "0";
			conti[2] = "1";
			conti[3] = "1";
			conti[4] = "0";
			conti[5] = "0";
			conti[6] = "0";
			conti[7] = "0";
			conti[8] = "0";
			conti[9] = "0";
			conti[10] = "0";
		} else if (op.equals("0100")) { // j
			conti[0] = "111";
			conti[1] = "0";
			conti[2] = "0";
			conti[3] = "0";
			conti[4] = "0";
			conti[5] = "0";
			conti[6] = "1";
			conti[7] = "0";
			conti[8] = "0";
			conti[9] = "0";
			conti[10] = "0";
		}
		System.out.println("ALUOp: " + conti[0]);
		System.out.println("RegDst: " + conti[1]);
		System.out.println("ALUSrc: " + conti[2]);
		System.out.println("RegWrite: " + conti[3]);
		System.out.println("MemRead: " + conti[4]);
		System.out.println("MemWrite: " + conti[5]);
		System.out.println("Branch: " + conti[6]);
		System.out.println("MemtoReg: " + conti[7]);
		System.out.println("BNE: " + conti[8]);
		System.out.println("BGT: " + conti[9]);
		System.out.println("SLT: " + conti[10]);
		System.out.println("Control Signals: " + Arrays.toString(conti) + "\n");
		cont = conti;
		return conti;
	}

}
