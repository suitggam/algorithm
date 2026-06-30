/*
 * 정올 5393번: 도시와 주
 * https://jungol.co.kr/problem/5393
 * 난이도: 골드 5
 * 풀이 날짜: 2026-06-30
 * 간단 설명: a도시의 첫 두 글자와 B주의 코드가 같고 b도시의 첫 두 글자와 A주의 코드가 같은 경우의 수를 구하는 문제

가장 좋은 건 완전 탐색이지만 N * N이 최대로 나오면 시간 초과 발생
그래서 도시 이름 두 글자 코드와 주의 코드를 합쳐서 HashMap에 넣음
그리고 입력을 받을 때마다 특별한 관계에 있는 코드를 HashMap에서 찾아서 개수만큼 더함
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class JO5393 {
    static HashMap<String, Integer> special = new HashMap<>(); // 도시와 주를 묶어서 저장할 HashMap
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 도시의 수
        long cnt = 0; // 특별한 관계의 도시 쌍의 수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String city = st.nextToken().substring(0, 2); // 도시의 이름
            String state = st.nextToken(); // 주의 코드
            
            String name = city + state; // 도시의 코드와 주의 코드를 합친 이름
            String pair = state + city; // 쌍이 될 도시의 코드와 주의 코드를 합친 이름
            
            special.put(name, special.getOrDefault(name, 0) + 1); // 같은 코드 개수 증가
            
            if (!city.equals(state)) {
                cnt += special.getOrDefault(pair, 0); // 짝이 되는 코드의 개수만큼 더하기
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료