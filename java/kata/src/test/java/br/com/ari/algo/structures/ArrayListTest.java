package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrayListTest {

    @Test
    void testArrayListHasDefaultCapacity10() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThat(list).isEmpty();
        assertThat(list.capacity()).isEqualTo(10);
    }

    @Test
    void testArrayListCanResize() {
        ArrayList<Integer> list = new ArrayList<>(2);
        list.add(1);
        list.add(2);

        assertThat(list).hasSize(2);
        assertThat(list.capacity()).isEqualTo(2);

        list.add(3);

        assertThat(list).hasSize(3);
        assertThat(list.capacity()).isEqualTo(4); // doubled capacity

        list.add(4);
        list.add(5);

        assertThat(list).hasSize(5);
        assertThat(list.capacity()).isEqualTo(8); // doubled capacity again
    }

    @Test
    void testInsert() {
        ArrayList<Integer> list = new ArrayList<>();

        assertThatThrownBy(list::getFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::getLast).isInstanceOf(NoSuchElementException.class);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(list.size() - 1)).isEqualTo(5);
        assertThat(list.getFirst()).isEqualTo(list.get(0));
        assertThat(list.getLast()).isEqualTo(list.get(list.size() - 1));

        assertThat(list).hasSize(5);
        assertThatThrownBy(() -> list.get(6)).isInstanceOf(ArrayIndexOutOfBoundsException.class);

        list.prepend(100);
        assertThat(list.get(0)).isEqualTo(100);
        assertThat(list).hasSize(6);

        assertThat(list.get(2)).isEqualTo(2);
        list.insertAt(2, 900);
        assertThat(list.get(2)).isEqualTo(900);

        assertThat(list).hasSize(7);
        assertThat(list.capacity()).isEqualTo(10);

        list.prepend(200);
        list.prepend(300);
        list.prepend(400);
        list.prepend(500);

        assertThat(list).hasSize(11);
        assertThat(list.capacity()).isEqualTo(20);

        int[] expectedState = new int[] { 500, 400, 300, 200, 100, 1, 900, 2, 3, 4, 5 };

        for (int i = 0; i < expectedState.length; i++) {
            assertThat(list.get(i)).isEqualTo(expectedState[i]);
        }
    }

    @Test
    void testRemove() {
        ArrayList<String> list = new ArrayList<>();

        assertThatThrownBy(list::removeFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::removeLast).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> list.get(0)).isInstanceOf(NoSuchElementException.class);

        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        list.add("item 4");
        list.add("item 5");

        assertThat(list).hasSize(5);
        assertThat(list.get(0)).isEqualTo("item 1");

        assertThat(list.removeFirst()).isEqualTo("item 1");
        assertThat(list).hasSize(4);
        assertThat(list.get(0)).isEqualTo("item 2");

        assertThat(list.get(2)).isEqualTo("item 4");
        assertThat(list.removeAt(2)).isEqualTo("item 4");
        assertThat(list.get(2)).isEqualTo("item 5");
        assertThat(list).hasSize(3);

        assertThat(list.get(list.size() - 1)).isEqualTo("item 5");
        assertThat(list.removeLast()).isEqualTo("item 5");
        assertThat(list.get(list.size() - 1)).isEqualTo("item 3");
        assertThat(list).hasSize(2);

        assertThat(list.get(0)).isEqualTo("item 2");
        assertThat(list.get(1)).isEqualTo("item 3");
        assertThat(list.capacity()).isEqualTo(10);
    }

    @Test
    void testIterator() {
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        ArrayList<Integer> list = new ArrayList<>(values);

        assertThat(list).hasSize(5);
        assertThat(list.capacity()).isEqualTo(5);

        int index = 0;
        for (Integer item : list) {
            assertThat(item).isEqualTo(values.get(index++));
        }

        index = 0;
        Iterator<Integer> it = list.iterator();

        while (it.hasNext()) {
            Integer item = it.next();
            assertThat(item).isEqualTo(values.get(index++));

            if (item.equals(2)) {
                it.remove();
            }
        }

        assertThat(list).hasSize(4);
        assertThat(list.capacity()).isEqualTo(5);

        values = List.of(1, 3, 4, 5);
        index = 0;

        for (Integer item : list) {
            assertThat(item).isEqualTo(values.get(index++));
        }
    }

    @Test
    void testSort() {
        ArrayList<Integer> list = new ArrayList<>(5, 1, 4, 9, 10, 2, 6, 3, 8, 7);
        assertThat(list).hasSize(10);

        list.sort(Integer::compareTo);

        for (int i = 0; i < 10; i++) {
            assertThat(list.get(i)).isEqualTo(i + 1);
        }
    }

    @Test
    void testFind() {
        ArrayList<Integer> list = new ArrayList<>(5, 1, 4, 9, 10, 2, 6, 3, 8, 7);
        assertThat(list.find(10)).isEqualTo(4);

        list.sort(Integer::compareTo);
        assertThat(list.find(10, Integer::compareTo, true)).isEqualTo(9);
    }

    @Test
    void testEquals() {
        ArrayList<Integer> list1 = new ArrayList<>(1, 2, 3, 4, 5);
        ArrayList<Integer> list2 = new ArrayList<>(1, 2, 3, 4, 5);

        assertThat(list1.equals(list2)).isTrue();

        list1.add(6);
        assertThat(list1.equals(list2)).isFalse();

        list1.removeLast();
        assertThat(list1.equals(list2)).isTrue();

        list1.removeAt(1);
        list2.removeAt(1);

        assertThat(list1.equals(list2)).isTrue();

        list1.removeFirst();
        list2.removeFirst();

        assertThat(list1.equals(list2)).isTrue();
    }

}
