package application;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String actionCode;
    private Map<String, String> parameterBitsMap;

    // 명령 문자열 받아서 처리하는 생성자 메서드
    public Request(String command) {
        String[] commandBits = command.split("\\?", 2);
        // 입력 받은 명령 구문 ? 기준으로 2개로 나누어 배열 저장. "delete?xxx" => {"delete", "xxx"}
        this.actionCode = commandBits[0]; // ? 이전은 동작 명령이 들어있으므로 동작 코드를 의미하는 actionCode에 할당

        if (commandBits.length == 1) { // 길이가 1이라면 명령어만 입력된 상태이므로 여기서 생성자 메서드 종료 필요
            String[] commandsUsingId = {"delete", "modify"}; // 길이가 1이라도 delete, modify 같이 id 코드가 필요한 명령들에 한해서 에러 메시지 출력 위함
            for (String commandUsingId : commandsUsingId) {
                if (commandUsingId.equals(this.actionCode)) { // 동작 명령이 id가 필요한 명령이라면
                    System.out.printf("Please enter the valid %s command. (e.g. %s?id=1)\n", this.actionCode, this.actionCode); // 제대로 된 명령 입력하라는 구문 출력
                }
            }
            return; // 생성자 메서드 종료
        }

        String[] parameter = commandBits[1].split("&");
        // 명령어 여러개 입력 가정해서 & 기준으로 나누어 배열 저장. "id=1&id=2" => {"id=1", id=2"}

        this.parameterBitsMap = new HashMap<>(); // 파라미터 분리에서 저장할 HashMap 선언.

        for (String parametersBits : parameter) { // 각 파라미터에 대해 진행
            String[] parameterBits = parametersBits.split("=", 2);
            // 파라미터 = 기준으로 2개로 나누어 배열 저장. "id=1" => {"id", "1"}

            if (parameterBits.length == 1) { // "="이 포함된 파라미터가 아니어서 해당 현상 발생.
                System.out.printf("Please enter the valid %s command. (e.g. %s?id=1)\n", this.actionCode, this.actionCode); // 제대로 된 명령 입력하라는 구문 출력
                continue; // 여기서 종료되더라도 HashMap은 null이 아니라 값이 할당되지 않은 형태로 존재
            }

            this.parameterBitsMap.put(parameterBits[0], parameterBits[1]); // HashMap에 key와 value로 저장. key = "id", value = "#"
        }
    }

    // actioncode 반환 메서드
    public String getActionCode() {
        return this.actionCode;
    }

    // HashMap에서 key를 통해 파라미터 반환하는 메서드. 생성자 메서드 과정에서 HashMap이 null일 가능성 존재.
    public String getParameter(String key) {
        try {
            return this.parameterBitsMap.get(key);
        } catch(NullPointerException e) {
            return "-1"; // null error 발생 시 -1 반환.
        }
    }

    // key를 입력받아 정수형 파라미터를 반환하는 메서드. 값이 없거나, 숫자가 아니어서 에러 발생 가능.
    public long getLongParameter(String key) {
        try {
            return Long.parseLong(getParameter(key)); // 파라미터 value 받아와서 long 형 정수로 반환
        } catch(NumberFormatException e) {
            if (!parameterBitsMap.isEmpty()) { // 값이 비어있지 않은 경우. 입력 코드에서 = 은 있던 상황.
                System.out.println("Please enter the valid number format."); // = 뒤에 숫자를 제대로 입력하라는 구문 출력
            }
            return -1; // number format error 발생 시 -1 반환
        }
    }

}
