package com.hengtong.led.heibaidiedai;

import org.springframework.stereotype.Component;

@Component
public class HbddMain {

    private static final int NUM = 8;

    private static int[][] main = new int[8][8];

    static {
        for (int i = 0; i < NUM; i++) {
            for (int f = 0; f < NUM; f++) {
                main[i][f] = 0;
            }
        }
    }

    public static void main(String[] args) {
        sout(0, 0);
        sout(0, 1);
        sout(3, 4);
        sout(3, 6);

        sout(0, 0);
        sout(0, 1);
        sout(3, 4);
        sout(3, 6);
    }

    public static boolean sout(int a, int b) {
        click(a, b, main);
        int xy = 0;
        int numx = 0;
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM; j++) {
                if (main[i][j] != xy) {
                    numx++;
                }
                System.out.print(main[i][j] + " ");
            }
            System.out.println();
        }
        if (numx == 0) {
            System.out.println("wow~完成了");
            return true;
        }
        System.out.println();
        return false;
    }

    public static int[][] click(Integer x, Integer y, int[][] main) {
        main[x][y] = change(main[x][y]);
        if (x > 0) {
            main[x - 1][y] = change(main[x - 1][y]);
        }
        if (x < (NUM - 1)) {
            main[x + 1][y] = change(main[x + 1][y]);
        }
        if (y > 0) {
            main[x][y - 1] = change(main[x][y - 1]);
        }
        if (y < (NUM - 1)) {
            main[x][y + 1] = change(main[x][y + 1]);
        }
        return main;
    }


    public static int change(int num) {
        if (num == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
