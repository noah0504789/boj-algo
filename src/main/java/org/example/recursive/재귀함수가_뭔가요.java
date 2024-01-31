package org.example.recursive;

import java.io.*;

/**
 * @author noah kim
 * @date 2024-02-01
 * @link https://www.acmicpc.net/problem/17478
 * @keyword_solution
    Requirements : 주어진 입력의 깊이만큼 QnA 메시지를 재귀적으로 출력한다.

    [챗봇메시지]
    - 시작메시지
    - QnA메시지 (재귀적)
 * @input
    - QnA메시지의 재귀 깊이
 * @output
    - 챗봇 메시지
 * @time_complex O(N)
 * @perf 14624kb / 136ms
 */

class 재귀함수가_뭔가요 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String HELLO_MSG = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
    private static final String QUESTION_MSG_FIRST = "\"재귀함수가 뭔가요?\"\n";
    private static final String QUESTION_MSG_SECOND = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    private static final String QUESTION_MSG_THIRD = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    private static final String QUESTION_MSG_FOURTH = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    private static final String ANSWER_MSG = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    private static final String CLOSE_MSG = "라고 답변하였지.\n";
    private static final String LINE_BREAK_UNIT_MSG = "____";
    private static StringBuffer sb = new StringBuffer();
    private static int recursiveCnt;    
    
    public static void main(String[] args) throws IOException {
        recursiveCnt = Integer.parseInt(br.readLine());
        
        sb.append(HELLO_MSG);

        printQnA(0);

        System.out.print(sb);
    }
    
    private static void printQnA(final int depth) {
        if (depth == recursiveCnt) {
            sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + QUESTION_MSG_FIRST);
            sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + ANSWER_MSG);
            sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + CLOSE_MSG);

            return;
        }

        sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + QUESTION_MSG_FIRST);
        sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + QUESTION_MSG_SECOND);
        sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + QUESTION_MSG_THIRD);
        sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + QUESTION_MSG_FOURTH);

        printQnA(depth+1);

        sb.append(LINE_BREAK_UNIT_MSG.repeat(depth) + CLOSE_MSG);
    }
}
