import java.util.*;

public class Prob563 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Max Combinations: ");
        long maxCombinations = scanner.nextInt();
        scanner.close();

        System.out.println("Max Combinations: " + maxCombinations); 

        final long NoSolution = 0L;
        int numSolutions = 1;
        long[] solutions = new long[(int) maxCombinations + 1];
        Arrays.fill(solutions, NoSolution);

        long result = 0;

        PriorityQueue<Long> areas = new PriorityQueue<>();
        areas.add(1L);

        List<Long> sides = new ArrayList<>();

        final long IgnoreAbove = 2300000000000000L;

        while (numSolutions < maxCombinations) {
            if (areas.isEmpty()) {
                System.out.println("Priority queue is empty, breaking loop.");
                break;
            }

            long current = areas.poll();
            System.out.println("Processing area: " + current); 

            if (current * current <= IgnoreAbove) {
                sides.add(current);
            }

            int[] multiples = {23, 19, 17, 13, 11, 7, 5, 3, 2};
            for (int multiple : multiples) {
                long next = multiple * current;
                if (next <= IgnoreAbove) {
                    areas.add(next);
                }
                if (current % multiple == 0) {
                    break;
                }
            }

            if (numSolutions >= 56) {
                if (current % 800 != 0) continue;
            } else if (numSolutions >= 8) {
                if (current % 80 != 0) continue;
            } else if (current % 40 != 0) {
                continue;
            }

            long sqrtCurrent = (long) Math.sqrt(current);
            int index = Collections.binarySearch(sides, sqrtCurrent);
            if (index < 0) index = -index - 1;
            index--;

            int numFound = 0;
            while (index >= 0) {
                long shortSide = sides.get(index--);
                long longSide = current / shortSide;

                if (longSide * 10 > shortSide * 11) break;

                if (longSide * shortSide == current) {
                    numFound++;
                }
            }

            if (numFound < 2 || numFound > maxCombinations) continue;

            if (solutions[numFound] == NoSolution) {
                solutions[numFound] = current;
                result += current;
                numSolutions++;
                System.out.println("Found solution: " + current + " | numFound: " + numFound + " | result: " + result); // Debug
            }
        }

        System.out.println("Final Result: " + result);
    }
}
