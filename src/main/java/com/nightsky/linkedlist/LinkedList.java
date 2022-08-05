package com.nightsky.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A basic doubly-linked list implementation. This implementation also provides
 * iteration and stream support.
 *
 * @param <T> The type of the elements in the list.
 */
public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Tests if this list is empty.
     *
     * @return <code>true</code> - If and only if this list has <code>size == 0</code>.<br />
     *         <code>false</code> - otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an object to the end of this list.
     *
     * @param value The object to add to the end of this list.
     */
    public void add(T value) {
        if ( isEmpty() ) {
            head = new Node<>(null, null, value);
            tail = head;
        } else {
            Node<T> newNode = new Node<>(tail, null, value);
            tail.setNext(newNode);
            tail = newNode;
        }

        ++size;
    }

    /**
     * Adds an object to the specified location in this list.
     *
     * @param x The index where the object should be added to this list.
     * @param value The object to add to this list.
     */
    public void add(int x, T value) {
        if ( x > size )
            throw new IllegalArgumentException();

        if ( x == size ) {
            // Shortcut for adding to the end of the list
            add(value);
            return;
        }

        Node<T> nodeToShift = head;
        int i = 0;
        while ( i < x ) {
            nodeToShift = nodeToShift.getNext();
            ++i;
        }

        Node<T> newNode = new Node<>(nodeToShift.getPrevious(), nodeToShift, value);

        if ( nodeToShift.getPrevious() != null )
            nodeToShift.getPrevious().setNext(newNode);
        else
            head = newNode;


        nodeToShift.setPrevious(newNode);

        ++size;
    }

    /**
     * Removes the object at the specified location from this list.
     *
     * @param x The index of the object to remove.
     * @return The object that was removed from this list.
     */
    public T remove(int x) {
        if ( x >= size )
            throw new NoSuchElementException();

        Node<T> currentNode = head;
        int i = 0;
        while (i < x) {
            currentNode = currentNode.getNext();
            ++i;
        }

        T val = currentNode.getValue();
        Node<T> previous = currentNode.getPrevious();
        Node<T> next = currentNode.getNext();

        if ( previous != null )
            previous.setNext(next);
        else
            head = next;

        if ( next != null )
            next.setPrevious(previous);
        else
            tail = previous;

        // Avoid memory leaks
        currentNode.removeReferences();

        --size;

        return val;
    }

    /**
     * Checks if the specified object is contained in this list. Equality is
     * determined by evaluating the <code>.equals(Object)</code> method on each
     * element in this list.
     *
     * @param value The object to find in this list.
     * @return <code>true</code> - If and only if this list contains the specified object<br/>
     *         <code>false</code> - Otherwise.
     */
    public boolean contains(T value) {
        Iterator<T> iter = iterator();
        while ( iter.hasNext() ) {
            T current = iter.next();
            if ( current == null && value == null )
                return true;
            if ( current != null && current.equals(value) )
                return true;
        }

        return false;
    }

    /**
     * Determines the index within this list of the specified object. Equality is
     * determined by evaluating the <code>.equals(Object)</code> method on each
     * element in this list. The index of the first matching object is returned.
     *
     * @param value The object whose index should be determined.
     * @return The index within this list of the specified object, or <code>-1</code>
     *         if the object is not present in this list.
     */
    public int indexOf(T value) {
        int i = 0;
        Iterator<T> iter = iterator();
        while ( iter.hasNext() ) {
            T current = iter.next();
            if ( current == null && value == null )
                return i;
            if ( current != null && current.equals(value) )
                return i;
            ++i;
        }

        return -1;
    }

    /**
     * Retrieves the object in this list at the specified location.
     *
     * @param x The index of the target object.
     * @return The object in this list at the specified location.
     */
    public T get(int x) {
        if ( x >= size )
            throw new NoSuchElementException();

        Node<T> currentNode = head;
        int i = 0;
        while ( i < x ) {
            currentNode = currentNode.getNext();
            ++i;
        }

        return currentNode.getValue();
    }

    /**
     * Sets the object at the specified location in this list.
     *
     * @param x The index in this list at which the new object should be placed.
     * @param value The new object to placed at the specified location.
     */
    public void set(int x, T value) {
        if ( x >= size )
            throw new NoSuchElementException();

        Node<T> currentNode = head;
        int i = 0;
        while ( i < x ) {
            currentNode = currentNode.getNext();
            ++i;
        }

        currentNode.setValue(value);
    }

    /**
     * Removes all elements in this list.
     *
     */
    public void clear() {
        while (size > 0)
            remove(0);
    }

    /**
     * Determines the number of elements in this list.
     *
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }

    /**
     * Returns a sequential <code>Stream</code> with this list as its source.
     *
     * @return A sequential <code>Stream</code> over the elements in this list.
     */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * The internal representation of a linked list node.
     *
     * @param <T> The type of the elements in the list
     */
    private class Node<T> {

        // The previous node in the list, or null if this node is the head
        private Node<T> previous;

        // The next node in the list, or null if this node is the tail
        private Node<T> next;

        // The object represented by this node in the list
        private T value;

        public Node() {
        }

        public Node(Node<T> previousNode, Node<T> nextNode, T nodeValue) {
            this.previous = previousNode;
            this.next = nextNode;
            this.value = nodeValue;
        }

        /**
         * Removes references to encapsulated objects.
         */
        public void removeReferences() {
            this.previous = null;
            this.next = null;
            this.value = null;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setPrevious(Node<T> node) {
            this.previous = node;
        }

        public void setNext(Node<T> node) {
            this.next = node;
        }

        public void setValue(T v) {
            this.value = v;
        }

    }

    /**
     * The specialized linked list node iterator for this class.
     *
     * @param <T> The type of the elements in the list.
     */
    private class NodeIterator<T> implements Iterator<T> {

        public Node<T> currentNode;

        public NodeIterator(Node<T> from) {
            this.currentNode = from;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T nextValue = currentNode.getValue();
            currentNode = currentNode.getNext();
            return nextValue;
        }

    }

}
