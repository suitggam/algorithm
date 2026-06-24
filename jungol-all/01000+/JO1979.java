/*
 * 정올 1979번: The Average
 * https://jungol.co.kr/problem/1979
 * 난이도: 실버 1
 * 풀이 날짜: 2026-06-24
 * 간단 설명: 내림차순으로 정렬했을 때 앞에서 N1개
 *           오름차순으로 정렬했을 때 앞에서 N2개를 제외한 숫자들의 평균을 구하는 문제

내림차순으로 정렬했을 때 앞에서 N1개 제외
오름차순으로 정렬했을 때 앞에서 N2개 제외

그러면 내림차순이든 오름차순이든 한 차순으로 정렬시켜놓은 뒤
앞뒤에서 N1개와 N2개를 빼고 나머지를 더한 걸 (N - N1 - N2)개로 나누면 된다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class JO1979 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N1 = Integer.parseInt(st.nextToken()); // 내림차순으로 정렬했을 때 앞에서 제외할 개수
        int N2 = Integer.parseInt(st.nextToken()); // 오름차순으로 정렬했을 떄 앞에서 제외할 개수
        int N  = Integer.parseInt(st.nextToken()); // 숫자의 개수

        long[] num = new long[N]; // 숫자 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            num[i] = Long.parseLong(st.nextToken()); // 숫자 저장
        }

        // 버퍼 닫기
        br.close();
        
        Arrays.sort(num); // 비내림차순 정렬
        
        long sum = 0; // 제외되지 않는 숫자의 합
        for (int i = N2; i < N - N1; i++) {
            sum += num[i];
        }
        
        long avg = sum / (N - N1 - N2); // 제외되지 않는 숫자의 합의 평균
        
        // 결과값 출력하기
        System.out.print(avg);
    }
}