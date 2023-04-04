package application;

public class Util {
    // 기수를 입력받아 서수 문자열로 반환하는 메서드
    public static String ordinal(long id) {
        if (id % 10 == 1) {
            return id + "st";
        } else if (id % 10 == 2) {
            return id + "nd";
        } else if (id % 10 == 3) {
            return id + "rd";
        } else {
            return id + "th";
        }
    }
}
