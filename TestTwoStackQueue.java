import java.util.List; 
import java.util.ArrayList; 
import java.util.NoSuchElementException;

public class TestTwoStackQueue {
    
    public static void main(String[] args) {
        Queue<Integer> q = new TwoStackQueue<Integer>();
        
        // Test isEmpty
        boolean isEmptyTest = true;
        if (! q.isEmpty()) {
            System.err.println((char)27 + "[31misEmpty failed on empty queue."+(char)27+"[0m");         
            isEmptyTest = false;
        }
        q.enqueue(1);
        if (q.isEmpty()) {
            System.err.println((char)27 + "[31misEmpty failed on non-empty queue."+(char)27+"[0m");
            isEmptyTest = false;
        }

        Integer test = q.dequeue(); 
         
        if (! q.isEmpty()) {
            System.err.println((char)27 + "[31misEmpty failed after dequeuing one element."+(char)27+"[0m");         
            isEmptyTest = false;
        }
        if (isEmptyTest) 
            System.err.println((char)27 + "[32misEmpty OK.[0m");
                           
        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            System.err.println((char)27 + "[32mDequeue from empty queue results in NoSuchElementException. Ok."+(char)27+"[0m");
        }
                        
        List<Integer> testList = new ArrayList<>(); 
        for (int i = 0; i < 10; i++) {
            testList.add(i);
            q.enqueue(i);
        }
         
        boolean fifoTest = true;
        for (int i=0; i < testList.size(); i++) 
            if (! testList.get(i).equals(q.dequeue())) 
                fifoTest = false;
            
        if (fifoTest) 
            System.err.println((char)27 + "[32mFIFO order Ok."+(char)27+"[0m");
        else 
            System.err.println((char)27 + "[31mDequeued elements in the wrong order (not FIFO)."+(char)27+"[0m");
         
    }
    
}