/* Ryan Houlihan
 * 
 * Linked List created to use instead of java's standard linked list class
 */

package Deer;

import java.util.*;

public class MyLinkedList {
    public MyLinkedList(){
        first = null;
        last = null;
    }
    // Gets the first node in the list
    public Object getFirst(){
        if(first != null)
           return first.data;
        return null;
    }
    // Gets the last node in the list
    public Object getLast(){
        if(last != null)
           return last.data;
        return null;
    }
    // Creates a node that points to the given object and adds the node to the front of the lise
    public void addFirst(Object obj){
        Node newNode = new Node();
        newNode.data = obj;
        newNode.next = first;
        first = newNode;
        if(last == null)
            last = newNode;
    }
    // Creates a node that points to the given object and adds the node to the end of the list
    public void addLast(Object obj){
        Node newNode = new Node();
        newNode.data = obj;
        newNode.previous = last;
        last = newNode;
        if(first == null)
            first = newNode;
    }
    // Returens the size of the list
    public int size(){
        int size = 0;
        ListIterator iterator = listIterator();
        if(first == null)
            return 0;
        while(iterator.hasNext()){
            size++;
            iterator.next();
        }
        return size;
    }
    // Gets the object at a particular index
    public Object get(int index){
        ListIterator iterator = listIterator();
        int position = -1;
        Object obj = null;
        while(iterator.hasNext() && position < index){
            obj = iterator.next();
            position++;
        }
        if(position == index){
            return obj;
        }
        return obj;
    }
    // Returns the index of a object in the list
    public int indexOf(Object obj){
        ListIterator iterator = listIterator();
        int index = 0;
        while(iterator.hasNext()){
            Object nodeObj = iterator.next();
            if(nodeObj.equals((PointStorage) obj)){
                return index;
            }
            index++;
        }
        return -1;
    }
    // Removes the first node from the list
    public Object removeFirst(){
        if (first == null)
            throw new NoSuchElementException();
        Object element = first.data;
        first = first.next;
        return element;
    }
    // Removes the node at a certian position in the list
    public Object remove(int index){
        ListIterator iterator = listIterator();
        int counter = 0;
        Object obj = null;
        while(iterator.hasNext()){
            obj = iterator.next();
            if(counter == index){
                iterator.remove();
                return obj;
            }
            counter++;
        }
        return null;
    }
    // Creates a iterator for the linkedList
    public ListIterator listIterator(){
        return new MyLinkedListIterator();
    }
    // Node class that stores a object and the next node and the previous node in the list
    private class Node {
        public Object data;
        public Node next;
        public Node previous;
    }
    // List iterator to be used to run through the linked list
    private class MyLinkedListIterator implements ListIterator {
        public MyLinkedListIterator(){
            position = null;
            previous = null;
            after = null;
        }
        // Returns the object stored in the node in position
        public Object next(){
            if(!hasNext())
                throw new NoSuchElementException();
            previous = position;

            if(position == null)
                position = first;
            else
                position = position.next;

            after = position.next;
            return position.data;
        }
        // Returns true if the list is not at the end
        public boolean hasNext(){
            if (position == null){
                return first != null;
            }
            else
                return position.next != null;
        }
        // Removes the node in position from the list
        public void remove(){
            if (previous == position)
                throw new IllegalStateException();
            if (position == first){
                removeFirst();
            }
            else {
                previous.next = position.next;
                after.previous = position.previous;
            }
            position = previous;
        }
        private Node after;
        private Node position;
        private Node previous;
    }
    private Node first;
    private Node last;
}
