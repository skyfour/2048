package com.game.number.work;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skyfour
 * @date 2019-06-11
 * @email skyzhang@easemob.com
 */
public class Work {

    private static final Integer DIRECTION_ALL = 0;
    private static final Integer DIRECTION_UP = 1;
    private static final Integer DIRECTION_DOWN = 2;
    private static final Integer DIRECTION_LEFT = 3;
    private static final Integer DIRECTION_RIGHT = 4;
    private static final String DECOLLATOR = "==";

    /**
     * 找到连接的数字
     */
    public static Map<String, Integer> findList(int[][] data) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                map = new HashMap<>();
                if (data[i][j] != 0) {
                    findMaxList(data, i, j, data[i][j], DIRECTION_ALL, map);
                }
                if (map.size() >= 3) {
                    return map;
                }
            }
        }
        return map;
    }

    /*
    将连接好的变成0，并且返回第一个的位置
    */
    public static int[] connectChangeZero(int[][] data, Map<String, Integer> map) {
        int ir = -1;
        int jr = -1;
        int value = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] temp = entry.getKey().split(DECOLLATOR);
            if (temp.length != 2) {
                return new int[] {};
            }
            int i = Integer.parseInt(temp[0]);
            int j = Integer.parseInt(temp[1]);
            if (entry.getValue() != null && entry.getValue() <= value) {
                ir = i;
                jr = j;
                value = data[i][j];

            }
            data[i][j] = 0;
        }

        return new int[] {value, ir, jr};
    }

    /**
     * 数字下落，将0覆盖
     */
    public static void fall(int[][] data) {
        if (data.length == 0) {
            return;
        }
        int length = data.length;
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < length; i++) {
                if (data[i][j] == 0 && i > 0 && data[i - 1][j] != 0) {
                    int temp = data[i][j];
                    data[i][j] = data[i - 1][j];
                    data[i - 1][j] = temp;
                    i = 0;
                }
            }
        }
    }

    /**
     * 将0通过随机数填充
     */
    public static void fill(int[][] data, int n) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    data[i][j] = Utils.getRandom(n);
                }
            }
        }
    }

    /**
     * direction 方向 0 全都 1 上 2 下 3 左 4 右
     */
    private static void findMaxList(int[][] data, int i, int j, int temp, int direction,
            Map<String, Integer> m) {
        //fmt.Printf("%d,%d,%d\n", i, j, temp)
        String key = i + DECOLLATOR + j;
        //横向加一
        if (i < data.length && j + 1 < data[i].length && direction != DIRECTION_RIGHT) {
            if (temp == data[i][j + 1] && temp == data[i][j]) {
                String key1 = i + DECOLLATOR + (j + 1);
                boolean existed = m.containsKey(key) && m.containsKey(key1);
                if (existed) {
                    return;
                }
                putMap(key, key1, m);
                findMaxList(data, i, j + 1, temp, DIRECTION_LEFT, m);
            }

        }
        //横向减一
        if (i < data.length && j - 1 >= 0 && direction != DIRECTION_LEFT) {
            if (temp == data[i][j - 1] && temp == data[i][j]) {
                String key1 = i + DECOLLATOR + (j - 1);
                boolean existed = m.containsKey(key) && m.containsKey(key1);
                if (existed) {
                    return;
                }
                putMap(key, key1, m);
                findMaxList(data, i, j - 1, temp, DIRECTION_RIGHT, m);
            }

        }
        //纵向加一
        if (i + 1 < data.length && direction != DIRECTION_UP) {
            if (temp == data[i + 1][j] && temp == data[i][j]) {
                String key1 = (i + 1) + DECOLLATOR + j;
                boolean existed = m.containsKey(key) && m.containsKey(key1);
                if (existed) {
                    return;
                }
                putMap(key, key1, m);
                findMaxList(data, i + 1, j, temp, DIRECTION_DOWN, m);
            }
        }
        //纵向减一
        if (i - 1 > 0 && direction != DIRECTION_DOWN) {
            if (temp == data[i - 1][j] && temp == data[i][j]) {
                String key1 = (i - 1) + DECOLLATOR + j;
                boolean existed = m.containsKey(key) && m.containsKey(key1);
                if (existed) {
                    return;
                }
                putMap(key, key1, m);
                findMaxList(data, i - 1, j, temp, DIRECTION_UP, m);
            }
        }
    }

    private static void putMap(String key, String key1, Map<String, Integer> m) {

        m.put(key, m.containsKey(key) ? m.get(key) + 1 : 0);
        m.put(key1, m.containsKey(key1) ? m.get(key1) + 2 : 2);
    }

}
