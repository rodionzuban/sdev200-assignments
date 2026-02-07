package module4;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise24_3 {
    public static void main(String args[]) {
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
        System.out.print("Enter five numbers: ");
        Scanner input = new Scanner(System.in);
        double[] v = new double[5];
        for (int i = 0; i < 5; i++)
            v[i] = input.nextDouble();

        list.add(v[1]);
        list.add(v[2]);
        list.add(v[3]);
        list.add(v[4]);
        list.add(0, v[0]);
        list.add(2, 10.55);
        list.remove(3);

        java.util.ListIterator<Double> iterator1 = list.listIterator();
        System.out.print("The list in forward order: ");
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + " ");

        java.util.ListIterator<Double> iterator2 = list.listIterator(list.size());
        System.out.print("\nThe list in backward order: ");
        while (iterator2.hasPrevious())
            System.out.print(iterator2.previous() + " ");

        input.close();
    }
}

class TwoWayLinkedList<E> implements MyList<E> {
    Node<E> head, tail;
    int size = 0;

    public TwoWayLinkedList() {

    }

    public TwoWayLinkedList(E[] objects) {
        for (E obj : objects) {
            add(obj);
        }
    }

    /** Return the first element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head

        if (head != null) {
            head.previous = newNode;
        } else {
            tail = newNode;
        }

        head = newNode; // head points to the new node
        size++; // Increase list size
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            newNode.previous = tail;
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override /**
               * Add a new element at the specified index
               * in this list. The index of the head element is 0
               */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            Node<E> temp = current.next;
            newNode.previous = current;
            temp.previous = newNode;
            current.next = newNode;
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and
     * return the object that is contained in the removed node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            E temp = head.element;
            head = head.next;
            head.previous = null;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp;
        }
    }

    /**
     * Remove the last node and
     * return the object that is contained in the removed node.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            E temp = head.element;
            head = tail = null;
            size = 0;
            return temp;
        } else {
            Node<E> newTail = tail.previous;

            E temp = tail.element;
            tail = newTail;
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override
    /**
     * Remove the element at the specified position in this
     * list . Return the element that was removed from the list.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            (current.next).previous = previous;
            size--;
            return current.element;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        if (head == null)
            return false;
        Node<E> current = head;
        while (current.next != null) {
            if (current.element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        if (index < 0 || index >= size - 1) {
            return null;
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.element;
    }

    @Override /**
               * Return the index of the first matching element in
               * this list. Return -1 if no match.
               */
    public int indexOf(Object e) {
        if (size == 0) {
            return -1;
        }

        Node<E> current = head;
        int index = 0;

        while (index < size) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    @Override /**
               * Return the index of the last matching element in
               * this list. Return -1 if no match.
               */
    public int lastIndexOf(E e) {
        if (size == 0) {
            return -1;
        }

        Node<E> current = tail;
        int index = size - 1;

        while (index >= 0) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.previous;
            index--;
        }

        return -1;
    }

    @Override /**
               * Replace the element at the specified position
               * in this list with the specified element.
               */
    public E set(int index, E e) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E temp = current.element;
        current.element = e;

        return temp;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    protected static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    private class LinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> nextElement = head;
        private int cursor = 0; // position between elements --> nextElement's index. Maximum is list.size;

        public LinkedListIterator() {
        }

        public LinkedListIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                        + size);
            cursor = index;

            for (int i = 0; i < index; i++) {
                nextElement = nextElement.next;
            }
        }

        // not implemented under the interface, but kept for assignment
        public void setLast() {
            cursor = size;
            nextElement = null;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            E e = nextElement.element;
            lastReturned = nextElement;
            nextElement = nextElement.next;
            cursor++;
            return e;
        }

        @Override
        // must be called after a next() or previous()
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> next = lastReturned.next;
            Node<E> prev = lastReturned.previous;

            if (prev == null) {
                head = next;
            } else {
                prev.next = next;
            }

            if (next == null) {
                tail = prev;
            } else {
                next.previous = prev;
            }

            if (lastReturned == nextElement) { // if last return call was a previous()
                nextElement = next;
            } else {
                cursor--;
            }

            size--;
            lastReturned = null;
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<E>(e);
            Node<E> prev = (nextElement == null) ? tail : nextElement.previous;

            newNode.next = nextElement;
            newNode.previous = prev;

            if (prev != null) {
                prev.next = newNode;
            } else {
                head = newNode;
            }

            if (nextElement == null) {
                tail = newNode;
            } else {
                nextElement.previous = newNode;
            }

            size++;
            cursor++;
            lastReturned = null;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            if (hasNext()) {
                nextElement = nextElement.previous;
            } else {
                nextElement = tail;
            }

            lastReturned = nextElement;
            cursor--;
            return lastReturned.element;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        // can only be called after a next() or previous()
        public void set(E e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.element = e;
        }
    }

}
