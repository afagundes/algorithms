package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {

    @Test
    void testGetIndexes() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertThat(list).hasSize(5);
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo(list.getFirst());
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(list.get(list.size() - 1));

        // First and last
        list.addFirst(-1);
        assertThat(list).hasSize(6);
        assertThat(list.getFirst()).isEqualTo(-1);

        list.addLast(100);
        assertThat(list).hasSize(7);
        assertThat(list.getFirst()).isEqualTo(-1);
        assertThat(list.getLast()).isEqualTo(100);

        // By collection
        List<Integer> values = List.of(1000, 2000, 3000);
        list.addAll(values);

        assertThat(list).hasSize(10);
        assertThat(list.get(7)).isEqualTo(1000);
        assertThat(list.get(8)).isEqualTo(2000);
        assertThat(list.get(9)).isEqualTo(3000);

        List<Integer> otherValues = List.of(999, 998);
        list.addAll(7, otherValues);

        assertThat(list).hasSize(12);
        assertThat(list.get(7)).isEqualTo(999);
        assertThat(list.get(8)).isEqualTo(998);
        assertThat(list.get(9)).isEqualTo(1000);
        assertThat(list.get(10)).isEqualTo(2000);
        assertThat(list.get(11)).isEqualTo(3000);
    }

    @Test
    void testRemoveItems() {
        List<String> list = new LinkedList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");

        assertThat(list).hasSize(5);
        assertThat(list.getFirst()).isEqualTo("First");
        assertThat(list.getLast()).isEqualTo("Fifth");

        // First and last
        list.removeFirst();
        list.removeLast();

        assertThat(list).hasSize(3);
        assertThat(list.getFirst()).isEqualTo("Second");
        assertThat(list.getLast()).isEqualTo("Fourth");

        // By index
        assertThat(list.get(1)).isEqualTo("Third");
        list.remove(1);
        assertThat(list.get(1)).isEqualTo("Fourth");

        // By Value
        list.remove("Second");
        assertThat(list).hasSize(1);
        assertThat(list.getFirst()).isEqualTo("Fourth");

        // By collection
        List<String> values = List.of("Other 1", "Other 2", "Other 3");
        list.addAll(values);

        assertThat(list).hasSize(4);
        assertThat(list.getFirst()).isEqualTo("Fourth");
        assertThat(list.getLast()).isEqualTo("Other 3");

        list.removeAll(values);
        assertThat(list).hasSize(1);
        assertThat(list.getFirst()).isEqualTo("Fourth");
        assertThat(list.getLast()).isEqualTo("Fourth");
    }

    @Test
    void testSetItems() {
        List<Integer> list = new LinkedList<>(1, 2, 3);

        assertThat(list).hasSize(3);
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.getLast()).isEqualTo(3);

        list.set(1, 20);

        assertThat(list).hasSize(3);
        assertThat(list.getFirst()).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(20);
        assertThat(list.getLast()).isEqualTo(3);

        list.set(0, 10);

        assertThat(list).hasSize(3);
        assertThat(list.getFirst()).isEqualTo(10);
        assertThat(list.get(1)).isEqualTo(20);
        assertThat(list.getLast()).isEqualTo(3);

        list.set(list.size() - 1, 30);

        assertThat(list).hasSize(3);
        assertThat(list.getFirst()).isEqualTo(10);
        assertThat(list.get(1)).isEqualTo(20);
        assertThat(list.getLast()).isEqualTo(30);
    }

    @Test
    void testSublist() {
        List<Function<Integer, Integer>> list = new LinkedList<>();
        list.add(arg -> arg + arg); // sums itself
        list.add(arg -> arg * arg); // multiplies by itself
        list.add(arg -> (int) Math.pow(arg, 3)); // powers by 3
        list.add(arg -> arg + 5); // sums 5 to the arg

        int[][] expected = {
                { 3, 6 },
                { 3, 9 },
                { 3, 27 },
                { 3, 8 }
        };

        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i).apply(expected[i][0])).isEqualTo(expected[i][1]);
        }

        var sublist = list.subList(0, 2);
        assertThat(sublist).hasSize(2);

        for (int i = 0; i < sublist.size(); i++) {
            assertThat(sublist.get(i).apply(expected[i][0])).isEqualTo(expected[i][1]);
        }

        var sublist2 = list.subList(2, list.size());
        assertThat(sublist2).hasSize(2);

        for (int i = 0; i < sublist2.size(); i++) {
            assertThat(sublist2.get(i).apply(expected[i + 2][0])).isEqualTo(expected[i + 2][1]);
        }
    }

    @Test
    void testIterator() {
        List<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);

        int index = 0;
        for (Integer item : list) {
            assertThat(item).isEqualTo(list.get(index++));
        }

        index = 0;
        Iterator<Integer> it = list.iterator();

        assertThat(list).hasSize(5).contains(5);

        while (it.hasNext()) {
            var item = it.next();
            assertThat(item).isEqualTo(list.get(index++));

            if (item.equals(5)) {
                it.remove();
            }
        }

        assertThat(list).hasSize(4).doesNotContain(5);
    }

    @Test
    void testToArray() {
        List<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
        assertThat(list).hasSize(5);

        var arr = list.toArray();
        assertThat(arr).isEqualTo(new Integer[]{ 1, 2, 3, 4, 5 });

        var arrRef = new Integer[5];
        list.toArray(arrRef);
        assertThat(arrRef).isEqualTo(new Integer[]{ 1, 2, 3, 4, 5 });
    }

}