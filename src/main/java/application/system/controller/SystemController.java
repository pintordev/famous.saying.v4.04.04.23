package application.system.controller;

import application.Container;

import java.util.Comparator;

// 시스템 관련 컨트롤 메서드가 담긴 클래스
public class SystemController {
    // 명언 앱 초기 실행 메서드
    public void init() {
        System.out.println("== Famous Saying Applicaion =="); // 명언 앱 시작
        // 가능한 명령어 출력
        System.out.println("1. record");
        System.out.println("2. list");
        System.out.println("3. delete");
        System.out.println("4. modify");
        System.out.println("0. terminate");
    }

    // 명언 앱 명령 입력 메서드
    public String getCommand() {
        System.out.println("=".repeat(30)); // 동작 간 구분
        System.out.printf("Command: "); // 명령 입력 요청
        return Container.getScanner().nextLine().trim().toLowerCase(); // 컨테이너 내 스캐너를 받아와서 한줄 입력을 받아 좌우 공백 제거하고 소문자로 변경해서 문자열 반환
    }

    // 명언 앱 종료 메서드
    public void terminate() {
        System.out.println("=".repeat(30));
        System.out.println("Application has been terminated."); // 명언 앱 종료
    }

    // 명언 앱 도움말 메서드
    public void help() {
        System.out.println("=".repeat(30)); // 동작 간 구분
        // 가능한 명령어 출력
        System.out.println("1. record");
        System.out.println("2. list");
        System.out.println("3. delete");
        System.out.println("4. modify");
        System.out.println("0. terminate");
    }

    // 명언 앱 입력 오류 메서드
    public void error() {
        System.out.println("=".repeat(30)); // 동작 간 구분
        System.out.println("Please enter the valid command. If you want to know commands, then enter \"help\"."); // 제대로 된 명령 입력 요청. 도움말 검색 유도
    }
}
