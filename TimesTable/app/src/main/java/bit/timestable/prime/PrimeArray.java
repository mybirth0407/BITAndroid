package bit.timestable.prime;

import bit.timestable.config.Config;

import java.util.*;

public class PrimeArray {
    public static List<Integer> sieve() {
        Boolean[] primeCheckArray =
            new Boolean[(Config.MAX_TIMES * Config.MAX_TIMES) + 1];
        Arrays.fill(primeCheckArray, true);

        int checkLength = primeCheckArray.length - 1;
        List<Integer> list = new LinkedList<>();

        for (int i = 2; i * i <= checkLength; i++) {
            if (primeCheckArray[i] == true) {
                for (int j = i * i; j <= checkLength; j += i) {
                    primeCheckArray[j] = false;
                    if (!list.contains(j)) {
                        list.add(j);
                    }
                }
            }
        }
        return list;
    }
}
