package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {

    private final int NUMBER_MAX = 3;

    public static void main(String[] args) {
        Application application = new Application();
        application.getUserNum();
    }

    public int makeRandomNum(){
        return Randoms.pickNumberInRange(1,9);
    }

    public int[] makeThreeRanNum(){
        int[] threeNum = new int[NUMBER_MAX];
        for(int i =0; i< threeNum.length;){
            int randomNum = makeRandomNum();
            if(checkNotContain(threeNum, randomNum)){
                threeNum[i] = randomNum;
                i++;
            }
        }
        return threeNum;
    }

    public boolean checkNotContain(int[] numbers, int randomNum){
        for( int i =0; i< numbers.length; i++){
            if(numbers[i] == randomNum){
                return false;
            }
        }
        return true;
    }

    public int[] getUserNum(){
        int[] userNums = new int[NUMBER_MAX];
        for(int i =0; i<NUMBER_MAX; i++){
            String input = Console.readLine();
            try {
                int inputNum = Integer.parseInt(input);
                userNums[i] = inputNum;
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("잘못된 입력값입니다. 게임을 종료합니다.");
            }
        }
        return userNums;
    }

    public int[] makeBallCount(int[] randomNums, int[] userNums){
        int[] ballCount = new int[2];
        for(int i =0; i< randomNums.length; i++){
            int strikeCheck = strikeCheck(randomNums[i], userNums[i]);
            ballCount[0] += strikeCheck;
            if(strikeCheck == 1){
                ballCount[1] += ballCheck(randomNums[i], userNums);
            }
        }
        return ballCount;
    }

    public int strikeCheck(int checkNum, int userNum) {
        if(checkNum == userNum){
            return 1;
        }
        return 0;
    }

    public int ballCheck(int checkNum, int[] userNums) {
        for(int i=0; i<userNums.length; i++){
            if(userNums[i] == checkNum){
                return 1;
            }
        }
        return 0;
    }

    // 스트라이크를 체크하고, 스트라이크가 아닌 숫자에 대해서만 볼 체크도 하는게 더 나은듯

}
