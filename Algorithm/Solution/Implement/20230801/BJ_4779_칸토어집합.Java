import java.io.BufferedReader;
import java.io.InputStreamReader;
//스트링빌더 안쓰면 메모리 터짐
public class Main {
	static StringBuilder s = new StringBuilder("-");
	static StringBuilder tempS = new StringBuilder();
	static void Cantor (int cur, int target) {
		if (cur == target) {
			if (cur != 0) {
				tempS.setLength(0);
				tempS.append(s);
				for (int i = 0; i < tempS.length(); i++) {
					s.append(" ");
				}
				s.append(tempS);
			}
			return;
		}
		if (cur != 0) {
			tempS.setLength(0);
			tempS.append(s);
			for (int i = 0; i < tempS.length(); i++) {
				s.append(" ");
			}
			s.append(tempS);
		}
		Cantor(cur + 1, target);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while ((str = br.readLine()) != null) {
			Cantor(0, Integer.parseInt(str));
			System.out.println(s);
			s.setLength(0);
			s.append("-");
		}
	}	
}
