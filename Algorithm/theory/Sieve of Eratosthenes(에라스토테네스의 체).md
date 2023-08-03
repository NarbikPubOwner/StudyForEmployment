
고대 그리스의 수학자, 에라스토테네스가 찾아낸 소수를 찾는 방법 중 하나. 체로 거르듯 수를 걸러낸다 하여 이름에 "체"가 들어간다. f(x) = x/1p(X)로 표현이 가능하다(X가 소수면 1, 아니면 0의 결과를 낸다)

에라스토테네스의 체의 시각적 작동 방식

![다운로드](https://github.com/NarbikPubOwner/StudyForEmployment/assets/113754405/309420b4-e41c-4f97-9dc8-60ca5169509d)

요약하자면 소수를 찾으면 그 수의 제곱 + 소수의 배수를 소수에서 제하는 것이다.

프로그래밍적 구현(JAVA)
public class Solution {

	// 예제와 같이 120까지의 소수를 구하기 위해 120 선언.
	static boolean prime[] = new boolean[121];
    
    public static void main(String[] args) throws Exception{
		
        // 구하고자 하는 숫자 범위
        int N = 120;
        
        // 소수는 false
        // 1은 소수가 아니므로 제외
        prime[0] = prime[1] = true;
        
        for(int i=2; i*i<=N; i++){
        	// prime[i]가 소수라면
            if(!prime[i]){
            	// prime[j] 소수가 아닌 표시
            	for(int j=i*i; j<=N; j+=i) prime[j] = true;                
            }        
        }    
        
        // 소수 출력
        for(int i=1; i<=N;i++){
        	if(!prime[i]) System.out.print(i+" ");        
        }
        
    }
}
주의 할 점은 다음과 같은 코드의 경우 크기가 너무 커지면 자바 자체적으로 오류가 나게 된

  

출처
https://namu.wiki/w/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98%20%EC%B2%B4
https://ldevil63.tistory.com/56

