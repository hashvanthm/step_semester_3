public class RemoveFirstK {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeFirstKNodes(Node head, int k) {
        while (k > 0 && head != null) {
            head = head.next;
            k--;
        }
        return head;
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
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        print(removeFirstKNodes(build(1, 2, 3, 4, 5), 2));
        print(removeFirstKNodes(build(1, 2), 5));
    }
}
