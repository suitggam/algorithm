/*
 * 백준 17086번: 아기 상어 2
 * https://www.acmicpc.net/problem/17086
 * 난이도: 실버 2
 * 풀이 날짜: 2026-11-23
 * 간단 설명: 아기 상어와의 안전 거리가 가장 큰 칸을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ17086 {
    static int N, M, maxSafetyDis; // 공간의 세로 길이, 공간의 가로 길이, 최대 안전 거리
    static int[][] space; // 공간 배열
    
    // 8방향 배열
    static int[] dx = { -1, -1, 0, 1, 1,  1,  0, -1 };
    static int[] dy = {  0,  1, 1, 1, 0, -1, -1, -1 };
    
    static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // Queue 생성
        boolean[][] visited = new boolean[N][M]; // 방문 처리 배열
        queue.offer(new int[] { x, y }); // 시작점 offer
        visited[x][y] = true; // 시작점 방문 처리
        
        int nx, ny;
        while (!queue.isEmpty()) {
            int cur[] = queue.poll(); // 현재 위치
            
            for (int d = 0; d < 8; d++) {
                nx = cur[0] + dx[d]; // 다음 x위치
                ny = cur[1] + dy[d]; // 다음 y위치
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue; // 범위를 벗어나면 continue
                
                if (space[nx][ny] == 1) { // 아기 상어를 만난 경우
                    maxSafetyDis = Math.max(maxSafetyDis, Math.max(Math.abs(x - nx), Math.abs(y - ny))); // 최대 안전 거리 갱신
                    return;
                }
                
                queue.offer(new int[] { nx, ny }); // 다음 지점 offer
                visited[nx][ny] = true; // 다음 지점 방문 처리
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 공간의 세로 길이
        M = Integer.parseInt(st.nextToken()); // 공간의 가로 길이
        
        space = new int[N][M]; // 공간 배열
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) {
                space[x][y] = Integer.parseInt(st.nextToken()); // 공간 정보
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (space[x][y] == 1) continue; // 아기 상어가 있는 곳이면 continue
                
                bfs(x, y); // 탐색
            }
        }
        
        
        // 결과값 출력
        System.out.print(maxSafetyDis);
    }
}