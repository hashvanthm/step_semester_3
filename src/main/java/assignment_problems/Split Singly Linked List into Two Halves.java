public class SplitListInHalf {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node splitListInHalf(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node second = slow.next;
        slow.next = null;
        return second;
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

    static String toStr(Node head) {
        if (head == null) return "(empty)";
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node h1 = build(1, 2, 3, 4, 5);
        Node r1 = splitListInHalf(h1);
        System.out.println("First half: " + toStr(h1) + " Returned: " + toStr(r1));

        Node h2 = build(1, 2, 3, 4);
        Node r2 = splitListInHalf(h2);
        System.out.println("First half: " + toStr(h2) + " Returned: " + toStr(r2));
    }
}
