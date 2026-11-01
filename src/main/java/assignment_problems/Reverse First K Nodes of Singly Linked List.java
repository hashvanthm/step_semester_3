public class ReverseFirstK {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {
        Node prev = null;
        Node current = head;
        Node next = null;
        int count = 0;
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        head.next = current;
        return prev;
    }

    static Node build(int... vals) {
        Node head = null, tail = null;
        for (int v : vals) {
            Node n = new Node(v);
            if (head == null) head = n;
            else tail.next = n;
            tail = n;
        }
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        print(reverseFirstKNodes(build(1, 2, 3, 4, 5), 3));
        print(reverseFirstKNodes(build(1, 2, 3, 4, 5), 5));
    }
}
