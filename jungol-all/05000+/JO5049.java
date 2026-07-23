/*
 * 정올 5049번: 광석수집 2
 * https://jungol.co.kr/problem/5049
 * 난이도: 실버 1
 * 풀이 날짜: 2026-07-23
 * 간단 설명: 권수쌤이 오른쪽과 아래로만 이동 가능할 때 얻을 수 있는 황금의 최대 개수를 구하는 문제

모든 조합을 완전 탐색을 돌기엔 너무 오래 걸림
특정 장소로 가는 방법은 오직 2가지. 위에서 내려오거나 왼쪽에서 오거나
그렇다면 맨 윗줄부터 탐색을 돌면서 이전 방향에서 올 수 있는 경우에 대해 max값을 구하고
황금이 있는 경우 1개를 더해주면 됨
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO5049 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 광산의 세로 길이
        int M = Integer.parseInt(st.nextToken()); // 광신의 가로 길이
        
        int[][] mine = new int[N + 1][M + 1]; // 광산 배열
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 1; y <= M; y++) mine[x][y] = Integer.parseInt(st.nextToken()); // 광산 정보
        }
        
        // 버퍼 닫기
        br.close();
        
        int[][] dp = new int[N + 1][M + 1]; // 황금 개수 배열
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= M; y++) {
                if (mine[x][y] == 1) continue; // 바위가 있어서 이동이 불가능한 경우
                else { // 이동이 가능한 경우
                    dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]); // 올 수 있는 방향에서 더 큰 값 선택
                    if (mine[x][y] == 2) dp[x][y]++; // 황금을 발견한 경우
                }
            }
        }
        
        // 결과값 출력하기
        System.out.print(dp[N][M]);
    } // main 종료
} // Main 종료