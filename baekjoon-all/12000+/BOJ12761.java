/*
 * 백준 12761번: 돌다리
 * https://www.acmicpc.net/problem/12761
 * 난이도: 실버 1
 * 풀이 날짜: 2026-11-25
 * 간단 설명: 동규가 주미에게 도달하기 위한 최소한의 이동 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ12761 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int A = Integer.parseInt(st.nextToken()); // 스카이 콩콩이의 힘 A
        int B = Integer.parseInt(st.nextToken()); // 스카이 콩콩이의 힘 B
        int N = Integer.parseInt(st.nextToken()); // 동규의 현재 위치
        int M = Integer.parseInt(st.nextToken()); // 주미의 현재 위치
        
        // 버퍼 닫기
        br.close();
        
        int minMove = 0; // 최소 이동 횟수
        
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 장소를 담을 Queue
        queue.offer(new int[]{ N, 0 }); // 동규 시작 위치 offer
        
        boolean[] visited = new boolean[100000 + 1]; // 방문 처리 배열
        visited[N] = true; // 동규 시작 위치 방문 처리
        
        int[] jump = {1, -1, A, -A, B, -B}; // 점프 거리
        int[] superJump = {A, B}; // 힘을 모아서 점프하는 거리
        int nx; // 움직일 위치
        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // 현재 위치, 현재 이동 횟수
            
            if (cur[0] == M) { // 동규가 주미에게 도착한 경우
                minMove = cur[1]; // 최소 이동 횟수
                break;
            }
            
            for (int d = 0; d < 6; d++) {
                nx = cur[0] + jump[d]; // 움직일 위치
                
                if (nx < 0 || nx > 100000 || visited[nx]) continue; // 이미 방문했거나 돌다리를 벗어나면 continue
                
                queue.offer(new int[]{ nx, cur[1] + 1 }); // 다음 위치 offer
                visited[nx] = true; // 다음 위치 방문 처리
            }
            
            for (int d = 0; d < 2; d++) {
                nx = cur[0] * superJump[d]; // 움직일 위치
                
                if (nx < 0 || nx > 100000 || visited[nx]) continue; // 이미 방문했거나 돌다리를 벗어나면 continue
                
                queue.offer(new int[]{ nx, cur[1] + 1 }); // 다음 위치 offer
                visited[nx] = true; // 다음 위치 방문 처리
            }
        }
        
        // 결과값 출력
        System.out.print(minMove);
    }
}