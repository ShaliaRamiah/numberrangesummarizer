package numberrangesummarizer;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

        NumberRangeSummarizer summarizer = new RangeSummarizer();

        Collection<Integer> collectedNumbers = summarizer.collect(input);

        String outputString = summarizer.summarizeCollection(collectedNumbers);

        System.out.println("Input: " + input);
        System.out.println("Output: " + outputString);
    }
}