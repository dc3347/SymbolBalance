import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.*;
import java.util.StringTokenizer;

public class ExpressionTree {
    private ExpressionNode root;
    private char[] operators={'+','-','*','/'};
    
    public ExpressionTree(ExpressionNode root){
        this.root=root;
    }
    
    /*this is the constructor of the expression tree. 
     * It will take in a String that represents a postfix expression and 
     * build the expression tree from that postfix expression*/
    public ExpressionTree(String postfix){
        root=stringToTree(postfix);
    }
    
    private ExpressionNode stringToTree(String str){
        Stack<ExpressionNode> nodes=new Stack<>();
        ExpressionNode node;
        
        /*https://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html*/
        //breaks a string into tokens
        StringTokenizer token=new StringTokenizer(str);
        while(token.hasMoreTokens()){
            boolean isOperator=false;
            String toke=token.nextToken();
            for(char oper: operators){
                if(toke.equals(Character.toString(oper)))
                    isOperator=true;
            }    
            if(isOperator){
                node=new ExpressionNode(toke.charAt(0));
            }
            else{
                node=new ExpressionNode(Integer.parseInt(toke));
            }
            if(node.isOperator){
                ExpressionNode n1=nodes.pop();
                ExpressionNode n2=nodes.pop();
                node.right=n1;
                node.left=n2;
                nodes.push(node); //creates a node
            }
            else{
                nodes.push(node); //puts node onto tree
            }
        }
        return nodes.pop();
    }
    
    private static class ExpressionNode{
        public ExpressionNode left;
        public ExpressionNode right;
        public int val;
        public boolean isOperator;
        public char operator;
        
        //for operators
        public ExpressionNode(char element){
            operator=element;
            isOperator=true;
        }
         
        //for operands
        public ExpressionNode(int element){
            val=element;
            isOperator=false;
        }

    }
    
    //returns the integer result of evaluating the expression tree
    public int eval(){
        if(root==null)
            return 0;
        else
            return eval(root);
    }
    
    //helper
    private int eval(ExpressionNode node){
        if(!node.isOperator)
            return node.val;
        else{
            int left=eval(node.left);
            int right=eval(node.right);
            return performOperation(left,node.operator, right);
        }
    }
    
    //evaluates the expression
    private int performOperation(int x, char oper, int y){
        if(oper=='-')
            return x-y;
        else if(oper=='+')
            return x+y;
        else if(oper=='/')
            return x/y;
        else if(oper=='*')
            return x*y;
        else                
            throw new IllegalArgumentException("That operation is not allowed.");
        
    }
    
    //returns a String that contains the corresponding postfix expression. 
    public String getPostfix(){
        String helper=postfix(root);
        return helper;
    }
    
    //this is the helper method
    private String postfix(ExpressionNode node){
        String left="";
        String right="";
        
        if(!node.isOperator){
            return " "+node.val; 
        }
        else{
            left=postfix(node.left);
            right=postfix(node.right);
            return left+" "+right+ " "+ node.operator;
        }
    }
   
    //tester
    public static void main(String[] args){
        ExpressionTree et = new ExpressionTree("34 2 - 5 *");
        System.out.println("Postfix: "+et.getPostfix());
        System.out.println("Result: "+et.eval());
    }
}  
   


    /*
    private ExpressionNode root;
    private String postfix;
    
 
   public ExpressionTree(String postfix) {
        if(postfix==null)
            throw new NullPointerException("Postfix shouldn't be null");
        if (postfix.length()==0)
            throw new IllegalArgumentException("Postfix shouldn't be empty");
        this.postfix=postfix;
        
      //constructs an expression tree using the postfix expression
     final Stack<ExpressionNode> nodes=new Stack<ExpressionNode>();
      ExpressionNode node;
        for (int i=0;i<postfix.length();i++){
          char ch=postfix.charAt(i);
          if(isOperator(ch)){
              ExpressionNode rightNode=nodes.pop();
              ExpressionNode leftNode=nodes.pop();
              node=new ExpressionNode(leftNode,ch,rightNode);
              nodes.push(node);
          }
          else{
              node=new ExpressionNode(null,ch,null);
              nodes.add(node);
          }
      }
      root=nodes.pop(); 
    }
    
  
    private boolean isOperator(char c){
        return c=='+'||c=='-'||c=='*'||c=='/';
    }
*/