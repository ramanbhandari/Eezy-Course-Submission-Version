package comp3350.eezy.objects.Stubs;

public class UserList {
    private Node head;
    private int size;

    public UserList(){
        head = null;
        size = 0;
    }

    public boolean addUser(User user) {
        if(this.checkEmail(user.getEmail()) && !this.checkUsername(user.getUserName())) {
            if (head == null) {
                head = new Node(user);
            } else {
                Node curr = head;

                while (curr.getNext() != null) {
                    curr = curr.getNext();
                }
                curr.setNext(new Node(user));
            }
            size++;
            return true;
        }
        return false;
    }

    //If false, same email exists
    public boolean checkEmail(String email) {
        boolean check = true;

        Node curr = head;

        while(curr!=null && check) {
            if(curr.getData().getEmail().equals(email)) {
                check = false;
            }
            else {
                curr = curr.getNext();
            }
        }
        return check;
    }

    //If false, same username exists
    public boolean checkUsername(String username) {
        boolean check = false;

        Node curr = head;

        while(curr!=null && !check) {
            if(curr.getData().getUserName().equals(username)) {
                check = true;
            }
            else {
                curr = curr.getNext();
            }
        }
        return check;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return "The list " + size;
    }
}
