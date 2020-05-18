import java.io.IOException;
import java.util.Arrays;

public class MemoryAccess extends Block {

	static Execute execute1 = new Execute();
	static String inst3;
	static String[] cont3;
	static long rs3;
	public MemoryAccess(int valid, int tag, int index, String data) {
		super(valid, tag, index, data);
		// TODO Auto-generated constructor stub
	}

	static String[] MemoryData = { "00000000", "00000101", "10000111", "00000000", "00001000", "10010001", "11101011",
			"00000110", "10101000", "00000000", "00010110", "00001111", "10000111", "00001010", "10001000", "00000001",
			"00000111", "10000001", "11110111", "00000101", "10100111", "00001100", "10001001", "00000101", "10001010",
			"00000110", "10000100", "00001010", "10000011", "00010001", "00111010", "00001011", "01000100", "00000011",
			"00000100", "01010101", "00000010", "00110110", "00001010", "10110111", "00011001", "11011011", "00101001",
			"01101110", "11100111", "00000101", "01000111", "10101010", "00000000", "00000101", "10000111", "00000000",
			"00001000", "10010001", "11101011", "00000110", "10101000", "00000000", "00010110", "00001111", "10000111",
			"00001010", "10001000", "00000001", "00000111", "10000001", "11110111", "00000101", "10100111", "00001100",
			"10001001", "00000101", "10001010", "00000110", "10000100", "00001010", "10000011", "00010001", "00111010",
			"00001011", "01000100", "00000011", "00000100", "01010101", "00000010", "00110110", "00001010", "10110111",
			"00011001", "11011011", "00101001", "01101110", "11100111", "00000101", "01000111", "10101010" };

	String mem[] = new String[2];
	static Block[] cache = new Block[16];
	// cache[0] =(Block) new Block(0,0,0,1);

	String[] arr = new String[] {};
	String hh;
	boolean f = false;

	public String[] MemoryAccess(String AluResult, String ReadData2, String SignExtended, String zeroflag,
			String BrachAddress, String MemWrite, String MemRead, String Branch) throws IOException {
		inst3=Execute.inst2;
		rs3=Execute.rs2;
		cont3=Execute.cont2;
		MemWrite=cont3[5];
		MemRead=cont3[4];
		Branch=cont3[6];
		System.out.println();
		System.out.println("Memory Stage: "+inst3);
		System.out.println("MemRead: "+MemRead);
		System.out.println("MemWrite: "+MemWrite);
		System.out.println("Branch: "+Branch);
		if (MemRead.equals("0") && MemWrite.equals("0")) {
			mem[0] = AluResult;
			mem[1] = ReadData2;
			System.out.println("There is no output of this stage because of the instruction's nature"+"\n");
			//System.out.println("Memory Outputs: "+Arrays.toString(mem)+"\n");
			return mem;
		}

		try {

			int memoryaddress = Integer.parseInt(AluResult, 2);

			int x = memoryaddress % 16;

			int t = memoryaddress / 16;
			// address is even

				hh = MemoryData[memoryaddress] + MemoryData[memoryaddress + 1];
				
			

			if (cache[x].valid == 1) {
				System.out.println("valid=1");
				if (cache[x].tag == t) {
					hit++;
					if (MemRead.equals("1")) {
						
						cache[x].valid = 1;
						cache[x].tag = t;
						ReadData2 = cache[x].data;
						System.out.println("memread");
						System.out.println("ALURESULT=" + AluResult + " , " + "ReadData2=" + ReadData2);

					}
					if (MemWrite.equals("1")) {

						cache[x].valid = 1;
						cache[x].tag = t;
						cache[x].data = ReadData2;
						MemoryData[memoryaddress]=ReadData2.substring(0, 8);
						MemoryData[memoryaddress + 1]=ReadData2.substring(8);
						System.out.println("memWrite");

					}
				}

				else {
					if (MemRead.equals("1")) {
						miss++;
						cache[x].tag = t;
						cache[x].valid = 1;
						ReadData2 = hh;
						cache[x].data = ReadData2;
						System.out.println("memread");
						System.out.println("ALURESULT=" + AluResult + " , " + "ReadData2=" + ReadData2);

					}
					if (MemWrite.equals("1")) {

						cache[x].valid = 1;
						cache[x].tag = t;

						cache[x].data = ReadData2;
						MemoryData[memoryaddress]=ReadData2.substring(0, 8);
						MemoryData[memoryaddress + 1]=ReadData2.substring(8);

						miss++;
						cache[x].tag = t;
						cache[x].valid = 1;
						System.out.println("memwrite");
					}

				}

			}

			if (cache[x].valid == 0) {
				
				
				if (MemRead.equals("1")) {
					miss++;
					cache[x].tag = t;
					cache[x].valid = 1;
					ReadData2 = hh;
					cache[x].data = hh;
					System.out.println("memread");
					System.out.println("ALURESULT=" + AluResult + " , " + "ReadData2=" + ReadData2);

				}
				if (MemWrite.equals("1")) {

					cache[x].valid = 1;
					cache[x].tag = t;

					cache[x].data = ReadData2;
					MemoryData[memoryaddress]=ReadData2.substring(0, 8);
					MemoryData[memoryaddress + 1]=ReadData2.substring(8);
					miss++;
					cache[x].tag = t;
					cache[x].valid = 1;
					System.out.println("memwrite");

				}
				

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("memory out of boundary");
		}

		mem[0] = AluResult;
		mem[1] = ReadData2;
		//System.out.println("Memory Outputs: "+Arrays.toString(mem)+"\n");
		//System.out.println("Control Signals: " + Arrays.toString(cont3));
		return mem;
	}

	
}
