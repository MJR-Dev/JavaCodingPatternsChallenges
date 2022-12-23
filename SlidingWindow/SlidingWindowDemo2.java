import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SlidingWindowDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] inp = sc.nextLine().split(",");
		int windowSize = Integer.parseInt(sc.nextLine());
		sc.close();
		int[] arr = new int[inp.length];
		for(int p=0;p<inp.length;p++) {
			arr[p] = Integer.parseInt(inp[p]);
		}
		int maxElement = 0;
		TreeSet<Integer> ts = new TreeSet<>();
		for(int i=0;i<=arr.length-windowSize;i++) {
			if(i==0) {				
				for(int j=0;j<i+windowSize;j++) {
					ts.add(arr[j]);
				}
				maxElement = ts.last();
			}
			else {
				ts.remove(arr[i-1]);
				ts.add(arr[i+windowSize-1]);				
				if(maxElement < ts.last())
					maxElement = ts.last();
			}
		}
			System.out.println("Result: "+maxElement);
	}

}
