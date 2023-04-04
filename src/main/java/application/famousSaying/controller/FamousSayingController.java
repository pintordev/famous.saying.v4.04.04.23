package application.famousSaying.controller;

import application.Container;
import application.Request;
import application.Util;
import application.famousSaying.entity.FamousSaying;

import java.util.ArrayList;
import java.util.List;

// 명언 관련 컨트롤 메서드가 담긴 클래스
public class FamousSayingController {
    private long id = 1; // famous saying id
    private List<FamousSaying> sayingList = new ArrayList<>(); // 명언 객체를 담을 List 선언

    // id 입력 받아서 있으면 명언 객체를 반환하고 없으면 null 값 반환하는 메서드
    public FamousSaying findById(long id) {
        for (FamousSaying saying : this.sayingList) {
            if (saying.getId() == id) { // 입력 받은 id랑 같은 id를 같은 명언 객체가 있다면
                return saying; // 해당 명언 객체 반환
            }
        }
        return null; // 반복문 종료가 되었다면 같은 id를 갖는 명언 객체가 없는 것이므로 null 값 반환
    }

    // 등록 메서드
    public void record() {
        System.out.printf("Famous Saying: "); // 명언 입력 요청
        String contents = Container.getScanner().nextLine().trim(); // 입력 받은 명언을 contents에 저장
        System.out.println("Author: "); // 작가 입력 요청
        String author = Container.getScanner().nextLine().trim(); // 입력 받은 작가를 author에 저장
        this.sayingList.add(new FamousSaying(id, author, contents)); // 명언 리스트에 입력 받은 정보로 만든 명언 객체 저장
        System.out.printf("%s famous saying has been recoded.\n", Util.ordinal(id++)); // 등록 완료 문구 출력, id 증가
    }

    // 목록 메서드
    public void list() {
        if (sayingList.size() > 0) {
            System.out.println("=".repeat(30));
            System.out.println("Id / Author / Contents"); // list 헤더 출력
            System.out.println("-".repeat(22)); // 헤더와 목록 구분
            for (int i = this.sayingList.size() - 1; i >= 0; i--) { // 목록 역순 출력
                System.out.println(this.sayingList.get(i).getList()); // getList 함수로 "Id / Author / Contents" 형태의 목록을 받아와서 출력
            }
        } else {
            System.out.println("There is no list in this application.");
        }
    }

    // 삭제 메서드
    public void delete(Request request) {
        long deleteId = request.getLongParameter("id"); // 리퀘스트 객체에서 삭제할 id 받아와서 저장

        if (deleteId == -1) { // -1은 에러 코드
            return; // 삭제하지 않고 메서드 종료
        }

        FamousSaying famousSaying = this.findById(deleteId); // 삭제할 id로 해당 명언 객체를 받아와서 할당

        if (famousSaying == null) { // 명언 객체가 존재하지 않는다면
            System.out.printf("%s famous saying does not exist.\n", Util.ordinal(deleteId)); // 존재하지 않는다는 구문 출력
            return; // 메서드 종료
        }

        this.sayingList.remove(famousSaying); // 이 라인까지 온 건 명언 객체 존재 => 삭제 시행
        System.out.printf("%s famous saying has been deleted.\n", Util.ordinal(deleteId)); // 삭제 완료 구문 출력
    }

    // 수정 메서드
    public void modify(Request request) {
        long modifyId = request.getLongParameter("id"); // 리퀘스트 객체에서 수정할 id 받아와서 저장

        if (modifyId == -1) { // -1은 에러 코드
            return; // 수정하지 않고 메서드 종료
        }

        FamousSaying famousSaying = this.findById(modifyId); // 수정할 id로 해당 명언 객체를 받아와서 할당

        if (famousSaying == null) { // 명언 객체가 존재하지 않는다면
            System.out.printf("%s famous saying does not exist.\n", Util.ordinal(modifyId)); // 존재하지 않는다는 구문 출력
            return; // 메서드 종료
        }

        System.out.printf("Famous Saying (present): %s\n", famousSaying.getContents()); // 이 라인까지 온 건 명언 객체 존재 => 해당 id에 현재 존재하는 명언 출력
        System.out.printf("Famous Saying (to be modified): "); // 해당 id에 새로 입력할 명언 입력 요청
        famousSaying.setContents(Container.getScanner().nextLine().trim()); // 명언을 입력받아서 좌우 공백 제거하고 저장

        System.out.printf("Author (present): %s\n", famousSaying.getAuthor()); // 해당 id에 현재 존재하는 작가 출력
        System.out.printf("Author (to be modified): "); // 해당 id에 새로 입력할 작가 입력 요청
        famousSaying.setAuthor(Container.getScanner().nextLine().trim()); // 작가를 입력받아서 좌우 공백 제거하고 저장
    }

}
