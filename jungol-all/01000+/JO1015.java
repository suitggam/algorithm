/*
 * 정올 1015번: 브라우저
 * https://jungol.co.kr/problem/1015
 * 난이도: 골드 5
 * 풀이 날짜: 2026-06-26
 * 간단 설명: 명령어와 페이지를 입력받아서 현재 페이지가 무엇인지 출력하는 문제

초기 페이지와 현재 페이지가 있고 명령어는 BACK, FORWARD, VISIT가 있다.
각 명령어에 맞게 1차원 배열 2개(backward stack, forward stack)에서
push, pop하며 현재 페이지를 교체하면 된다.

**조건을 중요하게 봐야 하고 조건대로 구현하려면 로직을 흐름에 맞게 설계해야 함**
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1015 {
    static String curPage = "http://www.acm.org/"; // 현재 페이지
    
    static int forwardTop = -1; // forward stack의 top
    static String[] forwardStack = new String[100]; // forward stack
    
    static int backwardTop = -1; // backward stack의 top
    static String[] backwardStack = new String[100]; // backward stack
    
    static boolean visit(String newPage) {
        backwardStack[++backwardTop] = curPage; // 현재 페이지 push
        curPage = newPage; // 입력된 URL로 현재 페이지 설정
        forwardTop = -1; // forwardStack 비우기
        
        return true; // 명령어 정상 작동
    } // visit 종료
    
    static boolean forward(String page) {
        if (forwardTop == -1) return false; // forwrad stack이 비어있는 경우
        
        backwardStack[++backwardTop] = page; // 현재 페이지 push
        curPage = forwardStack[forwardTop--]; // 새로 설정할 페이지 pop
        
        return true; // 명령어 정상 작동
    } // forward 종료
    
    static boolean back(String page) {
        if (backwardTop == -1) return false; // backward stack이 비어있는 경우
        
        forwardStack[++forwardTop] = page; // 현재 페이지 push
        curPage = backwardStack[backwardTop--]; // 새로 설정할 페이지 pop
        
        return true; // 명령어 정상 작동
    } // back 종료
        
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String command; // 명령어
        String newPage; // 새로 입력된 페이지
        boolean notIgnored = true; // 명령어의 무시 여부
        StringBuilder sb = new StringBuilder(); // 현재 페이지를 저장할 객체
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            command = st.nextToken(); // 명령어
            
            if (command.equals("VISIT")) { // 명령어가 VISIT인 경우
                newPage = st.nextToken(); // 새로 입력된 페이지
                notIgnored = visit(newPage); // VISIT 명령어 실행
            } else if (command.equals("FORWARD")) { // 명령어가 FORWARD인 경우
                notIgnored = forward(curPage); // FORWARD 명령어 실행
            } else if (command.equals("BACK")) { // 명령어가 BACK인 경우
                notIgnored = back(curPage); // BACK 명령어 실행
            } else { // 명령어가 QUIT인 경우
                break;
            }
            
            // 현재 페이지 추가하기
            if (notIgnored) sb.append(curPage).append("\n"); // 명령어가 정상 작동한 경우
            else sb.append("Ignored\n"); // 명령어가 무시된 경우
        }

        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료