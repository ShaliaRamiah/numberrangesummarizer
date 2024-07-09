package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RangeSummarizerTest {

    private final NumberRangeSummarizer summarizer = new RangeSummarizer();

    @Test
    public void testCollect() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        Collection<Integer> result = new ArrayList<>(summarizer.collect(input));
        assertEquals(expected, result);
    }

    @Test
    public void testSummarizeCollection() {
        Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String result = summarizer.summarizeCollection(input);
        assertEquals(expected, result);
    }

    @Test
    public void testInvalidStringInput() {
        String input = "1,2,abc,4,5";
        Collection<Integer> collectedNumbers = summarizer.collect(input);
        String expected = "1-2, 4-5";
        String result = summarizer.summarizeCollection(collectedNumbers);
        assertEquals(expected, result);
    }

    @Test
    public void testDecimalNumberInput() {
        String input = "1,2,3.5,4,5";
        Collection<Integer> collectedNumbers = summarizer.collect(input);
        String expected = "1-2, 4-5";
        String result = summarizer.summarizeCollection(collectedNumbers);
        assertEquals(expected, result);
    }

}
