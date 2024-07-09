package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RangeSummarizer implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> collectedNumbers = Stream.of(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        collectedNumbers = collectedNumbers.stream()
                .sorted((num1, num2) -> num1.compareTo(num2))
                .collect(Collectors.toList());

        return collectedNumbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> collectedNumbers = new ArrayList<>(input);
        StringBuilder outputString = new StringBuilder();

        for (int i = 0; i < collectedNumbers.size(); i++) {
            int rangeFirst = collectedNumbers.get(i);
            while (i < collectedNumbers.size() - 1 && collectedNumbers.get(i) + 1 == collectedNumbers.get(i + 1)) {
                i++;
            }
            int sequenceEnd = collectedNumbers.get(i);

            if (rangeFirst == sequenceEnd) {
                outputString.append(rangeFirst);
            } else {
                outputString.append(rangeFirst).append("-").append(sequenceEnd);
            }

            if (i < collectedNumbers.size() - 1) {
                outputString.append(", ");
            }
        }

        return outputString.toString();
    }
}