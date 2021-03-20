
public class List {

    private Node head = null;
    private Node tail = null;
    private int size;
    
    class Node {
        protected Suspect data;
        protected Node next = null;

        /**
         * Constructor. Sets data
         *
         * @param data the data stored
         * @return
         */
        Node(Suspect data) {
            this.data = data;
        }

        /**
         * Returns this node's data
         *
         * @return the reference to node's data
         */
        public Suspect getData() {
            // return data stored in this node
            return data;
        }

        /**
         * Get reference to next node
         *
         * @return the next node
         */
        public Node getNext() {
            // get next node
            return next;
        }


        /**
         * Set reference to next node
         *
         * @param next reference
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * Default constructor
     */
    public List() {
    	 size=0;
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    
    public void insertAtFront(Suspect data) {
        Node n = new Node(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
        size++;
    }

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    
    public void insertAtBack(Suspect data) {
        Node n = new Node(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        size++;
    }

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    
    public Suspect removeFromFront() throws Exception {
        if (isEmpty())
            throw new Exception();

        Suspect data = head.getData();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();
        size--;
        return data;
    }

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    
    public Suspect removeFromBack() throws Exception {
        if (isEmpty())
            throw new Exception();

        Suspect data = tail.getData();

        if (head == tail)
            head = tail = null;
        else {
            Node iterator = head;
            while (iterator.getNext() != tail)
                iterator = iterator.getNext();

            iterator.setNext(null);
            tail = iterator;
        }
        size--;
        return data;
    }

   
    /**
     * Returns list as String
     */
    public int size() {return size;}
    
    public void print() {
        if (isEmpty()) {
           System.out.println( "No names found.");
           return;
        }

        Node current = head;
        int count=1;
        
        while (current != null) {
            System.out.println(count++ +"." +current.data);
            current = current.next;
        }

    }
}

