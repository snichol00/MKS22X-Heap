public class MyHeap{
  private static void pushDown(int[]data,int size,int idx){
    int child;
    //makes child the greater child
    if (data[2 * idx + 1] > data[2 * idx + 2]){
      child = data[2 * idx + 1];
    }
    else{
      child = data[2 * idx + 2];
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
      if (data[2 * idx + 1] > data[2 * idx + 2]){
        child = data[2 * idx + 1];
      }
      else{
        child = data[2 * idx + 2];
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

  public static void heapify(int[]){

  }
    //convert the array into a valid heap. [ should be O(n) ]

  public static void heapsort(int[]){

  }
  //sort the array by converting it into a heap then removing the largest value n-1 times. [ should be O(nlogn) ]
}
