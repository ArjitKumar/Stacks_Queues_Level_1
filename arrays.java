import java.util.*;

public class arrays {
public static void main( String[] args){
	
   // int[] arr = {1,1,2,1,2,0,2,0,1,0};
   // // rotatebyr(arr,-4);
   // // // segregate_neg_positive(arr);
   // segregate_0_1_and_2(arr);
   //  for(int val : arr){
   // 	System.out.print(val+",");
   // }
	int[] arr = {13,46,24,52,20,9};
	// selectionSort(arr);
   // bar_Chart();
	int[] nums = {9,12,5,10,14,3,10};
	pivot(nums, 0, nums.length - 1);

}

// ================================ ROTATEBYK ===============================

public static void swap(int[] arr, int i, int j){
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j]= temp;
}
public static void reverse(int[] arr, int i, int j){
	while( i < j){
		swap(arr,i++,j--);
	}
}
public static void rotatebyr(int[] arr, int r){
	int n = arr.length;
	r %=n;
	if( r < 0) r+=n;
	reverse(arr,0,n-1);
	reverse(arr,n-r,n-1);
	reverse(arr,0,n-r-1);
}

// ======================= SEGREGATE +VE AND -VE=============================
 // Note : Order is not preserved in this algo

public static void segregate_neg_positive(int[] arr){
	int n = arr.length, ptr = -1, itr = 0;
	while( itr < n)  {
		if( arr[itr] < 0){ // negative then only increment ptr first then swap
          swap(arr,++ptr, itr);
		}
		itr++; // this always increments
	}
}

// ======================= SEGREGATE 0 AND 1=============================
// Note : Order not preserved 
public static void segregate_0_and_1(int[] arr){
	int n = arr.length, ptr = -1, itr = 0;
	while( itr < n)  {
		if( arr[itr] == 0){ // negative then only increment ptr first then swap
          swap(arr,++ptr, itr);
		}
		itr++; // this always increments
	}
}


// ======================= SEGREGATE 0,1 and 2=============================
public static void segregate_0_1_and_2(int[] arr){
	int n = arr.length, pt1= -1, pt2 = n-1, itr = 0;
	while( itr <= pt2)  {
		if( arr[itr] == 0){ 
          swap(arr,++pt1, itr++); // itr incremented 
		}else if( arr[itr] == 2){
			swap(arr,pt2--,itr); // itr not incremented kyunki last se jo ele aya h yo khuch bhi ho skta h
		}else{ // means value is 1 
		itr++; // this always increments
		}

	}
}


// ================================= Max sum in the configuration (GFG Medium)==========================
public static int max_sum(int A[], int n)
    {
	
	int sum = 0, sumWithIndex = 0 ;
	// caluculating sum and sumWithIndex
	for( int i = 0 ; i < n ; i++){
	    sum += A[i];
	    sumWithIndex += A[i] * i;
	}
	int maxAns = sumWithIndex; // now this stores (0a+1b+2c+3d+4e) as sum
	// therefore we will loop from 1 to n and calulate rest sum and compare
	for( int i = 1; i < n ; i++){
	    sumWithIndex = sumWithIndex - sum + A[i-1] * n; // main formula 
	    // here A[i-1]*n represents na and so on
	    maxAns = Math.max(sumWithIndex, maxAns);
	}
	return maxAns;
	
    }


 // ===================================== Container With Max Water ===========================   

class Container_With_Max_Water {
    public int maxArea(int[] height) {
        int n = height.length, MaxArea = 0;
        int i = 0, j = n-1;
        while( i < j){
            int w = j-i; // only 1 boundary included
            int criticalheight = Math.min(height[i],height[j]);
            int area = w * criticalheight;
            MaxArea = Math.max(MaxArea,area);
            if(height[i] < height[j]){
                i++;
            }else if( height[i] > height[j]){
                j--;
            }else{
                // both are equal increment i or decrement j
                i++;
            }
            
        }
        return MaxArea;
    }
    // shorter way to write code
    public int maxArea_Shorter(int[] height) {
        int n = height.length, maxArr = 0;
        int i = 0, j = n-1;
        while( i < j){
            int w = j-i; // only 1 boundary included
            maxArr = height[i] < height[j] ? Math.max(maxArr,height[i++] * w) :
            Math.max(maxArr,height[j--] * w);
        }
        return maxArr;
    }
}


// ==========================(LC-159)Longest substring with atmost 2 distinct characters===========================
public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        int n = s.length();
        if(n <= 2) return n;
        int[] freq = new int[128];
        int len = 0, count = 0, si = 0, ei = 0;
        while( ei < n ){
            if(freq[s.charAt(ei)] == 0) count++;
            freq[s.charAt(ei)]++;
            ei++;
            while( count > 2 ){
              if( freq[s.charAt(si)] == 1) count--;
              freq[s.charAt(si)]--;
              si++;
            }
            len = Math.max(len,ei-si);

        }
        return len;
    } 
 
 public static void bar_Chart(){
 	Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for( int i = 0 ; i < n ; i++ ){
        arr[i] = scn.nextInt();
    }
    int max = arr[0];
    for( int val : arr){
        max = Math.max(max, val);
    }
    System.out.print(max);
    for( int i = max ; i >= 1 ; i--){
       for( int j = 0; j < n; j++){
        if(arr[j] >= i){
           System.out.print("*\t"); 
        }else{
            System.out.print('\t');
        }
       }
       System.out.println();
    }
 }

 public static void selectionSort(int[] arr){
 	for( int i = 0; i < arr.length ; i++){
 		int min = i;
 		for( int j = i; j < arr.length ; j++){
             if( arr[j] < arr[min]){
             	min = j;
             }
 		}
 		// swaping
 		int temp = arr[i];
 		arr[i] = arr[min];
 		arr[min] = temp;
 	}
 	for( int val : arr){
 		System.out.print(val + " ");
 	}
 }

// ==================================== BUBBLE SORT ===============================

  public static void bubble_Sort(int[] arr, int n){
        //code here
        for( int i = n-1 ; i >= 1 ; i--){
            int didswap = 0;
            for( int j = 0 ; j <= i - 1 ; j++){
                if( arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    didswap = 1;
                }
            }
            if(didswap == 0) break;
        }
    }

// ========================================= MERGE SORT ==================================
    class mergeSort
{
     void merge(int[]nums,int low,int mid,int high){
        int left=low,right=mid+1;
        ArrayList<Integer> li = new ArrayList<>();
        while(left<=mid && right<=high){
            if(nums[left]<=nums[right]){
                li.add(nums[left++]);
            }
            else{
                li.add(nums[right++]);
            }
        }
        while(left<=mid){
            li.add(nums[left++]);
        }
        while(right<=high){
            li.add(nums[right++]);
        }
        //most important part of algo -> copying elements from temp array to orignal array

        for(int i=low;i<=high;i++){
            nums[i] = li.get(i-low);
        }
    }

    void mergeSort(int nums[], int low, int high)
    {
        //code here
        if(low>=high) return;
        int mid = (low+high)/2;
        mergeSort(nums,low,mid);
        mergeSort(nums,mid+1,high);
        merge(nums,low,mid,high);
    }
}

// ====================================== Partitioning an Array =================================
// note : below algo works if pivot is not present in the array
    public static void pivotArray(int[] arr, int pivot) {
        int i = 0,j=0;
        while(i < arr.length){
            if( arr[i] > pivot){
                i++;
            }else{
                swap(arr, i , j );
                i++;
                j++;
            }
        }
        for( int val : arr){
            System.out.print(val + " ");
        }
    }

    public static void pivot(int[] arr, int low, int high){
    	int pi = arr[low];
    	int i = low, j = high;
    	while( i < j){
    		while( arr[i] <= pi && i <= high-1) i++;
    		while( arr[j] > pi && j >= low + 1) j--;
    		if( i < j) swap(arr,i,j);
    	}
    	swap(arr,low,j);
    	for( int val : arr){
    		System.out.print(val + " ");
    	}
    }


    // ================================ QUICK SORT COMPLETE ALGO ==================================
    public class Quick_Sort {
    public static void swap(List<Integer> arr, int i, int j){
	int temp = arr.get(i);
	arr.set(i,arr.get(j));
	arr.set(j,temp);
}
    public static int pivot(List<Integer> arr, int low, int high){
    	int pi = arr.get(low);
    	int i = low, j = high;
    	while( i < j){
    		while( arr.get(i) <= pi && i <= high-1) i++;
    		while( arr.get(j) > pi && j >= low + 1) j--;
    		if( i < j) swap(arr,i,j);
    	}
    	swap(arr,low,j);
        return j;
    }
    public static void qs(List<Integer> arr, int low, int high){
        if( low < high) {
            int pi = pivot(arr, low, high);
            qs(arr,low,pi - 1);
            qs(arr,pi+1,high);
        }
    }
    public static List<Integer> quickSort(List<Integer> arr){
        // Write your code here.
        qs(arr, 0, arr.size() - 1);
        return arr;
    }
}





}
    