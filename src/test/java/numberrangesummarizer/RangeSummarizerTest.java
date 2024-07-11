package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RangeSummarizerTest {

    private final NumberRangeSummarizer summarizer = new RangeSummarizer();

    @Test
    public void testSort() {
        String input = "22,13,7,24,3,8,21,12,14,1,15,6,31,23";
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
    void testCollectWithDuplicates() {
        String input = "1, 2, 3, 3, 4";
        List<Integer> expected = List.of(1, 2, 3, 4);
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(expected, result);

        String summarizedResult = summarizer.summarizeCollection(result);
        assertEquals("1-4", summarizedResult);
    }

    @Test
    public void testInvalidStringInput() {
        String input = "1,2,abc,4,5";
        assertThrows(InvalidInputException.class, () -> summarizer.collect(input));

    }

    @Test
    public void testDecimalNumberInput() {
        String input = "1,2,3.5,4,5";
        assertThrows(InvalidInputException.class, () -> summarizer.collect(input));
    }

}