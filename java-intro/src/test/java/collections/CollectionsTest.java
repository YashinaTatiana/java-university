package collections;


import org.testng.annotations.Test;

import java.util.*;

import static collections.CollectionActions.*;
import static com.google.common.primitives.Longs.asList;
import static java.lang.Long.valueOf;
import static org.testng.AssertJUnit.assertEquals;

public class CollectionsTest {

    @Test
    public void testDescendingOrder() throws Exception {
        List array = new ArrayList(10);
        array.addAll(asList(0,1,2,3,4,5,6,7,8,9));
        descendingSort(array);
        assertEquals(asList(9,8,7,6,5,4,3,2,1,0), array);
    }

    @Test
    public void testDescendingOrderEmpty() throws Exception {
        List array = new ArrayList(10);
        descendingSort(array);
    }

    @Test(expectedExceptions = Exception.class)
    public void testDescendingOrderNull() throws Exception {
        descendingSort(null);
    }

    @Test
    public void tesSumOfElements() throws Exception {
        List array = new ArrayList(4);
        array.addAll(asList(0, 10, 100, 20));
        assertEquals(130, sumOfElements(array));
    }

    @Test
    public void tesSumOfElementsEmpty() throws Exception {
        List array = new ArrayList(10);
        sumOfElements(array);
    }

    @Test(expectedExceptions = Exception.class)
    public void tesSumOfElementsNull() throws Exception {
        sumOfElements(null);
    }

    @Test(expectedExceptions = Exception.class)
    public void testElementExistsNullMap() throws Exception {
        containsElement(null, 0);
    }

    @Test
    public void testElementExists() throws Exception {
        Map map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        map.put(3,3);
        assertEquals(true, containsElement(map, 1));
    }

    @Test
    public void testElementNotExists() throws Exception {
        Map map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        map.put(3,3);
        assertEquals(false, containsElement(map, 10));
    }

    @Test
    public void testAddFirstToLinkedList() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(Arrays.asList(7,1,3));
        addFirstToLinkedList(linkedList, 0);
        assertEquals(0, linkedList.getFirst());
    }

    @Test
    public void testAddLastToLinkedList() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(Arrays.asList(0,1,3));
        addLastToLinkedList(linkedList, 10);
        assertEquals(10, linkedList.getLast());
    }

    @Test
    public void testAddFirstStringToLinkedList() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(Arrays.asList("home","music", "sun"));
        addFirstToLinkedList(linkedList, "go");
        assertEquals("go", linkedList.getFirst());
    }

    @Test
    public void testAddLastStringToLinkedList() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(Arrays.asList("home","music", "sun"));
        addLastToLinkedList(linkedList, "go");
        assertEquals("go", linkedList.getLast());
    }

    @Test
    public void testGetElementByIndex() throws Exception {
        ArrayList<Long> array = new ArrayList(10);
        array.addAll(asList(0,1,2,3,4,5,6,7,8,9));
        assertEquals(valueOf(2), getElementByIndex(array, 2) );
    }

    @Test(expectedExceptions = Exception.class)
    public void testGetElementByIndexOutOfBounds() throws Exception {
        ArrayList<Long> array = new ArrayList(10);
        array.addAll(asList(0,1,2,3,4,5,6,7,8,9));
        assertEquals(valueOf(3), getElementByIndex(array, 100) );
    }

    @Test(expectedExceptions = Exception.class)
    public void testGetElementNull() throws Exception {
        getElementByIndex(null, 100);
    }



}
