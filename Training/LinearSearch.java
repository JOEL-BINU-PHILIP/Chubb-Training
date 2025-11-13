
public class LinearSearch {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		int n = a.length;
		int num = 8;
		for(int i =0; i<n; i++) {
			if(num == a[i]) {
				System.out.print(i);
				break;
			}
		}
	}

}
