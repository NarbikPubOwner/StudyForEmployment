package javapractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스문제 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//링크드 리스트 사용?
		//나머지 활용
		//진행될때마다 IDX-1 + K
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " " );
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			linkedList.add(i+1);
		}
		
		StringBuilder sb = new StringBuilder("");
		sb.append("<");
		int targetIdx = K-1;
		while (linkedList.size() != 0) {
			sb.append(linkedList.remove(targetIdx));
			if (linkedList.size() != 0) {
				sb.append(",").append(" ");
				targetIdx = (targetIdx + K - 1)%linkedList.size();
			}
		}
		sb.append(">");
		System.out.println(sb);
		
	}

}
