import java.io.IOException;
import java.util.*;


public class Main {
	static String pc = "0";
	static Fetching fetch = new Fetching();
	static InstructionDecode decode = new InstructionDecode();
	static Execute execute = new Execute();
	static MemoryAccess memory = new MemoryAccess(0, 0, 0, "0");
	static WriteBack back = new WriteBack();
	static String[][] MEMORY = { Fetching.InstMem, memory.MemoryData };
	static String[] IFID = new String[2];
	static String[] IDEX = new String[5];
	static String[] EXMEM = new String[5];
	static String[] MEMWB = new String[2];
	static String[] controlSignals1 = new String[11];
	static String[] controlSignals2 = new String[11];
	static String[] controlSignals3 = new String[11];
	static String[] controlSignals4 = new String[11];

	public static void fetchThread() {
		int pci=Integer.parseInt(pc,2);
		int size=Fetching.InstMem.length;
		if(pci<size) {
		Thread f = new Thread() {
			public void run() {
				if (Integer.parseInt(pc, 2) < Fetching.InstMem.length)
					IFID = fetch.InstFetch(pc);
			}
		};
		f.start();
		try {
			f.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pc = IFID[1];}
	}

	public static void decodeThread() {
		Thread d = new Thread() {
			public void run() {

				IDEX = decode.InstDecode(IFID[0], Integer.parseInt(pc, 2));

				// controlSignals.enqueue(controlSignals1);

			}
		};
		d.start();
		try {
			d.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		controlSignals1 = decode.ContUnit(IFID[0]);

		
	}

	public static void executeThread() {
		
		Thread e = new Thread() {
			public void run() {

				EXMEM = execute.Execute(controlSignals1[0], controlSignals1[2], IDEX[1], IDEX[2], IDEX[3],
						controlSignals1[8], controlSignals1[9], controlSignals1[10], pc);

			}
		};
		e.start();
		try {
			e.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     pc=EXMEM[4];
	}

	public static void memoryThread() {
		
		Thread m = new Thread() {
			public void run() {
				try {

					MEMWB = memory.MemoryAccess(EXMEM[0], IDEX[2], IDEX[3], EXMEM[1], EXMEM[2], controlSignals2[5],
							controlSignals2[4], controlSignals2[6]);
									} catch (IOException e) {
					System.out.println("memoryOutOfBoundary");
				}
			}
		};
		m.start();
		try {
			m.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void WBThread() {
	
		Thread w = new Thread() {
			public void run() {

				String wbouts = WriteBack.WriteBack(MEMWB[0], MEMWB[1], controlSignals3[7],
						controlSignals1[3]);
				//System.out.println(Arrays.toString(controlSignals1)+"\n");

				// System.out.println("write back outputs " + wbouts);
			}
		};
		w.start();
		try {
			w.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void first() {
    	fetchThread();
		
//		System.out.println("outputs of fetching " + Arrays.toString(IFID) + "\n");
		
    }
    public static void second() {
    	decodeThread();
    	fetchThread();
    }
    public static void third() {
    	executeThread();
    	decodeThread();
    	fetchThread();
    }
    public static void fourth() {
    	memoryThread();
    	executeThread();
    	decodeThread();
    	fetchThread();
    }
    public static void fifth() {
    	WBThread();
    	memoryThread();
    	executeThread();
    	decodeThread();
    	fetchThread();	
    }
    public static void fourthbackword() {
    	WBThread();
    	memoryThread();
    	executeThread();
    	decodeThread();
    	
    }
    public static void thirdbackword() {
    	WBThread();
    	memoryThread();
    	executeThread();
    	
    }
    public static void secondbackword() {
    	WBThread();
    	memoryThread();
    	
    }
    public static void firstbackword() {
    	WBThread();
    	
    }
                
    public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 16; i++) {
			MemoryAccess.cache[i] = (Block) new Block(0, 0, i, "1");
		}
		int pci = Integer.parseInt(pc, 2);
		
		int cycle=1;int size=Fetching.InstMem.length;
	while (cycle!=(size/2)+5) {
		System.out.println("The Current Cycle is: "+cycle);
		int diff=(size/2)+5-cycle;
		int tmp=cycle;
		if(diff<=4) {
			cycle=-diff;
		}
			switch(cycle) {
			case 1:first();break;
			case 2:second();break;
			case 3:third();break;
			case 4:fourth();break;
			case -4:fourthbackword();break;
			case -3:thirdbackword();break;
			case -2:secondbackword();break;
			case -1:firstbackword();break;
			default:fifth();break;
			}
			cycle=tmp+1;
			pci=Integer.parseInt(pc,2);
			
			System.out.println();
			System.out.println("Next PC: "+pci);
			System.out.println("-------------------------------------------------------------------------");
		}
		
		// int pci = Integer.parseInt(pc);
		// // System.out.println(pci);
		// while (pci < Fetching.InstMem.length) {
		// String[] inst = fetch.InstFetch(pc);
		// pc = inst[1];
		// pci = Integer.parseInt(pc, 2);
		// System.out.println("the pc is "+pci);
		// System.out.println(Arrays.toString(inst) + "\n");
		// String[] decodeOutputs = decode.InstDecode(inst[0],Integer.parseInt(pc,2));
		// System.out.println("decode outputs " + Arrays.toString(decodeOutputs) +
		// "\n");
		// String extended =decodeOutputs[3];
		// String[] controlSignals = decode.ContUnit(inst[0]);
		// System.out.println("Control signals " + Arrays.toString(controlSignals) +
		// "\n");
		// String[] executeOutputs = execute.Execute(controlSignals[0],
		// controlSignals[2], decodeOutputs[1],
		// decodeOutputs[2], extended, controlSignals[8], controlSignals[10],
		// controlSignals[9], pc);
		// System.out.println("execute outputs " + Arrays.toString(executeOutputs) +
		// "\n");
		// if (controlSignals[4].equals("1") || controlSignals[5].equals("1")) {
		// String[] memAccessOuts = memory.MemoryAccess(executeOutputs[0],
		// decodeOutputs[2], extended,
		// executeOutputs[1], executeOutputs[2],
		// controlSignals[5], controlSignals[4],
		// controlSignals[6]);
		// System.out.println("memory access outputs " + Arrays.toString(memAccessOuts)
		// + "\n");
		//
		// }
		//
		// WriteBack.WriteBack(executeOutputs[0], decodeOutputs[2],
		// Integer.parseInt(controlSignals[7], 2), Integer.parseInt(controlSignals[1],
		// 2));
		// System.out.println("write back outputs " + InstructionDecode.ReadData2);
		//
		// System.out.println("--------------------------------------------------------------------------------------");
		// }
		// }
	}
}
