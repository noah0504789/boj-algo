package org.example.math;

import java.util.*;

public class 소수상근수 {
    private static final StringBuilder sb = new StringBuilder();
    private static final Scanner sc = new Scanner(System.in);
    private static Set<Integer> set = new HashSet<>(), allSet = new HashSet<>();
    private static List<Integer> list = new ArrayList<>();
    private static boolean[] primeNumbers;
    private static int n;

    public static void main(String... args) {
        n = sc.nextInt();

        // TODO: 소수 구하기
        primeNumbers = new boolean[n+1];
        Arrays.fill(primeNumbers, true);
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (!primeNumbers[i]) continue;

            for (int j = i+i; j <= n; j += i) primeNumbers[j] = false;
        }

        for (int i = 7; i <= n; i++) {
            if (primeNumbers[i]) list.add(i);
        }

        // TODO: 상근수 구하기
        for (int i : list) {
            set.clear();
            if (!isSanggeun(i)) continue;

            sb.append(i).append('\n');
        }

        System.out.print(sb);
    }

    private static boolean isSanggeun(int num) {
        if (num == 1) return true;
        if (allSet.contains(num)) return false;
        if (set.contains(num)) {
            allSet.addAll(set);
            return false;
        }

        set.add(num);

        return isSanggeun(powNum(num));
    }

    private static int powNum(int num) {
        int sum = 0;
        while (num != 0) {
            int digit = num % 10;
            sum+= Math.pow(digit, 2);
            num /= 10;
        }
        return sum;
    }
}
