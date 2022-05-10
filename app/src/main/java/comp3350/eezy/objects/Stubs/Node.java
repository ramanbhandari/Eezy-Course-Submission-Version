package comp3350.eezy.objects.Stubs;

public class Node {
    private User user;
    private Node next;

    public Node(User user) {
        this.user = user;
        this.next = null;
    }

    public User getData() {
        return this.user;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
