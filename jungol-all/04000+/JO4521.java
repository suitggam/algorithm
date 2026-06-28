/*
 * 정올 4521번: 다이어트
 * https://jungol.co.kr/problem/4521
 * 난이도: 골드 4
 * 풀이 날짜: 2026-06-29
 * 간단 설명: 최소 영양분을 채우는 최소 가격과 조합을 구하는 문제

N과 식재료의 수가 크지 않기 때문에 
1번부터 N번까지 1개부터 15번까지 고르는 조합을 모두 백트래킹하면 됨
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class JO4521 {
    static int N; // 식재료의 개수
    static int mp, mf, ms, mv; // 최소로 필요한 단백질, 지방, 탄수화물, 비타민
    static int[][] table; // 식재료 표
    
    static int minCost; // 최소 가격
    static int[] min; // 최소 가격 조합
    static int[] selected; // 현재 선택한 조합
    
    static void comb(int curIdx, int prvP, int prvF, int prvS, int prvV, int prvCost, int selIdx) { // 식재료를 조합하는 메서드
        int curP = prvP + table[curIdx][0]; // 현재 단백질의 합
        int curF = prvF + table[curIdx][1]; // 현재 지방의 합
        int curS = prvS + table[curIdx][2]; // 현재 탄수화물의 합
        int curV = prvV + table[curIdx][3]; // 현재 비타민의 합
        int curCost = prvCost + table[curIdx][4]; // 현재 가격의 합
        
        selected[selIdx] = curIdx; // 조합 추가
        
        if (curP >= mp && curF >= mf && curS >= ms && curV >= mv && minCost > curCost) { // 조건을 만족한 경우
            reset(); // 최소 가격 조합 초기화
            minCost = curCost; // 최소 가격 갱신
            for (int i = 0; i <= selIdx; i++) min[i] = selected[i]; // 최소 가격 조합 갱신
        } else { // 조건을 만족하지 못한 경우
            for (int i = curIdx + 1; i <= N; i++) {
                if (curCost + table[i][4] >= minCost) continue; // 최소 가격을 초과하면 continue
                
                comb(i, curP, curF, curS, curV, curCost, selIdx + 1); // 다음 식재료 찾기
            }
        }
    } // comb 종료
    
    static void reset() { // 최소 가격 조합을 초기화하는 메서드
        Arrays.fill(min, 0); // 최소 가격 조합 배열 초기화
    } // reset 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 식재료의 개수
        min = new int[N]; // 최소 가격 조합
        selected = new int[N]; // 현재 선택한 조합
        
        st = new StringTokenizer(br.readLine(), " ");
        mp = Integer.parseInt(st.nextToken()); // 최소 단백질
        mf = Integer.parseInt(st.nextToken()); // 최소 지방
        ms = Integer.parseInt(st.nextToken()); // 최소 탄수화물
        mv = Integer.parseInt(st.nextToken()); // 최소 비타민
        
        table = new int[N + 1][5]; // 식재료 표
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            table[i][0] = Integer.parseInt(st.nextToken()); // 단백질
            table[i][1] = Integer.parseInt(st.nextToken()); // 지방
            table[i][2] = Integer.parseInt(st.nextToken()); // 탄수화물
            table[i][3] = Integer.parseInt(st.nextToken()); // 비타민
            table[i][4] = Integer.parseInt(st.nextToken()); // 가격
        }

        // 버퍼 닫기
        br.close();
        
        minCost = Integer.MAX_VALUE; // 최소 가격 초기화
        for (int i = 1; i <= N; i++) {
            comb(i, 0, 0, 0, 0, 0, 0); // 식재료 조합 찾기
        }
        
        StringBuilder sb = new StringBuilder(); // 최소 비용과 조합을 담기 위한 객체
        if (minCost != Integer.MAX_VALUE) { // 조합이 있는 경우
            sb.append(minCost).append("\n"); // 결과값 추가하기
            for (int i = 0; i < N; i++) {
                if (min[i] == 0) break; // 조합을 다 기록했으면 break
                
                sb.append(min[i]).append(" "); // 결과값 추가하기
            }
        } else sb.append(-1); // 조합이 없는 경우
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료