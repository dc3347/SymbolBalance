import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;


public class SymbolBalance {
    public SymbolBalance(){
        
    }
        public static BalanceError checkFile(String pathToFile) { 
            // Should return either MismatchError(int lineNumber, char currentSymbol, char symbolPopped),
            // EmptyStackError(int lineNumber),  or
			// NonEmptyStackError(char topElement, int sizeOfStack). 
			// All three classes implement BalanceError
            // Write this method
           
            String file=pathToFile;
            ArrayStack<Character> symbols=new ArrayStack<>();
            int counter=0;

        try{
            Scanner input=new Scanner(new FileReader(file));
            while(input.hasNextLine()){
                counter++;
                String str =input.nextLine();
                char[] ch= str.toCharArray();
                for(int i=0; i<ch.length; ++i){
                    if(ch[i]=='(' || ch[i]=='{' || ch[i]=='['){
                        if(symbols.isEmpty()==false && symbols.top()!= '"' && symbols.top()!= '*'){
                            symbols.push(ch[i]);
                        }
                        else if(symbols.isEmpty()){
                            symbols.push(ch[i]);
                        }
                    }
                    else if(ch[i]=='"'){
                        if(symbols.isEmpty()==false && symbols.top()=='"'&&symbols.top()!='*'){
                            symbols.pop();
                        }
                        else if(symbols.top()!='*'){
                            symbols.push(ch[i]);
                        }
                        
                    }
                    else if(i+1<ch.length &&ch[i]=='/' && ch[i+1]=='*'){
                        if(symbols.isEmpty()==false && symbols.top()!='*'){
                            symbols.push(ch[i+1]);
                        }
                        else if(symbols.isEmpty()==true){
                            symbols.push(ch[i+1]);
                        }
                    }
                    else if(ch[i]==')' &&(symbols.top()!='"' && symbols.top()!='*')){
                        if(!(symbols.isEmpty())&&symbols.top()=='('){
                            symbols.pop();
                        }
                        else if(symbols.isEmpty()){
                            BalanceError emptyStack=new EmptyStackError(counter);
                            return emptyStack;
                        }
                        else if(symbols.top()!='('){
                            BalanceError mismatch=new MismatchError(counter,ch[i], symbols.top());
                            return mismatch;
                        }
                    }
                    else if(!(symbols.isEmpty()) &&ch[i]=='}'&&(symbols.top()!='"'&& symbols.top()!='*')){
                        if(!(symbols.isEmpty())&& symbols.top()=='{'){
                            symbols.pop();
                        }
                        else if(symbols.isEmpty()){
                            BalanceError empty=new EmptyStackError(counter);
                            return empty;
                        }
                        else if(symbols.top()!='{'){
                            BalanceError m=new MismatchError(counter,ch[i],symbols.top());
                            return m;
                        }
                    }
      
                    else if(ch[i]==']' && (symbols.top()!=' ' && symbols.top()!='*')){
                        if(!(symbols.isEmpty())&&symbols.top()=='['){
                            symbols.pop();
                        }
                        else if(symbols.isEmpty()){
                            BalanceError x=new EmptyStackError(counter);
                            return x;
                        }
                        else if(symbols.top()!='['){
                            BalanceError z= new MismatchError(counter,ch[i],symbols.top());
                            return z;
                        }
                    }
                    else if((symbols.isEmpty())&&ch[i]=='}'){
                        BalanceError x=new EmptyStackError(counter);
                        return x;
                    }
                    
                    else if(ch[i]=='"' &&symbols.top()!='*'){
                        if(!(symbols.isEmpty())&&symbols.top()=='"'){
                            symbols.pop();
                        }
                        else if(symbols.isEmpty()){
                            BalanceError error=new EmptyStackError(counter);
                            return error;
                        }
                        else if(symbols.top()!='"'){
                            BalanceError m=new MismatchError(counter,ch[i],symbols.top());
                            return m;
                        }
                    }
                    else if(i!=0 && ch[i-1]=='*'&& ch[i]=='/'&&symbols.top()!='"'){
                        if(!(symbols.isEmpty())&&symbols.top()=='*'){
                            symbols.pop();
                        }
                        else if(symbols.isEmpty()){
                            BalanceError x=new EmptyStackError(counter);
                            return x;
                        }
                        else if(symbols.top()!='*'){
                            BalanceError z=new MismatchError(counter,ch[i],symbols.top());
                            return z;
                        }
                    }   
                }
          
            }
            input.close();
            
            if(symbols.isEmpty()){
                return null;
            }
            else{
                BalanceError nonerror=new NonEmptyStackError(symbols.top(),-1);
                return nonerror;
            }
           
        }
        catch(IllegalArgumentException e){
             System.out.println("Oops! That's an illegal argument.");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Something went wrong there, buddy.");
        }
        catch(FileNotFoundException e){
            System.out.println("File not found...");
            
        }
            
        return null; 
     }   
    
     public static void main(String[] args){
            
            // Write this main program
            SymbolBalance test=new SymbolBalance();
            System.out.println(test.checkFile("TestFiles/Test4.java"));
            
     }
}
    
  