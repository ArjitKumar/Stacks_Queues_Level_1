import java.util.*;
public class Stacks_Queues_L1{
	public static void main(String[] args){
		Scanner scn = new Scanner(System.in);
     
     Infix_Evaluation obj = new Infix_Evaluation();
     System.out.println(obj.Infix_evaluation_ans("2+(5-3*6/2)"));
	}

// ============================ DUPLICATE BRACKETS ========================
 
 public static boolean duplicateParanthesis(String str) {
    // Write your code here
    Stack<Character> st = new Stack<>();
    for( int i = 0 ; i < str.length(); i++){
          char ch = str.charAt(i);
          if( ch == ')'){
        // two cases -> 1. agar peek pr seedha opening bracket h means duplicate return true
        // 2. tab tak pop kro jab to opening bracket nhi milta
        if( st.peek() == '('){
                  return true;
        }else{
                while( st.peek() != '('){
          st.pop();
        }
        st.pop();
        }
      }
          else{
        st.push(ch);
      }
    }
    return false;
  }

// ============================== Redundant Bracket ================================
   public static int checkRedundancy(String str) {
        // code here
         Stack<Character> st = new Stack<>();
    for( int i = 0 ; i < str.length(); i++){
          char ch = str.charAt(i);
          // if ch == opening bracket or any operator push
          if( ch == '(' || ch == '+' || ch == '-' || ch == '/' || ch == '*'){
       st.push(ch);
      }
          else{
        // if ch == closing bracket , pop till opening bracket and check if
        // there is any operator or not 
        if( ch == ')'){
            boolean isRedundant = true;
            while( st.peek() != '('){
                char top = st.peek();
                if( top == '+' || top == '-' || top == '/' || top == '*'){
                    isRedundant = false;
                }
                st.pop();
            }
            if(isRedundant == true) return 1;
            st.pop();
        }
      }
     }
    return 0;
    }

 // ======================================== NEXT GREATER ELE TO RIGHT ===========================
   public static long[] nextLargerElement(long[] arr, int n)
    {
        long[] nge  = new long[n];
        Stack<Long> st = new Stack<>();
        nge[n-1] = -1;
        st.push(arr[n-1]);
        for( int i = n-2 ; i >= 0 ; i--){
            while( st.size() > 0 && arr[i] >= st.peek() ){
                st.pop(); 
            }
            // loop k bahar matlb do conditions ya to stack empty ya bada element mil gaya
            if( st.size() == 0) nge[i] = -1;
            else nge[i] = st.peek();
            
            // at last the current elements always push itself
            st.push(arr[i]);
        }
        return nge; 
        
        // ====================== Alternative solution ( gave TLE on GFG )=======================

        // long[] nge = new long[n];
        // Stack<Integer> st = new Stack<>();
        // st.push(0);
        // for( int i = 0 ; i < n ; i++){
        //     while( st.size() > 0 && arr[i] >= arr[st.peek()]){
        //         nge[st.peek()] = arr[i];
        //         st.pop();
        //     }
        //     st.push(i);
        // }
        // // now if the stack if non empty that means no nge so ans is 1
        // while( st.size() > 0){
        //     nge[st.peek()] = -1;
        //     st.pop();
        // }
        // return nge;

    }   

    // ====================================== LARGEST AREA HISTOGRAM (LEETCODE HARD) =============================
     

     // Approach - find nextgreater ele from right and left and store it in two arrays
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        int[] rb = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        rb[n-1] = (int)arr.length;
        st.push(n-1);
        for( int i = n-2 ; i >= 0 ; i--){
            while( st.size() > 0 && arr[i] <= arr[st.peek()]){
                st.pop();
            }
            if( st.size() == 0){
                rb[i] = n;
            }else {
                rb[i] = st.peek();
            }
            st.push(i);
        }
        
        int[] lb = new int[n];
        st = new Stack<>();
        lb[0] = -1;
        st.push(0);
        for( int i = 1 ; i < n ; i++){
            while( st.size() > 0 && arr[i] <= arr[st.peek()]){
                st.pop();
            }
            if( st.size() == 0){
                lb[i] = -1;
            }else {
                lb[i] = st.peek();
            }
            st.push(i);
        }
        
        int maxArea = 0;
        for( int i = 0 ; i < n ; i++){
            int width = rb[i] - lb[i] - 1;
            int area = arr[i] * width;
            maxArea = Math.max(maxArea , area);
        }
        
        return maxArea;
    }


    // ==================================== INFIX EVALUTION ========================

    static class Infix_Evaluation{
      public static int precedence( char operator)
      {
        if( operator == '+'){
             return 1;    
        }else if(operator == '*'){
            return 2;
        }else if(operator == '-'){
            return 1;
        }else if(operator == '/'){
            return 2;
        }
        return 0;
      }
      public static int operation( int a, int b, char operator)
      {
        if( operator == '+'){
             return a + b;
        }else if(operator == '*'){
            return a*b;
        }else if( operator == '-'){
          return a-b;
        }else{
          return a/b;
        }
      }

      public static int Infix_evaluation_ans(String str){
        Stack<Integer> operands = new Stack<>();
        Stack<Character> optors = new Stack<>();
        for(int i  = 0 ; i<str.length() ; i++){
          char ch = str.charAt(i);
          if( ch == '('){
             optors.push(ch);
          }else if(Character.isDigit(ch) == true){
             operands.push(ch-'0'); // push the integer value
          }else if(ch == ')'){
               // pop till you get opening bracket and evalaute the answer
            while(optors.peek() != '('){
              // operator is poped once while two operands are poped
              char operator = optors.pop();
              int b = operands.pop();
              int a = operands.pop();

              int ans = operation(a,b,operator);
              operands.push(ans);

            }
            optors.pop(); // for opening bracket

          }else if( ch == '+' || ch == '-' || ch == '/' || ch == '*'){
            while( optors.size() > 0 && optors.peek() != '(' && precedence(ch) <= precedence(optors.peek())){
              char operator = optors.pop();
              int b = operands.pop();
              int a = operands.pop();

              int ans = operation(a,b,operator);
              operands.push(ans);
            }
               optors.push(ch);
          }
         
        }
        // there are chances that stack is not empty -> 2 * 3 + 6
         while(optors.size() != 0){
              // operator is poped once while two operands are poped
              char operator = optors.pop();
              int b = operands.pop();
              int a = operands.pop();

              int ans = operation(a,b,operator);
              operands.push(ans);

            }
        // System.out.println(operands.peek());
          // int pow = 1, result = 0;
          // while( operands.size() != 0){
          //     result = result + operands.pop() * pow;
          //     pow *=10;
          // }
          // operands.push(result);
        return operands.peek();
      }

    }

  // ================================= THE CELEBRITY PROBLEM ===========================
    // BRUTEFORECE ( ACCEPTED ON GFG)
   int celebrity(int M[][], int n)
    {
      // code here 
      int potentialCelebrity = -1;
      for( int i = 0 ; i < n ; i++){
          boolean flag = true;
          for( int j = 0 ; j < n ; j++){
              if(M[i][j] == 1){
                  flag = false;
                  break;
              }
          }
         // System.out.println(isPotentialCelebrity + " " + potentialCelebrity);
          if(flag == true){
              potentialCelebrity = i;
              break;
          }
      }
      if(potentialCelebrity == -1) return -1;
      for( int i = 0 ; i < n ; i++){
          if( i != potentialCelebrity){
             if(M[i][potentialCelebrity] == 0){
                  return -1;
             }
             
          }
      }
      return potentialCelebrity;
      
      
    }  

   // OPTIMISED USING STACK 
   class Celebrity_Optimised_Using_Stack
{ 
    //Function to find if there is a celebrity in the party or not.
    int potentialCelebrity(int arr[][], int n){
        Stack<Integer> st = new Stack<>();
        for( int i = 0 ; i < n ; i++) st.push(i); // pushed all the elements
        while( st.size() >= 2){
            // 2 pops so that we can compare
            int i = st.pop();
            int j = st.pop();
            if( arr[i][j] == 1){
                // i knows j -> i can't be a celebrity so push j 
                st.push(j);
            }else{
                st.push(i);
            }
        }
        int pot = st.pop(); // this could be potential celebrity
        return pot;
    }
    int celebrity(int M[][], int n)
    {
      int pot = potentialCelebrity(M, n);
      // since we have our potential celebrity we just neet to check its column
      for( int i = 0 ; i < n ; i++){
          if( i != pot){ // no need to check arr[1][1] because 1 khudko to janta hi h
             if(M[i][pot] == 0 || M[pot][i] == 1){ // here these conditions are very important because
             // we have not checked all celles in stack like the brutforce solution
                  return -1;
             }
             
          }
      }
      return pot; 
      
    }
} 


// ============================== MERGE INTERVALS(LEETCODE 56) ======================================

class MERGE_INTERVALS {
    class Pair implements Comparable<Pair>{
        int st;
        int et;
        
        Pair(int st, int et){
            this.st = st;
            this.et = et;
        }
        public int compareTo(Pair a){
            if( this.st != a.st){
                return this.st - a.st;
            }else{
                // if their start time is equal sort acc to end time
                return this.et - a.et;
            }
        }
    }
    public int[][] merge(int[][] intervals)
    {
        // Creating a pair array
        int n = intervals.length;
        Pair[] pairs = new Pair[n];
        for( int i = 0 ; i < n ; i++){
            pairs[i] = new Pair(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(pairs);
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]); // pushing first pair
        for( int i = 1; i < n ; i++){
            // pop compare and add 
            Pair p = pairs[i];
            if( p.st <= st.peek().et){
                Pair top = st.pop();
                int newEndTime = Math.max(top.et , p.et);
                // we need to pop st.peek() because this is new merged interval
                st.push(new Pair(top.st,newEndTime));
                
            }else{
                st.push(new Pair(p.st,p.et));
            }
        }
        Stack<Pair> ns = new Stack<>();
        while( st.size() > 0){
            Pair p = st.pop();
            ns.push(p);
        }
        int[][] arr = new int[ns.size()][2];
        for( int i = 0 ; i < arr.length ; i++){
            Pair p = ns.pop();
            int start = p.st;
            int end = p.et;
            arr[i][0] = start;
            arr[i][1] = end;
        }
        return arr;
        
        
    }
}


// =======================(2375)Smallest pattern from DI string=================
class DI_String_Pattern {
    public String smallestNumber(String pattern) {
        int num = 1;
        String ans = "";
        Stack<Integer> st =  new Stack<>();
        for( int i = 0 ; i < pattern.length() ; i++){
            char ch = pattern.charAt(i);
            if( ch == 'D'){
                // push increment 
                st.push(num);
                num++;
            }else{
                // push increment and empty stack
                st.push(num);
                num++;
                while( st.size() > 0) ans += st.pop();
            }
        }
        st.push(num); // last number will be left
        while( st.size() > 0) ans += st.pop();
        return ans;
    }
}



}