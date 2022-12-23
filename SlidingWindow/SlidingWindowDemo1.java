import java.util.Scanner;

public class SlidingWindowDemo1 {

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

		int sum =0, maxSum = 0;
		for(int i=0;i<=arr.length-windowSize;i++) {
			if(i==0) {
				for(int j=0;j<i+windowSize;j++) {
					sum = sum + arr[j];
				}
				maxSum = sum;
			}
			else {
				sum -= arr[i-1];
				sum += arr[i+windowSize-1];
				if(maxSum < sum)
					maxSum = sum;
			}
		}
			System.out.println("Result: "+maxSum);
	}

}
