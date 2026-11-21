/*
 * 백준 18429번: 근손실
 * https://www.acmicpc.net/problem/18429
 * 난이도: 실버 3
 * 풀이 날짜: 2026-11-22
 * 간단 설명: 중량이 500 밑으로 내려가지 않는 운동 키트 사용 순서 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18429 {
    static int N; // 운동 키트의 개수
    static int K; // 매일 감소하는 중량
    static int cnt; // 중량이 유지되는 경우의 수
    static int[] kit; // 운동 키트 배열
    
    static void recur(int idx, int usedCnt, int weight, boolean[] used) { // 중량을 측정하는 메서드
        used[idx] = true; // 현재 운동 키트 사용 처리
        usedCnt++; // 현재 사용된 운동 키트 개수 증가
        weight -= K; // 중량 감소
        int curWeight = weight + kit[idx]; // 현재 중량
        
        // 유지 조건
        if (usedCnt == N && curWeight >= 500) {
            cnt++; // 중량이 유지되는 경우의 수
            used[idx] = false; // 운동 키트 사용 처리 취소
            return;
        }
        
        // 종료 조건
        if (curWeight < 500) {
            used[idx] = false; // 운동 키트 사용 처리 취소
            return;
        }
            
        for (int i = 0; i < N; i++) {
            if (used[i]) continue; // 사용한 운동 키트면 continue
            
            recur(i, usedCnt, curWeight, used); // 다음 운동 키트 호출
        }
        
        used[idx] = false; // 운동 키트 사용 처리 취소
    }
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 운동 키트의 개수
        K = Integer.parseInt(st.nextToken()); // 매일 감소하는 중량
        
        kit = new int[N]; // 운동 키트 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) kit[i] = Integer.parseInt(st.nextToken()); // 운동 키트의 중량 증가량
        
        boolean[] used = new boolean[N]; // 사용 처리 배열
        for (int i = 0; i < N; i++) recur(i, 0, 500, used); // 중량 유지
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력
        System.out.print(cnt);
    }
}