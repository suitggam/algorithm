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
        
        int remain = (int) (N % 20); // 남은 숫자
        int lastDigit = 0; // 제곱수의 합의 마지막 자릿수
        for (int i = 1; i <= remain; i++) {
            lastDigit = (lastDigit + i * i) % 10; // 남은 자리 제곱수의 합의 마지막 자릿수 구하기
        }
        
        // 결과값 출력하기
        System.out.print(lastDigit);
    } // main 종료
} // Main 종료