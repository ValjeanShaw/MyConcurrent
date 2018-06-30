import java.util.Arrays;

/**
 * @author 600006
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Integer[] a={1,2,3,4,12,12,12,15,29,94};
//        System.out.println(binarySearch(a,1));
//        System.out.println(search(a,1));
//
//
//        System.out.println(5>>1);
//        System.out.println(5<<1);
//
//        int[][] arrt = {{1,2,3},{2,3,4},{3,4,5},{4,5,6}};
//        System.out.println(arrt.length);
//        System.out.println(arrt[0].length);
//
//        System.out.println(search(arrt,10));
        StringBuffer stringBuffer = new StringBuffer(" helloworld");
        System.out.println(replaceSpace(stringBuffer));

    }

    public static int binarySearch(Integer[] srcArray, int des) {
        //定义初始最小、最大索引
        int low = 0;
        int high = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (low <= high) {
            //计算出中间索引值
            int middle = (high + low)>>>1 ;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                high = middle - 1;
                //判断上限
            } else {
                low = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }

    public static int search(Integer[] arr,int obj){
        int start = 0;
        int end = arr.length -1;
        int mid = end /2;
        while(arr[mid] != obj){
            if(start == end){
                return -1;
            }
            if(arr[mid]>obj){
                end = mid;
                mid = (start + mid)/2;
            }else{
                start = end;
                mid = (mid + end)/2;
            }
        }
        return mid;
    }



    public static boolean search(int[][] arr,int obj){
        int row = 0;
        int col = arr[0].length-1;
        while(row<=arr.length-1 && col >= 0){
            int value = arr[row][col];
            if(value == obj){
                return true;
            }
            if(value > obj){
                col --;
            }else{
                row++;
            }
        }
        return false;
    }

    public static String replaceSpace(StringBuffer str) {
        int len = str.length();
        int num = 0;
        char[] chars = str.toString().toCharArray();
        for(int i=0;i<len;i++){
            if(chars[i] == ' '){
                num ++;
            }
        }
        char[] temp = new char[len+num*2];
        for(int i=0;i<len;i++){
            temp[i] = chars[i];
        }
        int low = len-1;
        int high = len + num*2 -1;

        while(low>=0 ){
            char ch = temp[low];
            if(ch == ' '){
                temp[high--] = '0';
                temp[high--] = '2';
                temp[high--] = '%';
                low--;
            }else{
                temp[high--] = temp[low--];
            }
        }

        return new String(temp);
    }

}
