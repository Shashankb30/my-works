import java.util.ArrayList;

class solution{
   private static void merge(int[] arr,int low,int mid,int high){
      ArrayList <Integer> temp =new ArrayList<>();//temp arr
      int left = low;//starting idx of 1sst part of arr
      int right = mid +1; //starting idx of 2nd part of arr

      //sorting elements in the temp arr in sorted manner
      while(left<=mid && right<=high){
         if(arr[left]<=arr[right]){
            temp.add(arr[left]);
            left++;
         }else{
            temp.add(arr[right]);
            right++;
         }
      }
      //if left (1st part arr)side is left out
      while( left<=mid){
         temp.add(arr[left]);
         left++;
      }
      //if right (2nd part arr)side is left out
      while(right<=high){
         temp.add(arr[right]);
         right++;
      }
      //transferring temp array to arr 
      for(int i=low;i<high;i++){
         arr[i]=temp.get(i-low);
      }
    }

    public static void mergeSort(int[] arr,int low,int high){
         if(low == high){
            return ;
         }
         int mid = (low + high) / 2 ;
         mergeSort(arr, low, mid);//1st side of arr
         mergeSort(arr, mid+1, high); //2nd side of arr 
         merge(arr, low, mid, high);//merging sorted half
    }
   }
public class mergesort{
      public static void main(String args[]) {
         int arr[] = {2,3,8,1,4,6,6,9};
         int n = arr.length;
         System.out.println("before sorting");
         for(int i=0;i<n;i++){
            System.out.print(arr[i]);
         }
         System.out.println();

         solution.mergeSort(arr,0,n-1);
         System.out.println("after sorting");
         for(int i=0;i<n;i++){
            System.out.print(arr[i]);
         }
         System.out.println();

         
      }
   }
