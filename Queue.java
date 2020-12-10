interface Queue<T> {
    /**
     * Insert a new item at the back of the queue 
     */
    public void enqueue(T x);
    /**
     * Remove and return the next item from the 
     * front of the queue. Should throw a 
     * NoSuchElementException if dequeuing from
     * an empty queue
     */ 
    public T dequeue();
    /**
     * Return true if the queue is empty and 
     * false otherwise 
     */ 
    public boolean isEmpty();
}