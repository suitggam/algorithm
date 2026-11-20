/*
 * 백준 2841번: 외계인의 기타 연주
 * https://www.acmicpc.net/problem/2841
 * 난이도: 실버 1
 * 풀이 날짜: 2026-11-21
 * 간단 설명: 멜로디를 연주하는데 필요한 최소 손가락의 움직임 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ2841 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 멜로디에 포함돼있는 음의 수
        int P = Integer.parseInt(st.nextToken()); // 한 줄에 있는 프렛의 수
        
        ArrayDeque<Integer>[] stack = new ArrayDeque[N + 1]; // 스택 배열 생성
        for (int i = 1; i <= N; i++) stack[i] = new ArrayDeque<>(); // 스택 생성
        
        int sound; // 음 번호
        int fret;  // 프렛 번호
        int cur;   // 현재 프렛 번호
        int minCnt = 0; // 손가락을 가장 적게 움직인 횟수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            sound = Integer.parseInt(st.nextToken()); // 음 번호
            fret  = Integer.parseInt(st.nextToken()); // 프렛 번호
            
            cur = stack[sound].peek() == null ? 0 : stack[sound].peek(); // 현재 프렛 번호보다 높은 경우
            if (cur < fret) { // 기존의 프렛보다 높은 프렛인 경우
                stack[sound].push(fret); // 프렛 번호 push
                minCnt++; // 한 번 누르기
            } else if (cur > fret) { // 현재 프렛 번호보다 낮은 경우
                while (cur > fret) {
                    stack[sound].pop(); // 프렛 번호 pop
                    minCnt++; // 손가락 떼기
                    cur = stack[sound].peek() == null ? 0 : stack[sound].peek(); // 현재 프렛 번호보다 높은 경우
                }
                
                if (cur < fret) { // 현재 프렛보다 번호가 높은 경우
                    stack[sound].push(fret); // 프렛 번호 push
                    minCnt++; // 한 번 누르기
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력
        System.out.print(minCnt); // 결과값 출력
    }
}