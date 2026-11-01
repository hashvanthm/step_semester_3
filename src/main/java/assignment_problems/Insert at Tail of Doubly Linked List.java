public class InsertTailDoubly {

    static class DNode {
        int data;
        DNode prev, next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            return newNode;
        }
        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return head;
    }

    static void print(DNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data);
            if (head.next != null) sb.append(" <-> ");
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        DNode head = null;
        head = insertAtTailDoubly(head, 1);
        head = insertAtTailDoubly(head, 2);
        head = insertAtTailDoubly(head, 3);
        head = insertAtTailDoubly(head, 4);
        print(head);

        DNode empty = insertAtTailDoubly(null, 7);
        print(empty);
    }
}
