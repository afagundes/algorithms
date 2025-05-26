package br.com.ari.algo.algorithms;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class MergeSortedListsTest {

    @Test
    void testMergeSortedLists() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);
        list1.add(9);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);
        list2.add(10);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        expected.add(10);

        List<Integer> result = MergeSortedLists.merge(list1, list2);
        Assertions.assertThat(result).isEqualTo(expected);
    }

}