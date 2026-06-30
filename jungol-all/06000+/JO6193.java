/*
 * 정올 6193번: 제곱수의 합의 마지막 자릿수
 * https://jungol.co.kr/problem/6193
 * 난이도: 브론즈 2
 * 풀이 날짜: 2026-06-30
 * 간단 설명: 1 x 1 + 2 x 2 + ••• + N x X의 마지막 자리의 숫자(1의 자릿수)를 구하는 문제

완전 탐색을 돌기엔 N의 범위가 너무 큼
제곱수로 더해서 1의 자리만 구해보면 1~20까지의 사이클이 형성됨
N % 20을 통해 구하면 됨
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JO6193 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine()); // 정수 N
        
        // 버퍼 닫기
        br.close();
        
        int[] oneDigit = new int[21]; // 일의 자릿수 배열
        for (int i = 1; i <= 20; i++) {
            oneDigit[i] = i * i % 10; // 제곱수의 1의 자리만 구하기
        }
        
        for (int i = 1; i <= 20; i++) { // 1의 자리 누적 합 변환
            oneDigit[i] = (oneDigit[i - 1] + oneDigit[i]) % 10;
        }
        
        // 결과값 출력하기
        System.out.print(oneDigit[(int) (N % 20)]);
    } // main 종료
} // Main 종료