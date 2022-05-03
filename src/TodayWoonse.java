import java.util.Random;

public class TodayWoonse {
    private static final String[] WOONSE = {"나서지 말라", "능력을 발휘하기 좋은 날", "격한 운동으로 스트레스를 풀어라", "도움을 받기보다는 도움을 주는 날", "좋은 일로 모든 사람이 행복하다",
        "일이 바쁜날", "투자는 금물", "뒤를 돌아보자", "과식을 피하라", "무모한 집착으로 문제가 커진다.", "기다리던 때가 오니 역량을 발휘하라"};

    public static String selWoonse() {
        return WOONSE[new Random().nextInt(WOONSE.length)];
    }
}
