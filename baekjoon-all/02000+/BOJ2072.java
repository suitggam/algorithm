/*
 * 백준 2072번: 오목
 * https://www.acmicpc.net/problem/2072
 * 난이도: 실버 1
 * 풀이 날짜: 2026-11-24
 * 간단 설명: 순서대로 바둑돌을 두었을 때 몇 번째 수에서 승패가 갈리는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2072 {
    static int[][] board; // 바둑판 배열
    
    static boolean check(int idx, int x, int y) { // 게임이 끝났는지 확인하는 메서드
        int up = 0, down = 0, left = 0, right = 0; // 위, 아래, 왼쪽, 오른쪽
        int ul = 0, dr = 0, ur = 0, dl = 0; // 왼쪽위, 오른쪽아래, 오른쪽위, 왼쪽아래
        
        int nx = x; // x 시작점
        int ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 위쪽 방향 탐색
            nx += 1;
            
            if (nx == 0 || nx > 19) break; // 바둑판을 벗어나면 종료
            
            if (idx % 2 == 1 && board[nx][ny] == 1) up++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) up++; // 백 차례인 경우
            else break;
        }
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 아래쪽 방향 탐색
            nx -= 1;
            
            if (nx == 0 || nx > 19) break; // 바둑판을 벗어나면 종료
            
            if (idx % 2 == 1 && board[nx][ny] == 1) down++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) down++; // 백 차례인 경우
            else break;
        }
        
        if (up + down == 4) return true;
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 왼쪽 방향 탐색
            ny -= 1;
            
            if (ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종료
            
            if (idx % 2 == 1 && board[nx][ny] == 1) left++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) left++; // 백 차례인 경우
            else break;
        }
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 오른쪽 방향 탐색
            ny += 1;
            
            if (ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종료
            
            if (idx % 2 == 1 && board[nx][ny] == 1) right++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) right++; // 백 차례인 경우
            else break;
        }
        
        if (left + right == 4) return true;
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 왼쪽위 방향 탐색
            nx -= 1;
            ny -= 1;
            
            if (nx == 0 || nx > 19 || ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종료
            
            if (idx % 2 == 1 && board[nx][ny] == 1) ul++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) ul++; // 백 차례인 경우
            else break;
        }
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 오른쪽아래 방향 탐색
            nx += 1;
            ny += 1;
            
            if (nx == 0 || nx > 19 || ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종
            
            if (idx % 2 == 1 && board[nx][ny] == 1) dr++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) dr++; // 백 차례인 경우
            else break;
        }
        
        if (ul + dr == 4) return true;
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 왼쪽아래 방향 탐색
            nx += 1;
            ny -= 1;
            
            if (nx == 0 || nx > 19 || ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종
            
            if (idx % 2 == 1 && board[nx][ny] == 1) dl++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) dl++; // 백 차례인 경우
            else break;
        }
        
        nx = x; // x 시작점
        ny = y; // y 시작점
        for (int i = 0; i < 4; i++) { // 오른쪽위 방향 탐색
            nx -= 1;
            ny += 1;
            
            if (nx == 0 || nx > 19 || ny == 0 || ny > 19) break; // 바둑판을 벗어나면 종
            
            if (idx % 2 == 1 && board[nx][ny] == 1) ur++; // 흑 차례인 경우
            else if (idx % 2 == 0 && board[nx][ny] == 2) ur++; // 백 차례인 경우
            else break;
        }
        
        if (dl + ur == 4) return true;
        
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 놓는 돌의 개수
        board = new int[19 + 1][19 + 1]; // 바둑판 배열
        int x, y; // 바둑알의 좌표
        int end = -1; // 승패가 갈리는 수
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken()); // 바둑알의 x좌표
            y = Integer.parseInt(st.nextToken()); // 바둑알의 y좌표
            
            if (i % 2 == 1) board[x][y] = 1; // 흑차례
            else board[x][y] = 2; // 백차례
            
            if (check(i, x, y)) { end = i; break; }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력
        System.out.print(end);
    }
}