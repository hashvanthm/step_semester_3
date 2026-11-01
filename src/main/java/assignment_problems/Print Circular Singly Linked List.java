public class PrintCircular {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void printCircular(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }
        Node current = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(current.data).append(" ");
            current = current.next;
        } while (current != head);
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        Node a = new Node(10);
        Node b = new Node(20);
        Node c = new Node(30);
        a.next = b;
        b.next = c;
        c.next = a;
        printCircular(a);
        printCircular(null);
    }
}
