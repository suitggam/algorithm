/*
 * 정올 5393번: 도시와 주
 * https://jungol.co.kr/problem/5393
 * 난이도: 골드 5
 * 풀이 날짜: 2026-06-30
 * 간단 설명: a도시의 첫 두 글자와 B주의 코드가 같고 b도시의 첫 두 글자와 A주의 코드가 같은 경우의 수를 구하는 문제

가장 좋은 건 완전 탐색이지만 N * N이 최대로 나오면 시간 초과 발생
그래서 도시 이름 두 글자 코드와 주의 코드를 숫자로 변환해서 2차원 배열 생성
그리고 도시를 입력 받을 때마다 특별한 관계에 있는 쌍의 개수를 더함
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO5393 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 도시의 수
        long cnt = 0; // 특별한 관계의 도시 쌍의 수
        
        int[][] map = new int[26 * 26][26 * 26]; // map[도시 코드][주 코드]
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String cityStr  = st.nextToken(); // 도시의 이름
            String stateStr = st.nextToken(); // 주의 코드
            
            int city  = (cityStr.charAt(0) - 'A') * 26 + (cityStr.charAt(1) - 'A');; // 도시의 코드와 주의 코드를 합친 이름
            int state = (stateStr.charAt(0) - 'A') * 26 + (stateStr.charAt(1) - 'A');; // 도시의 코드와 주의 코드를 합친 이름
            
            if (city != state) {
                cnt += map[state][city]; // 짝이 되는 개수만큼 더하기
            }
            
            map[city][state]++; // 쌍의 빈도수 증가
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료