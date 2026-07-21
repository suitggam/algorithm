/*
 * 정올 3427번: 볼 모으기(balls)
 * https://jungol.co.kr/problem/3427
 * 난이도: 실버 1
 * 풀이 날짜: 2026-07-22
 * 간단 설명: 볼을 같은 색끼리 놓기 위한 최소 이동 횟수를 구하는 문제

총 4가지 경우의 수가 존재함
빨간색 볼을 오른쪽으로 옮기는 경우
빨간색 볼을 왼쪽으로 옮기는 경우
파란색 볼을 오른쪽으로 옮기는 경우
파란색 볼을 왼쪽으로 옮기는 경우

그래서 4가지 경우를 구해서 최솟값을 구하면 됨
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JO3427 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 볼의 총 개수
        String ball = br.readLine(); // 볼 배열
        
        // 버퍼 닫기
        br.close();
        
        int redCnt  = 0; // 빨간색 볼의 개수
        int blueCnt = 0; // 파란색 볼의 개수
        for (int i = 0; i < N; i++) {
            if (ball.charAt(i) == 'R') redCnt++; // 빨간색 볼 개수 증가 
        }
        
        blueCnt = N - redCnt; // 파란색 볼의 개수
        
        // 왼쪽 끝에서 연속된 볼의 색상과 개수
        char leftColor = ball.charAt(0); // 볼의 색상
        int leftContinueCnt = 0; // 연속된 개수
        for (int i = 0; i < N; i++) {
            if (ball.charAt(i) == leftColor) leftContinueCnt++; // 연속된 볼의 개수 증가
            else break;
        }
        
        // 오른쪽 끝에서 연속된 볼의 색상과 개수
        char rightColor = ball.charAt(N - 1); // 볼의 색상
        int rightContinueCnt = 0; // 연속된 개수
        for (int i = N - 1; i >= 0; i--) {
            if (ball.charAt(i) == rightColor) rightContinueCnt++; // 연속된 볼의 개수 증가
            else break;
        }
        
        int minMove = Integer.MAX_VALUE; // 최소 이동 횟수
        
        // 1. 빨간색 볼을 왼쪽으로 모으는 경우
        minMove = Math.min(minMove, redCnt - (leftColor == 'R' ? leftContinueCnt : 0));
        
        // 2. 빨간색 볼을 오른쪽으로 모으는 경우
        minMove = Math.min(minMove, redCnt - (rightColor == 'R' ? rightContinueCnt : 0));
        
        // 3. 파란색 볼을 왼쪽으로 모으는 경우
        minMove = Math.min(minMove, blueCnt - (leftColor == 'B' ? leftContinueCnt : 0));
        
        // 4. 파란색 볼을 오른쪽으로 모으는 경우
        minMove = Math.min(minMove, blueCnt - (rightColor == 'B' ? rightContinueCnt : 0));
        
        // 결과값 출력하기
        System.out.print(minMove);
    } // main 종료
} // Main 종료