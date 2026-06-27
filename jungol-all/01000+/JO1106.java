/*
 * 정올 1106번: 장기
 * https://jungol.co.kr/problem/1106
 * 난이도: 실버 1
 * 풀이 날짜: 2026-06-27
 * 간단 설명: 말이 졸을 잡기 위한 최소 이동 횟수를 구하는 문제

방법은 크게 두 가지임
1. 이동의 가중치가 없기 때문에 최단 거리로 구하는 방법
2. 최단 거리임을 이용한 메모이제이션으로 구하는 방법
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class JO1106 {
    static int[][] board; // 장기판 배열
    static int N, M; // 장기판 행의 수, 장기판 열의 수
    static int S, K; // 졸이 있는 행, 졸이 있는 열
    
    // 말의 이동 배열
    static int[] dx = {-2, -1, 1, 2,  2,  1, -1, -2};
    static int[] dy = { 1,  2, 2, 1, -1, -2, -2, -1};
    
    static void move(int x, int y, int moveCnt) { // 말이 이동하는 메서드
        // 현재 최소 이동 횟수보다 같거나 크면 종료
        if (moveCnt >= board[x][y] || moveCnt >= board[S][K]) return;
        
        board[x][y] = moveCnt; // 현재 최소 이동 횟수 갱신
        
        // 졸을 잡으면 종료
        if (x == S && y == K) return;
        
        // 말 이동하기
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx < 1 || ny < 1 || nx > N || ny > M) continue; // 장기판을 벗어나면 continue
            
            move(nx, ny, moveCnt + 1); // 다른 위치로 이동
        }
    } // move 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 장기판 행의 수
        M = Integer.parseInt(st.nextToken()); // 장기판 열의 수
        
        board = new int[N + 1][M + 1]; // 장기판 배열 생성
        
        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken()); // 말이 있는 행
        int C = Integer.parseInt(st.nextToken()); // 말이 있는 열
        S = Integer.parseInt(st.nextToken()); // 졸이 있는 행
        K = Integer.parseInt(st.nextToken()); // 졸이 있는 열

        // 버퍼 닫기
        br.close();
        
        // 보드 별 이동 횟수 초기화
        for (int x = 1; x <= N; x++) Arrays.fill(board[x], Integer.MAX_VALUE);
        
        move(R, C, 0); // 이동 시작
        
        // 결과값 출력하기
        System.out.print(board[S][K]);
    } // main 종료
} // Main 종료