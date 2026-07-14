/*
 * 정올 2261번: 경로 찾기
 * https://jungol.co.kr/problem/2261
 * 난이도: 골드 4
 * 풀이 날짜: 2026-07-14
 * 간단 설명: A부터 B까지 해밍 거리가 1로만 이동 가능한 경로를 구하는 문제

이진 코드가 전부 문자열이고 완전 탐색으로 비교하기에는 메모리 부담이 큼
이진 코드기 정수로 바꿔서 저장을 한 후 XOR 연산을 통해 비교하면서 완전 탐색하면 됨
단 BFS로 탐색을 하기 때문에 경로를 바로바로 추가하면 최단 경로가 안 나옴
그래서 부모 경로를 기록한 후에 역추적해서 최단 경로를 찾아야 함
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class JO2261 {
    static int N; // 이진 코드의 수
    static int[] binaryCode; // 이진 코드 배열
    
    static StringBuilder findPath(int A, int B) { // 경로를 찾는 메서드
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 경로를 담을 Queue
        boolean[] visited = new boolean[N + 1]; // 방문 처리 배열
        int[] parent = new int[N + 1]; // 부모 노드 배열
        
        queue.add(A); // 시작점 add
        visited[A] = true; // 시작점 방문 처리
        
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 위치
            
            if (cur == B) break; // 도착했으면 종료
            
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue; // 방문한 곳이면 continue
                
                if (diffCode(binaryCode[cur], binaryCode[i])) { // 두 숫자의 비트 차이가 1개인 경우
                    queue.add(i); // 다음 경로 add
                    visited[i] = true; // 방문 처리
                    parent[i] = cur; // 부모 노드 기록
                }
            }
        }
        
        StringBuilder sb = new StringBuilder(); // 경로를 저장할 객체
        
        if (visited[B]) { // 경로가 있는 경우
            ArrayList<Integer> path = new ArrayList<>(); // 경로 역추적을 위한 ArrayList
            
            int tmp = B; // 도착점
            
            while (tmp != A) {
                path.add(tmp); // 도착점 add
                tmp = parent[tmp]; // 다음 경로로 이동
            }
            path.add(A); // 시작점 add
            
            for (int i = path.size() - 1; i >= 0; i--) {
                sb.append(path.get(i)).append(" "); // 경로 추가
            }
        } else { // 경로가 없는 경우
            sb.append(-1); // 결과값 추가하기
        }
        
        return sb; // 경로 반환
    } // findPath 종료
    
    static boolean diffCode(int n1, int n2) { // 이진 코드의 차이를 확인하는 메서드
        int diff = n1 ^ n2; // XOR 연산
        
        if (diff == 0) return false; // 두 수가 같으면 false
        
        return (diff & (diff - 1)) == 0; // 한 비트만 다른지 여부 확인 후 return
    } // diffBit 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 이진 코드의 수
        int K = Integer.parseInt(st.nextToken()); // 이진 코드의 길이
        
        binaryCode = new int[N + 1]; // 이진 코드에 있는 1의 개수를 센 배열
        for (int i = 1; i <= N; i++) {
            binaryCode[i] = Integer.parseInt(br.readLine(), 2); // 이진수를 십진수로 변환
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken()); // 시작점
        int B = Integer.parseInt(st.nextToken()); // 도착점
        
        // 버퍼 닫기
        br.close();
        
        StringBuilder sb = findPath(A, B); // 경로
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료