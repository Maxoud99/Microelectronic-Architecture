import java.util.Arrays;
import java.util.concurrent.Callable;

public class Fetching {

	static String[] InstMem = { "11110110", "00000101", "10000111", "00000000", "00001000", "10010001", "11101011",
			"00000110", "10101000", "00000000", "00010000", "00001111", "10000111", "00001010", "10001000", "00000001",
			"00000111", "10000001", "11110111", "00000101", "10100111", "00001100", "10001001", "00000101", "10001010",
			"00000110", "10000100", "00001010", "10000011", "00010001", "00001001", "10100010", "00001010", "10110011",
			"00000100", "00110100", "00000100", "01010101", "00000010", "00110110", "00001010", "10110111", "00011001",
			"10011011", "00101001", "10011110", "11100111", "00000101", "01000000", "00101101" };

	public String[] InstFetch(String pc) {
		System.out.println();
		System.out.println("Fetch Stage: ");
		String[] outputs = new String[2];
		int pci = Integer.parseInt(pc, 2);
		String inst = InstMem[pci] + InstMem[pci + 1];
		pc = ProgCount(pc);
		outputs[0] = inst;
		outputs[1] = pc;
		System.out.println("Fetch Outputs: " + Arrays.toString(outputs)+"\n");
		return outputs;
	}

	public String ProgCount(String pc) {
		int pci = Integer.parseInt(pc, 2);

		pci += 2;
		// System.out.println(pci);
		String out = Integer.toBinaryString(pci);

		return out;
	}
}
