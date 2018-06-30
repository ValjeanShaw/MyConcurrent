/**
 * @author 600006
 * @version 1.0
 */
public class Search {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,6,8,9};
        int[] arr2 = {9,8,6,4,3,2};
        int[] arr3 = {9,5,3,1,2,3,4,6,8,9};
        System.out.println(search(arr1));
        System.out.println(search(arr2));
        System.out.println(search(arr3));
    }
    public static int search(int[] arr){
        int low = 0;
        int high = arr.length-1;

        while(high > low){
            int mid = (low + high) >> 1;
            if(mid == 0 || mid == arr.length-1){
                return mid;
            }
            if(arr[mid] < arr[mid+1] && arr[mid] < arr[mid-1]){
                return mid;
            }
            if(arr[mid] > arr[mid-1] && arr[mid+1] > arr[mid]){
                high = mid -1;
                continue;
            }
            if(arr[mid] < arr[mid-1] && arr[mid+1] < arr[mid]){
                low = mid +1;
                continue;
            }
        }
        return high;
    }
}
