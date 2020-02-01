package collections;

import java.util.*;

public class CollectionActions {

    public static <T extends Comparable> void descendingSort(List<T> array) throws Exception {
        if (null == array) {
            throw new IllegalAccessException("Array is not defined!");
        }
        array.sort(Comparator.reverseOrder());
    }

    public static long sumOfElements(List<Long> array) throws Exception {
        if (array == null) {
            throw new IllegalAccessException("Array is not defined!!");
        }
        return array.stream().reduce((long) 0, (x, y) -> (x + y));
    }

    public static<T> boolean containsElement(Map map, T key) throws Exception {
        if (map == null) {
            throw new IllegalAccessException("Map is not defined!");
        }
        return map.containsKey(key);
    }

    public static<T> void addFirstToLinkedList(LinkedList<T> list, T elem) throws Exception {
        if (list == null) {
            throw new IllegalAccessException("LinkedList is not defined!");
        }
        list.addFirst(elem);
    }

    public static<T> void addLastToLinkedList(LinkedList list, T elem) throws Exception {
        if (list == null) {
            throw new IllegalAccessException("LinkedList is not defined!");
        }
        list.addLast(elem);
    }

    public static<T> T getElementByIndex(ArrayList<T> array, int index) throws Exception {
        if (array == null) {
            throw new IllegalAccessException("Array is empty or null!");
        }
        if (index > array.size()) {
            throw new IllegalAccessException("Index is out of bounds!");
        }
        return array.get(index);
    }

}
