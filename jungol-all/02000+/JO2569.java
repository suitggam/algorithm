/*
 * 정올 2569번: 사다리
 * https://jungol.co.kr/problem/2569
 * 난이도: 실버 1
 * 풀이 날짜: 2026-06-25
 * 간단 설명: 1초마다 1씩 움직이는 막대기를 이용하여 제일 높은 층까지 가장 짧은 시간 내에 올라가는 문제

각 막대기는 1초마다 현재 방향으로 움직이고 벽에 부딪히면 반대로 움직인다.
각 막대기가 해당 층에서 움직일 수 있는 거리는 L - 막대기의 길이이고
시간을 해당 층에서 움직일 수 있는 거리로 나눈 것을 % 연산을 하면 현재 방향이 어디인지가 나온다.
이를 토대로 현재 층의 막대기의 시작점과 끝점, 다음 층의 막대기의 시작점과 끝점을 구해서 올라가면 된다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO2569 {
    static int getPos(int time, int len, int dir, int canMoveDis, int L, boolean start) { // 위치를 구하는 함수
        int pos = 0; // 위치
        
        if (canMoveDis == 0) { // 움직일 수 있는 거리가 0인 경우
            if (start) pos = dir == 0 ? 0 : L - len;
            else pos = dir == 0 ? len : L;
            
            return pos;
        }
        
        boolean forward = (time / canMoveDis) % 2 == 0; // 다음 층의 원래 방향
        if (forward) { // 원래 방향인 경우
            if (start) pos = dir == 0 ? time % canMoveDis : L - time % canMoveDis - len; // 막대기의 왼쪽 위치인 경우
            else pos = dir == 0 ? len + time % canMoveDis : L - time % canMoveDis; // 막대기의 오른쪽 위치인 경우
        } else { // 원래 방향과 반대인 경우
            if (start) pos = dir == 0 ? L - time % canMoveDis - len : time % canMoveDis; // 막대기의 왼쪽 위치인 경우
            else pos = dir == 0 ? L - time % canMoveDis : len + time % canMoveDis; // 막대기의 오른쪽 위치인 경우
        }
        
        return pos;
    }    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 층 수
        int L = Integer.parseInt(st.nextToken()); // 층의 길이
        
        int[][] building = new int[N + 1][3]; // 건물 배열
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            building[i][0] = Integer.parseInt(st.nextToken()); // 막대기의 길이
            building[i][1] = Integer.parseInt(st.nextToken()); // 막대기의 방향
            building[i][2] = L - building[i][0]; // 막대기가 움직일 수 있는 거리
        }

        // 버퍼 닫기
        br.close();
        
        int cur = 1;   // 현재 층
        int time = 0;; // 걸린 시간
        int curStart, curEnd; // 현재 층의 막대기 왼쪽 위치, 현재 층의 막대기 오른쪽 위치
        int nxtStart, nxtEnd; // 다음 층의 막대기 왼쪽 위치, 다음 층의 막대기 오른쪽 위치
        int curCanMoveDis, nxtCanMoveDis; // 현재 층의 움직일 수 있는 거리, 다음 층의 움직일 수 있는 거리
        int curLen, curDir; // 현재 층의 막대기 길이, 현재 층의 막대기 방향
        int nxtLen, nxtDir; // 다음 층의 막대기 길이, 다음 층의 막대기 방향
        
        while (cur < N) { // 건물 오르기
            curLen = building[cur][0]; // 현재 층의 막대기 길이
            curDir = building[cur][1]; // 현재 층의 막대기 방향
            curCanMoveDis = building[cur][2]; // 현재 층의 움직일 수 있는 거리
            
            curStart = getPos(time, curLen, curDir, curCanMoveDis, L, true);
            curEnd   = getPos(time, curLen, curDir, curCanMoveDis, L, false);
            
            nxtLen = building[cur + 1][0]; // 다음 층의 막대기 길이
            nxtDir = building[cur + 1][1]; // 다음 층의 막대기 방향
            nxtCanMoveDis = building[cur + 1][2]; // 다음 층의 움직일 수 있는 거리
            
            nxtStart = getPos(time, nxtLen, nxtDir, nxtCanMoveDis, L, true);
            nxtEnd   = getPos(time, nxtLen, nxtDir, nxtCanMoveDis, L, false);
            
            if (curStart <= nxtEnd && nxtStart <= curEnd) { // 다음 층의 막대기로 이동 가능한 경우
                cur++;
            } else { // 다음 층의 막대기로 이동 못 하는 경우
                time++;
            }
        }
        
        // 결과값 출력하기
        System.out.print(time);
    }
}