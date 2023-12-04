public class ListNode {

    class Node {
    
        String data;

        Node next;

        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    Node tail = null;

    public void addData(String data){
        Node newNode = new Node(data);

        if(head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public String getData(int Entry){
        Node current = head;
        String output = "Empty";

        for(int i = 0; i < Entry; i++)
        {
            if(current.data != "Empty")
            {
                output = current.data;
            }
            current = current.next;
        }
        
        return output.toString();
    }

    public void remove(int Entry, int length){
        if(Entry > 0 && Entry < length)
        {
            Node current = head;

            for(int i = 0; i < Entry; i++)
            {
                current = current.next;
            }
            for(int i = Entry + 1; i < length; i++)
            {
                current.data = current.next.data;
                if(current.next != null);
                {
                    current = current.next;
                }
            }

            current.data = null;
        }

    }
}