public class Maxele {
    public static int max(int[] m) {
        int k = m[0];
        for(int i =0; i < m.length; i++){
            if(k < m[i]){
                k = m[i];
            }
        }
        return k;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.println(max(numbers));      
    }
 }