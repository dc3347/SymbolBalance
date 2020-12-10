import java.util.NoSuchElementException;
import java.util.*;
/* In this problem you will implement a Banker’s queue, as discussed in class, which uses 
 * two completely separate stacks, S1 and S2. Enqueue operations happen by pushing the data on to S1.
 * Dequeue operations are completed with a pop from S2.
 * 
 * Write a class TwoStackQueue that provides the Queue ADT (by implementing the Queue interface) 
 * using two stacks. Provide all methods specified in the interface. Your class should not 
 * use any additional memory to store the data in the queue except for the two stacks.
 * For your stacks, use the provided ArrayStack class. Your TwoStackQueue should raise a 
 * java.util.NoSuchElementException when trying to dequeue from an empty queue. You may use the 
 * TestTwoStackQueue class to test your code.*/

public class TwoStackQueue<T> implements Queue<T> {

     private Stack<T> s1; //data pushed onto s1: enqueue
     private Stack<T> s2; //data popped from s2: dequeue 
    
    public TwoStackQueue(){
        // write a constructor
        s1=new ArrayStack<>();
        s2=new ArrayStack<>();
        
        
    }
    
    /**
     * Insert a new item at the back of the queue 
     */
    public void enqueue(T x) { 
        s1.push(x);
    }
    
    /**
     * Remove and return the next item from the 
     * front of the queue. Should throw a 
     * NoSuchElementException if dequeuing from
     * an empty queue
     */ 
    public T dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("Dequeue from empty queue");   
        }
        if (s2.isEmpty()){
             move();
        }
        T popped = s2.pop();
        return popped;
        
    }
    
    
    private void move(){
        while(!s1.isEmpty()){
            s2.push(s1.pop()); //pop items from s1 and push onto s2
        }
    }
    
    public boolean isEmpty() {
        return(s1.isEmpty() && s2.isEmpty());
    }
    
}

