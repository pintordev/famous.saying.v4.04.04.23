package application;

import application.famousSaying.controller.FamousSayingController;
import application.system.controller.SystemController;

public class App {
    // application 구동 메서드
    public static void run() {
        SystemController systemController = new SystemController(); // 시스템 컨트롤러 객체 선언
        FamousSayingController famousSayingController = new FamousSayingController(); // 명언 컨트롤러 객체 선언

        systemController.init(); // 명언 앱 초기 부분 실행 메서드

        while (true) { // 명령 지속적으로 받기 위해 반복문 적용.
            Request request = new Request(systemController.getCommand()); // 입력 명령어를 가공하는 리퀘스트 객체 선언

            switch (request.getActionCode()) { // 리퀘스트에 저장된 actioncode에 따라 각각 구동할 메서드 실행
                case "terminate": // terminate 명령일 때 (종료)
                    systemController.terminate(); // 명언 앱 종료 메서드 실행
                    return; // 메서드 종료
                case "record": // recode 명령일 때 (등록)
                    famousSayingController.record(); // 명언 앱 등록 메서드 실행
                    break; // switch 문 종료 -> 다음 명령 입력 요청으로 이어짐
                case "list": // list 명령일 때 (목록)
                    famousSayingController.list(); // 명언 앱 목록 메서드 실행
                    break;
                case "delete": // delete 명령일 때 (삭제)
                    famousSayingController.delete(request); // 명언 앱 삭제 메서드 실행. 구동에 필요한 리퀘스트 객체를 매개변수로 넘겨줌
                    break;
                case "modify": // modify 명령일 때 (수정)
                    famousSayingController.modify(request); // 명언 앱 수정 메서드 실행. 구동에 필요한 리퀘스트 객체를 매개변수로 넘겨줌
                    break;
                case "help": // help 명령일 때 (도움말)
                    systemController.help(); // 명언 앱 도움말 메서드 실행
                    break;
                default: // 명령어가 제대로 입력되지 않았을 때
                    systemController.error(); // 명언 앱 입력 오류 메서드 실행
                    break;
            }
        }
    }
}
