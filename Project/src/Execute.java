import java.util.Arrays;

public class Execute {
	String AluResult;
	String zeroflag;
	String Branchaddress = "don't care";
	String[] Execute = new String[5];
	static InstructionDecode decode1 = new InstructionDecode();
	static String inst2;
	static String[] cont2 = new String[11];
	static long rs2;
	public String[] Execute(String ALUOP, String Alusrc, String ReadData1, String Readdata2, String signExtended,
			String bneq, String bg, String slt, String PC) {
		Branchaddress = "There is no branchaddress";
		int readdata1 = Integer.parseInt(ReadData1, 2);
		int readdata2 = Integer.parseInt(Readdata2, 2);
		System.out.println();
		int signex = 0;
		if (!signExtended.isEmpty()) {
			signex = Integer.parseInt(signExtended.substring(8), 2);
		}
		int pc = Integer.parseInt(PC, 2);
		inst2 = InstructionDecode.inst;
		rs2=InstructionDecode.rs;
		cont2 = InstructionDecode.cont;
		ALUOP = cont2[0];
		Alusrc = cont2[2];
		bneq = cont2[8];
		bg = cont2[9];
		slt = cont2[10];
		System.out.println("Execute Stage: " + inst2);
		System.out.println("SignEx: " + signExtended);
		System.out.println("ALUOp: " + ALUOP);
		System.out.println("ALUSrc: " + Alusrc);
		System.out.println("BNE: " + bneq);
		System.out.println("BGT: " + bg);
		System.out.println("SLT: " + slt);
		switch (ALUOP) {
		case ("000"):// add,addi,lw,sw
			if (Alusrc.equals("1")) {// addi,lw,sw

				AluResult = Integer.toBinaryString(readdata1 + signex);
				check();
				// if (MemRead.equals("0")&& Memwrite.equals("0")){
				// check();
				// //callwriteback
				// }
				// else{
				// check();
				// // call memory
				// Block.cache( MemRead, Memwrite, PC, Readdata2, signExtended, Branchaddress,
				// AluResult);
				// }

			} else {
				AluResult = Integer.toBinaryString(readdata1 + readdata2);
				check();
			}

			break;
		case ("001"):// sub,bneq,bg,slt
			int signex1=0;
		if(!signExtended.isEmpty()) {
			 signex1 = Integer.parseInt(signExtended.substring(12), 2);}
			int a = readdata1 - readdata2;
			if (bneq.equals("1")) {

				if (a != 0) {// branch
					int h = pc + 2 + (signex1 * 2);
					while (h < 0) {
						h = h + pc;
					}

					Branchaddress = Integer.toBinaryString(h);
					while (Branchaddress.length() < 16) {
						Branchaddress = "0" + Branchaddress;
					}
					PC = Branchaddress;
					check();
					// fetch(Branchaddress);

				}
			} else if (bg.equals("1")) {

				if (a > 0) {
					int h = pc + (signex1 * 2);
					while (h < 0) {
						h = h + pc;
					}

					AluResult = Integer.toBinaryString(a);
					Branchaddress = Integer.toBinaryString(h);

					while (Branchaddress.length() < 16) {
						Branchaddress = "0" + Branchaddress;

						// fetch(Branchaddress);
					}
					PC = Branchaddress;
					check();
				}

			} else if (slt.equals("1")) {
				if (a < 0) {
					AluResult = "0000000000000001";
					check();
				} else {
					AluResult = "0000000000000000";
					check();

				}
				// check();
				// call writeback
			} else {// sub
				AluResult = Integer.toBinaryString(a);
				check();
				// call writeback

			}

			break;

		case ("010"):// mul
			AluResult = Integer.toBinaryString(readdata1 * readdata2);
			check();
			// callwriteback
			break;
		case ("011"):// and
			AluResult = Integer.toBinaryString(readdata1 & readdata2);
			check();
			// call writeback
			break;
		case ("100"):// ori
			AluResult = Integer.toBinaryString(readdata1 | signex);
			check();
			// callwriteback
			break;
		case ("101"):// shiftleft
			int x = readdata1 << readdata2;
			AluResult = Integer.toBinaryString(x);
			check();
			// callwriteback
			break;
		case ("110"):// shiftr
			int y = readdata1 >> readdata2;
			AluResult = Integer.toBinaryString(y);
			check();
			// callwriteback
			break;
		case ("111"): // jump
			signExtended = signExtended.substring(0, 14);
			signExtended = signExtended.substring(2);
			System.out.println(signExtended);
			PC = Integer.toBinaryString(pc + Integer.parseInt(signExtended, 2));
			// String d=signExtended.substring(4);
			// int k=Integer.parseInt(d,2);
			// k=k<<2;
			// PC=PC.substring(0, 2)+Integer.toBinaryString(k);
			// while (k<0){
			// k=k+pc+2;
			// }

			Branchaddress = PC;

			while (Branchaddress.length() < 16) {
				Branchaddress = "0" + Branchaddress;
			}
			PC = Branchaddress;
			 break;

		default:
			System.out.println("error");
			break;
		}
		while (PC.length() < 16) {
			PC = "0" + PC;
		}
		Execute[0] = AluResult;
		Execute[1] = zeroflag;
		Execute[2] = Branchaddress;
		Execute[3] = Readdata2;
		Execute[4] = PC;
		
		System.out.println("Execute Outputs: " + Arrays.toString(Execute) + "\n");
		return Execute;

	}
   
	public void check() {
		while (AluResult.length() < 16) {
			AluResult = "0" + AluResult;
		}

		if (AluResult.length() > 16) {
			System.out.println(".......ERROR.......");

		}
		if (AluResult.equals("0000000000000000")) {
			zeroflag = "1";
		} else {
			zeroflag = "0";
		}
		System.out.println("Z-Flag=" + zeroflag);
		System.out.println("Branch Address=" + Branchaddress);
		System.out.println("AluResult/Address=" + AluResult);
	}

}
