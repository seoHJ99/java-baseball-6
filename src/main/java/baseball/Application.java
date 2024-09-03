package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;

public class Application {

    private final int NUMBER_MAX = 3;

    public static void main(String[] args) {
        Application application = new Application();
        application.playGame();
    }

    public void playGame(){
        int[] randomNumbers = makeThreeRanNum();
        System.out.println(Arrays.toString(randomNumbers));
        while (true){
            boolean end = playOneCycle(randomNumbers);
            if(end){
                boolean continuous = getContinuous();
                if(continuous){
                    randomNumbers = makeThreeRanNum();
                    continue;
                }
                break;
            }
        }
    }



    public boolean playOneCycle(int[] randomNumbers) {
        int[] userNums = getUserNum();
        int[] ballCounts = makeBallCount(randomNumbers, userNums);
        System.out.println(Arrays.toString(randomNumbers));
        System.out.println(Arrays.toString(ballCounts));
        viewResult(ballCounts);
        return 0checkOut(ballCounts[0]);
    }

    public int makeRandomNum() {
        return Randoms.pickNumberInRange(1, 9);
    }

    public int[] makeThreeRanNum() {
        int[] threeNum = new int[NUMBER_MAX];
        for (int i = 0; i < threeNum.length; ) {
            int randomNum = makeRandomNum();
            if (checkContainNum(threeNum, randomNum)) {
                threeNum[i] = randomNum;
                i++;
            }
        }
        return threeNum;
    }

    public boolean checkContainNum(int[] numbers, int randomNum) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == randomNum) {
                return false;
            }
        }
        return true;
    }

    public int[] getUserNum() {
        int[] userNums = new int[NUMBER_MAX];
        System.out.print("숫자를 입력해 주세요 : ");
        String input = Console.readLine();
        String[] splited = input.split("");
        try {
            for (int i = 0; i < userNums.length; i++) {
                int parsedInt = Integer.parseInt(splited[i]);
                userNums[i] = parsedInt;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력값입니다. 게임을 종료합니다.");
        }
        return userNums;
    }

    public boolean getContinuous(){
        String input= Console.readLine();
        if(!input.equals("1") && !input.equals("2")){
            System.out.println("잘못된 입력값입니다.");
            throw new IllegalArgumentException();
        }
        return input.equals("1");
    }

    public int[] makeBallCount(int[] randomNums, int[] userNums) {
        int[] ballCount = new int[2];
        for (int i = 0; i < randomNums.length; i++) {
            int strikeCheck = strikeCheck(randomNums[i], userNums[i]);

            ballCount[0] += strikeCheck;
            if (strikeCheck != 1) {
                ballCount[1] += ballCheck(randomNums[i], userNums);
            }
        }
        return ballCount;
    }

    public int strikeCheck(int checkNum, int userNum) {
        if (checkNum == userNum) {
            return 1;
        }
        return 0;
    }

    public int ballCheck(int checkNum, int[] userNums) {
        for (int i = 0; i < userNums.length; i++) {
            if (userNums[i] == checkNum) {
                return 1;
            }
        }
        return 0;
    }

    public boolean checkOut(int strike) {
        if (strike == 3) {
            return true;
        }
        return false;
    }

    public void viewStrike(int strikeCount) {
        if (strikeCount > 0) {
            System.out.println(strikeCount + "스트라이크");
        }
    }

    public void viewBall(int ballCount) {
        if (ballCount > 0) {
            System.out.println(ballCount + "볼");
        }
    }

    public void mergeViews(int[] ballcount){
        viewBall(ballcount[1]);
        viewStrike(ballcount[0]);
        if(ballcount[0] == 0 && ballcount[1]==0){
            System.out.println("낫씽");
        }
    }

    public void viewOut() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    public void viewResult(int[] ballCount) {
        boolean outCheck = checkOut(ballCount[0]);
        if (outCheck) {
            viewOut();
            return;
        }
        mergeViews(ballCount);
    }
}
