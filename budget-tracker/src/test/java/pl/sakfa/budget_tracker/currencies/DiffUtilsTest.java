package pl.sakfa.budget_tracker.currencies;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by msakowski on 27/10/15.
 */
public class DiffUtilsTest {
    public String note1 = "Sentence 1, sentence 2, sentence 3, sentence 4";
    public String note2 = "Sentence 1, sentence 3, sentence 5";

    @Test
    public void foo() {
        int[][] arr = new int[5][4];
        System.out.println(arr[0][3]);
    }

    @Test
    public void testDiff() {
        Patch<String> patch = DiffUtils.diff(Arrays.asList(note1.split("[\\.,]")), Arrays.asList(note2.split("[\\.,]")));

        for (Delta<String> delta: patch.getDeltas()) {
            System.out.println(delta);
        };
        //outputs
        //[DeleteDelta, position: 1, lines: [ sentence 2]]
        //[ChangeDelta, position: 3, lines: [ sentence 4] to [ sentence 5]]

    }
}
