package application;

import java.util.Scanner;

public class Container {
    private static Scanner scanner; // 스캐너 선언

    // 스캐너 할당 메서드
    public static void init() {
        scanner = new Scanner(System.in);
    }

    // 스캐너 종료 메서드
    public static void close() {
        scanner.close();
    }

    // 스캐너 반환 메서드
    public static Scanner getScanner() {
        return scanner;
    }
}
