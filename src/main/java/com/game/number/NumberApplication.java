package com.game.number;

import com.game.number.work.Utils;
import com.game.number.work.Work;

import java.util.Map;
import java.util.Scanner;

public class NumberApplication {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int[][] gameData = Utils.initData(5);
            print(gameData);

            boolean isInput = true;
            boolean isAuto = true;
            //分数
            int score = 0;
            //可使用次数
            int times = 5;
            int i=0, j=0;
            while (true) {
                print(gameData);
                if (isInput) {
                    Map<String, Integer> map = Work.findList(gameData);
                    if (!map.isEmpty()) {
                        int[] result = Work.connectChangeZero(gameData, map);
                        if (result.length != 3) {
                            break;
                        }
                        if (isAuto) {
                            gameData[result[1]][result[2]] = result[0] + 1;
                            score += result[0] * map.size();

                        } else {
                            gameData[i][j] = result[0] + 1;
                            score += result[0] * map.size();
                            isAuto = true;
                        }
                        times += 1;

                        Work.fall(gameData);
                        Work.fill(gameData, 6);
                        continue;
                    }
                }
                System.out.println("Your score is " + score);
                System.out.println("Your times is " + times);
                if (times == 0) {
                    System.out.println("Your are game over");
                    break;
                }
                isInput = false;
                String data = scanner.nextLine();
                String[] input = data.split(" ");
                if (input.length != 2) {
                    System.out.println("Your input is invaild "+ data);
                    continue;
                }
                try {
                    i = Integer.parseInt(input[0]);
                    j = Integer.parseInt(input[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Your input is invaild "+ data);
                    continue;
                }

                gameData[i][j] = gameData[i][j] + 1;
                times -= 1;
                isInput = true;
                isAuto = false;

            }
        }

//    public static void main(String[] args) {
//
//        int[][] t = new int[][] {
//                {3, 3, 2, 1, 3},
//                {4, 1, 1, 2, 3},
//                {2, 3, 4, 4, 4},
//                {2, 2, 1, 4, 2},
//                {1, 1, 1, 1, 3}
//        };
//        print(t);
//        Work.findList(t);
//    }

    private static void print(int[][] data) {
        System.out.println("================");
        for (int[] line : data) {

            for (int i : line) {

                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
