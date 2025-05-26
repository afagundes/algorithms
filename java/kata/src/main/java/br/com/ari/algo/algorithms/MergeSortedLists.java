package br.com.ari.algo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedLists {

    private MergeSortedLists() {
    }

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>(list1.size() + list2.size());
        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                mergedList.add(list1.get(i++));
            } else {
                mergedList.add(list2.get(j++));
            }
        }

        while (i < list1.size()) {
            mergedList.add(list1.get(i++));
        }

        while (j < list2.size()) {
            mergedList.add(list2.get(j++));
        }

        return mergedList;
    }

}
