import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//6자리 수 입력받고 출력 제대로 되도록 수정
public class BabyJin {
	
	static boolean isRun(int[] arr) {
		if ((arr[0] == arr[1]+1 && arr[1] == arr[2]+1) 
				|| (arr[0] == arr[1]-1 && arr[1] == arr[2]-1)) {
			return true;
		}
		return false;
	}
	static boolean isTripple(int[] arr) {
		if ((arr[0] == arr[1] && arr[1] == arr[2])) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean flag = false;
		int[] arr = new int[]{5,1,8,0,3,1};
		int[] front = new int[3];
		int[] back = new int[3];
		int length = arr.length;
		for (int i1 = 0; i1 < length; i1++) {
			for (int i2 = 0; i2 < length; i2++) {
				if (i1 != i2) {
					for (int i3 = 0; i3 < length; i3++) {
						if (i3 != i2 && i3 != i1) {
							for (int i4 = 0; i4 < length; i4++) {
								if (i4 != i3 && i4 != i2 && i4 != i1) {
									for (int i5 = 0; i5 < length; i5++) {
										if (i5 != i4 && i5 != i3 && i5 != i2 && i5 != i1 ) {
											for (int i6 = 0; i6 < length; i6++) {
												if (i6 != i5 && i6 != i4 && i6 != i3 && i6 != i2 && i6 != i1) {
													front[0] = arr[i1];
													front[1] = arr[i2];
													front[2] = arr[i3];
													back[0] = arr[i4];
													back[1] = arr[i5];
													back[2] = arr[i6];
													
													if ( isRun(front) || isTripple(front) )  {
														if (isRun(back) || isTripple(back) ) {
//															System.out.printf("%d %d %d %d %d %d",front[0], front[1], front[2], back[0], back[1], back[2]);
															flag = true;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
