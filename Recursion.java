import java.util.*;

public class Recursion {
public static void main( String[] args){
    
    ArrayList<String> res = new ArrayList<>();
    // Sub(0,"abc", new StringBuilder(), res);
    Subesequence_with_Return_Type("abc",0,"",res);
    System.out.println(res);
}

	static void printIncreasing( int a, int b){
		if( a > b) return;
        System.out.println(a);
        printIncreasing(a+1, b);
	}
    
    static void printDecreasing( int a, int b){
		if( a > b) return;

        printDecreasing(a+1, b);
        System.out.println(a);

	}

	static void printIncreasing_Decreasing( int a, int b){
		// faith -> (5,9) -> (6,9) knows how to get printed 6789 9876 now we want 5 at starting and at ending
		if( a > b) return;
		System.out.println(a);
		printIncreasing_Decreasing(a+1,b);
		System.out.println(a);
	}
    
    static void printOdd_Even( int a, int b){
    	if( a > b) return;
    	if( a % 2 == 1) System.out.println(a);
    	printOdd_Even(a+1,b);
    }

    static int factorial(int a){
    	// if( a == 0) return 1;
        // int factorial = a * factorial(a-1); 
        // return factorial;

        // or

        return a == 0 ? 1 : factorial(a-1) * a;
    }
    static int power( int a, int b){

    	return b == 0 ? 1 : power(a,b-1)*a;
    }

    static int powerBetter( int a, int b){
    	if( b == 0) return 1;

    	int smallAns = powerBetter(a, b/2);
    	smallAns *= smallAns;

    	return b % 2 != 0 ? smallAns * a : smallAns;
    }
    static void displayArrayReverse(int[]arr, int idx){
    	if( idx == arr.length) return;

    	displayArrayReverse(arr,idx + 1);
    	System.out.println(arr[idx]);
    }

    public static int maximum( int[] arr, int idx){
    	if( idx == arr.length - 1) return arr[idx]; // this wont work if arr is empty so we return -1e9

    	int max = maximum(arr, idx + 1);
    	if( arr[idx] > max) max = arr[idx];
    	return max;
    } // same code for minimum function
    public static int maximum_Better( int[] arr, int idx){
    	if( idx == arr.length) return -(int)(1e9); // this handles everycase 

    	int max = maximum_Better(arr, idx + 1);
    	if( arr[idx] > max) max = arr[idx];
    	return max;
    }
    public static int minimum_Better( int[] arr, int idx){
    	if( idx == arr.length) return (int)(1e9); // this handles everycase 

    	int min = minimum_Better(arr, idx + 1);
    	if( arr[idx] < min) min = arr[idx];
    	return min;
    }


     // faith -> pehle check kro kya mai khud data k barabar hun aur nhi to call lagoo
    public static boolean find(int[] arr, int idx, int data){
        if( idx == arr.length) return false;
        return ( arr[idx] == data) || find( arr, idx + 1, data);
    }
    
    // firstIndex is similar to find 
    public static int firstIndex(int[] arr, int idx, int data){
        if( idx == arr.length) return -1;
        return ( arr[idx] == data) ? idx : firstIndex( arr, idx + 1, data);
    }

    // for last index we can iterate from backwards but we need to do it in pre to learn recursion 

    public static int lastIndexBackward(int[] arr, int idx, int data){
        if( idx <= 0) return -1;
        return ( arr[idx] == data) ? idx : lastIndexBackward( arr, idx - 1, data);
    }
   

   // last index in post phase -> hum pre mai travel karenge aur jb post m wapas aenge to check karenge value == data h ya nhi
    // agar nhi h to hum return -1 karenge indicating ki data nhi mila aur jaise hi hume data mile index non negative ho jaega
    // ab index non -ve ka mtlb data mil gaya h aur isse ab return karenge aur change nhi kargenge

    public static int lastIndexPreRecursive(int[] arr, int idx, int data){
        if( idx == arr.length) return -1;
        int smallAns = lastIndexPreRecursive(arr, idx + 1, data);
        if( smallAns != -1) // that means we have found are index so return this no need to got further
        	return smallAns;
        
        return arr[idx] == data ? idx : -1;  

    }

    public static int[] allIndex( int[] arr, int idx, int data, int count){
    	if( idx == arr.length) return new int[count];

    	if( arr[idx] == data) count++; // incrementing count in pre phase

    	int[] ans = allIndex(arr, idx + 1, data, count);
    	if( arr[idx] == data) {
    		ans[count - 1] = idx;
    	}
    	return ans;
    }

    public static ArrayList<String> String_subsequnce(String str, int idx){
    	if( idx == str.length()) {
    		ArrayList<String> base = new ArrayList<>();
    		base.add("");
    		return base;
    	}

    	ArrayList<String> recAns = String_subsequnce(str, idx + 1); // this return the ans
    	ArrayList<String> myAns = new ArrayList<>(recAns); // this copies recANs value so ye nhi ana wala case ho gya
    	// hum chahe to direclty bhi likh skte h aur copy kr skte h 

    	for( String s : recAns){
    		myAns.add(str.charAt(idx) + s);
    	}
    	return myAns;
    }

    public static void Sub(int idx, String str, StringBuilder ans, ArrayList<String> res){
    	if( idx >= str.length()) {
    		// print
    		if(ans.length() == 0) res.add("{}");
    		else res.add(ans.toString());
    		return;
    	}

    	ans.append(str.charAt(idx)); 
    	Sub(idx + 1, str, ans, res); 

        ans.deleteCharAt(ans.length() - 1);
        Sub(idx + 1, str, ans, res);

    }

    // the above can be simplified as
    public static int Subesequence_with_Return_Type( String str, int idx, String ans, ArrayList<String> list){

        if( str.length() == idx) {
        	// add to list
        	list.add( ans );
        	return 1;
        }
    	int count = 0;
    	count += Subesequence_with_Return_Type(str, idx + 1, ans + str.charAt(idx), list);
    	// not take case
    	count += Subesequence_with_Return_Type(str, idx + 1, ans, list);
    	return count;

    }


    // getKPC

   public static ArrayList<String> getKPC(String str) {
        // base case empty string -> no key pressed
        if( str.length() == 0) {
             ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        // fatih -> 73 janta h kpc
        char ch  = str.charAt(0);
        String ros = str.substring(1); // rest string 73
        // assuming 73 brings result in rres after call
        
        ArrayList<String> rres = getKPC(ros); // result for 73
        // ab iske age 5 k kpc lagane h ans bn jaega
        
        ArrayList<String> myresult = new ArrayList<>();
        String codesforch = codes[ch - '0'];  // jkl
        for(int i = 0 ; i < codesforch.length() ; i++){
            char c = codesforch.charAt(i);
            for( String rst : rres){
                myresult.add(c + rst);
            }
        }
        return myresult;
        
    }


    // get stair paths

}
