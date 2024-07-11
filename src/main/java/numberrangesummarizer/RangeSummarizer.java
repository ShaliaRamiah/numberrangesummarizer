package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RangeSummarizer implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        List<Integer> collectedNumbers;

        try {
            // split input, trim white space, parse to int, filter duplicates
            collectedNumbers = Stream.of(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .filter(num -> {
                        if (!uniqueNumbers.add(num)) {
                            return false; // skipping duplicates
                        }
                        return true; // adding unique
                    })
                    .collect(Collectors.toList());
            // sort numbers
            collectedNumbers.sort((num1, num2) -> num1.compareTo(num2));

        } catch (NumberFormatException e) {
            throw new InvalidInputException(e.getMessage()); // invalid number format
        }

        return collectedNumbers; // sorted and unique integers
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> collectedNumbers = new ArrayList<>(input);
        StringBuilder outputString = new StringBuilder();

        for (int i = 0; i < collectedNumbers.size(); i++) {
            int rangeFirst = collectedNumbers.get(i);
            // checking numbers to form ranges
            while (i < collectedNumbers.size() - 1 && collectedNumbers.get(i) + 1 == collectedNumbers.get(i + 1)) {
                i++;
            }
            int sequenceEnd = collectedNumbers.get(i);

            // adding ranges/single nums to output
            if (rangeFirst == sequenceEnd) {
                outputString.append(rangeFirst);
            } else {
                outputString.append(rangeFirst).append("-").append(sequenceEnd);
            }
            // adding comma and space, not last element
            if (i < collectedNumbers.size() - 1) {
                outputString.append(", ");
            }
        }

        return outputString.toString(); // return summarised string
    }
}