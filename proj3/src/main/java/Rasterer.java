import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    public Map<String, Object> queryBox(Map<String, Object> results, Map<String, Double> params) {

        final int SL = 288200;
        double ullon = params.get("ullon");
        double ullat = params.get("ullat");
        double lrlon = params.get("lrlon");
        double lrlat = params.get("lrlat");
        double width = params.get("w");
        double height = params.get("h");
        int depth;
        String[][] grid;
        boolean query_success = true;
        depth = getDepth(requiredResolution(lrlon, ullon, width, SL), SL);

        if (!bound(ullon, ullat, lrlon, lrlat)) {
            results.put("query_success", false);
            results.put("raster_ul_lon", params.get("ullon"));
            results.put("raster_ul_lat", params.get("ullat"));
            results.put("raster_lr_lon", params.get("lrlon"));
            results.put("raster_lr_lat", params.get("lrlat"));
            results.put("depth", depth);
            grid = new String[1][1];
            results.put("render_grid", grid);
            return results;
        }

        int[] topLeft, bottomRight;
        topLeft = getImageIndex(ullon, ullat, depth);  //  get the x and y value from query box(ullon, ullat)
        bottomRight = getImageIndex(lrlon, lrlat, depth); // get the x and y value from query box(lrlon, lrlat)

        int row = bottomRight[1] - topLeft[1] + 1;
        int column = bottomRight[0] - topLeft[0] + 1;

        grid = new String[row][column];  // the grid array size;


        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                grid[j][i] = "d" + depth + "_x" + (i + topLeft[0]) + "_y" + (j + topLeft[1]) + ".png";
            }
        }


        results.put("raster_ul_lon", params.get("ullon"));
        results.put("raster_ul_lat", params.get("ullat"));
        results.put("raster_lr_lon", params.get("lrlon"));
        results.put("raster_lr_lat", params.get("lrlat"));
        results.put("depth", depth);
        results.put("query_success", query_success);
        results.put("render_grid", grid);

        return results;
    }


    //** return required resolution according to query */
    public double requiredResolution(double lrlon, double ullon, double width, int SL) {
        return (lrlon - ullon) * SL / width;

    }

    //** return the depth when input required resolution */
    public int getDepth(double requiredResolution, int SL) {
        double lonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON)  / MapServer.TILE_SIZE * SL;
        int depth = 0;
        while (lonDPP > requiredResolution) {
            lonDPP = lonDPP * 1/2;
            depth += 1;
            if (depth > 7) {
                return 7;
            }
        }
        return depth;

    }

    //** return image depth */
    public int getImageDepth(String image) {
        char[] imageString = image.toCharArray();
        return Character.getNumericValue(imageString[1]);
    }

    //** whether the region include all query box */
    public boolean includeRegion(String[][] images) {
        String topLeft = images[0][0];
        String topRight = images[0][images[0].length - 1];
        String bottomLeft = images[images.length - 1][0];
        String bottomRight = images[images.length - 1][images[0].length - 1];


        return true;
    }

    //** get image index; the image can cover lon and lat at specified depth */
    public int[] getImageIndex(double lon, double lat, int depth) {
        int[] imageIndex = new int[2];
        int totalimages = (int) Math.pow(2, depth);
        int lonIndex = (int) ((lon - MapServer.ROOT_ULLON) / (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) * totalimages);
        int latIndex = (int) ((lat - MapServer.ROOT_ULLAT) / (MapServer.ROOT_LRLAT - MapServer.ROOT_ULLAT) * totalimages);

        imageIndex[0] = Math.min(lonIndex, totalimages - 1);
        imageIndex[1] = Math.min(latIndex, totalimages - 1);
//        imageIndex[0] = lonIndex;
//        imageIndex[1] = latIndex;
        return imageIndex;
    }

    public boolean bound(double ullon, double ullat, double lrlon, double lrlat) {
        if (ullon < MapServer.ROOT_ULLON || lrlon > MapServer.ROOT_LRLON || ullat > MapServer.ROOT_ULLAT || lrlat < MapServer.ROOT_LRLAT) {
            return false;
        }
        if (ullon > lrlon || ullat < lrlat) {
            return false;
        }

        return true;
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
         System.out.println(params);
        Map<String, Object> results = new HashMap<>();
//        Map<String, Object> results = new Rasterer<>();

        queryBox(results, params);

        System.out.println(results.get("query_success"));

//        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
//                           + "your browser.");
        return results;
    }

}
