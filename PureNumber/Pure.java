import java.util.*;
import java.io.*;

public class Pure {

	private static ArrayList<Integer> globalSumTracker = new ArrayList<Integer>();

	public static void main(String args[]) throws IOException {
		System.out.println("** Enter a number and press enter **");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input.txt contains only the number which we want to check.
		String str = br.readLine();
		int num = Integer.parseInt(str);
		boolean isPure = checkPure(num);
		System.out.println(str + "'s pure check : "+isPure);
	}

	public static boolean checkPure(int num) {

		int sum = parseNumberAndGetSum(num);

		//If the sum equals one, it is a pure number
		if(sum == 1 ) {
			return true;
		}
		//Else, if we have already arrived at this number before, it is not pure;
		else if(globalSumTracker.contains(sum) || sum < 0) {
			return false;
		}
		//Else, check if the new sum is a pure number. 
		else {
			globalSumTracker.add(sum);   
			return checkPure(sum);   
		}

	}

	public static int  parseNumberAndGetSum(int num) {
		int rem=0, rev=num;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while(rev > 0) {
			rem = rev % 10;
			digits.add(rem);
			rev = rev / 10;
		}
		int sum = findSum(digits);
		return sum;
	}

	public static int findSum(ArrayList<Integer> digits) {
		int sum= 0;
		//Find the sum of squares for the digits
		for(int i=0; i<digits.size(); i++) {
			//Check if we exceeded the integer limit.
			if(sum + (digits.get(i) * digits.get(i)) > Integer.MAX_VALUE) {
				return -1; 
			}
			sum = sum + (digits.get(i) * digits.get(i)); 
		}

		return sum;

	}

}