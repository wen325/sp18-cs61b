import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    //** @source: https://github.com/sctpan/CS61B/blob/master/proj3/src/main/java/Router.java
// some code is revised*/

    private static class Status {
        private GraphDB.Node node;
        private double priority;

        public Status(GraphDB.Node node, double priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private static class StatusComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Status s1 = (Status) o1;
            Status s2 = (Status) o2;
            return Double.compare(s1.priority, s2.priority);
        }
    }
    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * @param g The graph to use.
     * @param stlon The longitude of the start location.
     * @param stlat The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
                                          double destlon, double destlat) {

        List<Long> res = new ArrayList<>();
        HashMap<String, Double> distTo = new HashMap<>();
        HashMap<String, String> edgeTo = new HashMap<>();
        HashSet<String> marked = new HashSet<>();
        PriorityQueue<Status> pq = new PriorityQueue<>(new StatusComparator());

        long startid = g.closest(stlon, stlat);
        long destid = g.closest(destlon, destlat);
        double h = g.distance(startid, destid);
        GraphDB.Node startnode = g.getNode(String.valueOf(startid));
        GraphDB.Node destnode = g.getNode(String.valueOf(destid));
        double dis = 0;
        distTo.put(startnode.id, dis);
        pq.add(new Status(startnode, dis + h));

        while (!pq.isEmpty()) {
            Status curr = pq.poll();
            String currId = curr.node.id;
            marked.add((currId));
            if (currId.equals(destnode.id)) {
                break;
            }
            for (String nid : curr.node.adjacents) {
                GraphDB.Node node = g.getNode(nid);

                if (!marked.contains(nid)) {
                    h = g.distance(Long.parseLong(nid), Long.parseLong((destnode.id)));
                    dis = distTo.get(currId) + g.distance(Long.parseLong(currId), Long.parseLong(nid));

                    if(!distTo.containsKey(nid) || dis <distTo.get(nid)) {
                        distTo.put(nid, dis);
                        edgeTo.put(nid, currId);
                        Status neighbor = new Status(node, 0);
                        pq.remove(neighbor);
                        pq.add(new Status(node, distTo.get(nid) + h));
                    }
                }
            }
        }
        String temp = destnode.id;

        while (!temp.equals(startnode.id)) {
            res.add(Long.parseLong(temp));
            temp = edgeTo.get(temp);
            if (temp == null) {
                break;
            }
        }

        res.add(Long.parseLong(startnode.id));
        Collections.reverse((res));
        return  res;
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * @param g The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigatiionDirection objects corresponding to the input
     * route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        return null; // FIXME
    }


    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";
        
        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents.*/
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it into
         * a Navigation Direction object.
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                    && way.equals(((NavigationDirection) o).way)
                    && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
