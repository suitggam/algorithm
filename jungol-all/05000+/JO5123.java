/*
 * 정올 5123번: ABBC
 * https://jungol.co.kr/problem/5123
 * 난이도: 골드 5
 * 풀이 날짜: 2026-07-10
 * 간단 설명: A와 그 뒤에 있는 B를 지울 수 있고 B와 그 뒤에 있는 C를 지울 수 있을 때 최대로 지울 수 있는 횟수를 구하는 문제

B는 앞의 A와 지워질 수도 있고 뒤의 C와 함께 지워질 수 있다.
그렇다면 C가 나왔을 때 우선적으로 먼저 지운 후에
남은 B를 가지고 A와 함께 지우는 것이 최적의 방법이다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class JO5123 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] S = br.readLine().toCharArray(); // 문자열 배열
        
        // 버퍼 닫기
        br.close();
        
        int max = 0; // 최대 시행 횟수
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // B의 인덱스를 담을 Queue
        for (int i = 0; i < S.length; i++) { // BC 없애기
            if (S[i] == 'B') { // 문자가 B인 경우
                queue.add(i); // B의 인덱스 add
            } else if (S[i] == 'C' && queue.size() > 0) { // 문자가 C면서 앞에 B가 있는 경우
                S[queue.poll()] = ' '; // 가장 앞에 있는 B 없애기
                max++; // 최대 시행 횟수 증가
            }
        }
        
        int ACnt = 0; // B 앞에 있는 A의 개수
        for (int i = 0; i < S.length; i++) { // AB 없애기
            if (S[i] == 'A') ACnt++; // 문자가 B인 경우
            else if (S[i] == 'B' && ACnt > 0) { // 문자가 B면서 앞에 A가 있는 경우
                ACnt--; // B의 개수 감소
                max++; // 최대 시행 횟수 증가
            }
        }
        
        // 결과값 출력하기
        System.out.print(max);
    } // main 종료
} // Main 종료