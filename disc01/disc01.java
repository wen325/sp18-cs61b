public class disc01 {

  public static void main(String[] arg) {
    int[] array = new int[] { 3, 0, 4, 6, 3 };
    int k = 2;
    System.out.print(fib2(7, 1, 0, 1));
  }

  /**Return the index of smallest element from element[k] in Array */
  public static int mystery(int[] inputArray, int k) {
    int x = inputArray[k];
    int answer = k;
    int index = k + 1;
    while (index < inputArray.length) {
      if (inputArray[index] < x) {
        x = inputArray[index];
        answer = index;
      }
      index = index + 1;
    }
    return answer;
  }

  public static void mystery2(int[] inputArray) {
    int index = 0;
    while (index < inputArray.length) {
      int targetIndex = mystery(inputArray, index);
      int temp = inputArray[targetIndex];
      inputArray[targetIndex] = inputArray[index];
      inputArray[index] = temp;
      index = index + 1;
    }
  }

  public static int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }

  public static int fib2(int n, int k, int f0, int f1) {
    if (n == k) {
      return f0;
    } else {
      return fib2(n, k + 1, f1, f0 + f1);
    }
  }
}
