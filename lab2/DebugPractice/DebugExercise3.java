/**
 * Created by jug on 1/22/18.
 */
public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                int newTotal = totalTurnips + numAvailable;   // the number of 0.45 cost item is negative, I revised the foods.csv
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
    }
}
