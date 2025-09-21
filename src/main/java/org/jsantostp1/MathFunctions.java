package org.jsantostp1;

import java.util.Arrays;

public class MathFunctions {
    private final MathLogger logger;

    public MathFunctions(MathLogger logger) {
        this.logger = logger;
    }

    public int multiplyByTwo(int number) {
        int result = number * 2;
        logger.log("multiplyByTwo", new int[]{number});
        return result;
    }

    public int[] generateMultiplicationTable(int number, int limit) {
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++) {
            result[i] = number * (i + 1);
        }
        logger.log("generateMultiplicationTable", new int[]{number, limit});
        return result;
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            logger.log("isPrime", new int[]{number});
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                logger.log("isPrime", new int[]{number});
                return false;
            }
        }
        logger.log("isPrime", new int[]{number});
        return true;
    }

    public double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            logger.log("calculateAverage", new int[0]);
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        double avg = Arrays.stream(numbers).average().getAsDouble();
        logger.log("calculateAverage", numbers);
        return avg;
    }
}
