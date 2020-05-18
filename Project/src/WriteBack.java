import java.util.Arrays;

public class WriteBack extends InstructionDecode {

	static MemoryAccess memory1 = new MemoryAccess(0, 0, 0, "0");
	static String[] cont4;
	static String inst4;
	static long rs4;

	public static String WriteBack(String ALUresult, String ReadData, String MemToReg, String RegWrite) {
		String WriteData = "";
		inst4 = MemoryAccess.inst3;
		rs4 = MemoryAccess.rs3;
		cont4 = MemoryAccess.cont3;
		MemToReg = cont4[7];
		RegWrite = cont4[3];
		if (MemToReg.equals("1")) {
			WriteData = ReadData;
			data[(int) rs4] = WriteData;
			System.out.println("The register's number where the data will be written in is : " + rs4+"\n");
		}

		if (MemToReg.equals("0") && RegWrite.equals("1")) {
			WriteData = ALUresult;
			data[(int) rs4] = WriteData;
			System.out.println("The register's number where the data will be written in is : " + rs4+"\n");
		}
		System.out.println();
		System.out.println("Writeback Stage: " + inst4);
		System.out.println("MemtoReg: " + MemToReg);
		System.out.println("RegWrite: " + RegWrite);

		if (WriteData.isEmpty()) {
			System.out.println("There is no output of this stage because of the instruction's nature" + "\n");
		} else {
			System.out.println("Data Written: " + WriteData + "\n");
		}
		// System.out.println("Control Signals: " + Arrays.toString(cont4));
		return WriteData;
	}
}
