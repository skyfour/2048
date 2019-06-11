package com.game.number.work;

import java.util.Random;

/**
 * @author skyfour
 * @date 2019-06-11
 * @email skyzhang@easemob.com
 */
public class Utils {
    public static int[][] initData(int n) {
        int[][] data = new int[n][n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = Utils.getRandom(n);
                data[i][j] = a;

            }
        }
        return data;
    }
    public static int getRandom(int n) {
        Random rand = new Random();
        return rand.nextInt(n-1) + 1;
    }
}
