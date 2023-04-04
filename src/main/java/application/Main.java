package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Container.init(); // 컨테이너 초기화 -> 내부에 스캐너 할당
        App.run(); // application 구동 메서드 실행
        Container.close(); // 컨테이너 종료 -> 내부 스캐너 반환
    }
}