public class drawingTriangle_N{
    public static void main(String[] arg) {
        int N = 10;
        drawingTriangle(N);
               
    }
    
    public static void drawingTriangle(int N) {
        int i = 1;
        while (i <= N){
            for(int j = 0; j<i;j++){            
                System.out.print("*");
            }
            System.out.println("");
            i++;
        }
    }
}