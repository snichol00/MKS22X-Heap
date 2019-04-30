import java.util.Arrays;
import java.util.*;

public class MyHeap{
  private static void pushDown(int[]data,int size,int idx){
    int child;
    //makes child the greater child
    if ((2 * idx + 2) < size){
      if (data[2 * idx + 1] > data[2 * idx + 2]){
        child = 2 * idx + 1;
      }
      else{
        child = 2 * idx + 2;
      }
    }
    else{
      child = 2 * idx + 1;
    }
    //while not out of bounds and not less than
    while(child < size && data[child] > data[idx]){
      //swap child with index
      int temp = data[child];
      data[child] = data[idx];
      data[idx] = temp;
      //reset so child becomes new index
      idx = child;
      //then find the greater of its two children again
      //System.out.println(idx);
      if ((2 * idx + 2) < size){
        if (data[2 * idx + 1] > data[2 * idx + 2]){
          child = 2 * idx + 1;
        }
        else{
          child = 2 * idx + 2;
        }
      }
      else{
        child = 2 * idx + 1;
      }
    }
  }
  /*
     - size  is the number of elements in the data array.
     - push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
     - precondition: index is between 0 and size-1 inclusive
     - precondition: size is between 0 and data.length-1 inclusive.
    */
  private static void pushUp(int[]data,int idx){
    int parent = (idx - 1) / 2;
    while (idx >= 0 && data[parent] < data[idx]){
      //swap
      int temp = data[parent];
      data[parent] = data[idx];
      data[idx] = temp;
      //reset so parent becomes new index
      idx = parent;
      parent = (idx - 1) / 2;
    }
  }
  /*
     - push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
     - precondition: index is between 0 and data.length-1 inclusive.
     */

  public static void heapify(int[] arr){
    //just push all elements down
      //since pushDown is constant time
    for (int x = arr.length - 1; x >= 0; x--){
      pushDown(arr, arr.length, x);
    }
  }
    //convert the array into a valid heap. [ should be O(n) ]

  public static void heapsort(int[] arr){
    //make data into heap
    heapify(arr);
    int idx = arr.length - 1;
    while (idx > 0){
      //remove 1st number and add it to new array
      int temp = arr[idx];
      arr[idx] = arr[0];
      arr[0] = temp;
      //by pushing down the new first num, the next greatest will be on top
      pushDown(arr, idx, 0);
      idx--;
    }
  }
  //sort the array by converting it into a heap then removing the largest value n-1 times. [ should be O(nlogn) ]

  public static void main(String[] args) {
    int[] arr = {11, 2, 6, 8, 13, 5, 4, 7, 9, 10, 20, 0};
    HeapPrinter.print(arr);
    pushUp(arr, 4);
    System.out.println("Push Up 4th index, 13");
    HeapPrinter.print(arr);
    pushDown(arr, 11, 4);
    System.out.println("Push Down 4th index, 2");
    HeapPrinter.print(arr);
    heapify(arr);
    System.out.println("heapify");
    HeapPrinter.print(arr);
    System.out.println(Arrays.toString(arr));
    int[] arr1 = {11, 2, 6, 8, 13, 5, 4, 7, 9, 10, 20, 0};
    heapsort(arr1);
    System.out.println("Sort");
    System.out.println(Arrays.toString(arr1));

    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          MyHeap.heapsort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
