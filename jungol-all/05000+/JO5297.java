/*
 * 정올 5297번: 소뒤집기
 * https://jungol.co.kr/problem/5297
 * 난이도: 골드 2
 * 풀이 날짜: 2026-06-29
 * 간단 설명: 1 ~ N번까지의 소를 M개의 구간에 대해 뒤집는 걸 K번 반복하는 문제

소를 직접 뒤집으려면 N * M * K를 해야 함. 숫자가 너무 큼
우선 한 번은 직접 소를 뒤집어 봄. 그러면 N * M이라 가능함
그럼 각 숫자가 이동돼있음. 그럼 이제 이 이동에 맞게 사이클 발생
K % (사이클을 형성하는 이동 횟수) 연산만큼 이동시키면 최종 배치 가능
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class JO5297 {
    static int N, K; // 소의 수, 반복 횟수
    static int[] cow; // 소 배열
    
    static ArrayList<Integer> cycle = new ArrayList<>(); // 사이클 묶음을 위한 ArrayList
    static boolean[] visited; // 방문 처리 배열
    static int[] result; // 정답 배열
    
    static void reverse(int L, int R) { // 소를 뒤집는 메서드
        int tmp; // swap을 위한 임시 변수
        
        while (L < R) {
            // 위치 교환
            tmp = cow[L];
            cow[L] = cow[R];
            cow[R] = tmp;
            
            // 다음 자리로 이동
            L++;
            R--;
        }
    } // reverse 종료
    
    static void placeCow() { // 소를 최종 배치하는 함수
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue; // 이미 배치한 곳이면 continue
            
            int cur = i; // 현재 위치
            
            while (!visited[cur]) {
                visited[cur] = true; // 방문 처리
                cur = cow[cur]; // 다음 이동 위치
                cycle.add(cur); // 사이클 원소 추가
            }
            
            int move = cycle.size(); // 남은 움직임 횟수
            
            for (int j = 0; j < move; j++) {
                int after = cycle.get(j); // 원래 위치
                result[after] = cycle.get((j + K) % move); // 소 최종 배치
            }
            
            cycle.clear(); // 사이클 비우기
        }
    } // placeCow 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 소의 수
        int M = Integer.parseInt(st.nextToken()); // 구간의 수
        K = Integer.parseInt(st.nextToken()); // 반복 횟수
        
        cow = new int[N + 1]; // 소 배열
        for (int i = 1; i <= N; i++) cow[i] = i; // 소 배치
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken()); // 뒤집으려는 구간의 시작 지점
            int R = Integer.parseInt(st.nextToken()); // 뒤집으려는 구간의 종료 지점
            
            reverse(L, R); // 소 뒤집기
        }

        // 버퍼 닫기
        br.close();
        
        result = new int[N + 1]; // 정답 배열
        visited = new boolean[N + 1]; // 방문 처리 배열
        placeCow(); // 소를 최종 배치하는 함수
        
        StringBuilder sb = new StringBuilder(); // 소의 순서를 저장할 객체
        for (int i = 1; i <= N; i++) sb.append(result[i]).append("\n"); // 결과값 추가하기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료