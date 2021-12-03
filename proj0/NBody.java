import javax.xml.xpath.XPath;

public class NBody {

    // ** Get the file path and read Radius, Planets */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Planet[] Planets = readPlanets(filename);
        int num = Planets.length; // ** get the number of planets */
        String background = "./images/starfield.jpg";
        // draw_background(Radius, background);

        // for (Planet planet : Planets) {
        // planet.draw();
        // }
        // String music = "./audio/2001.mid";
        // StdAudio.play(music);

        StdDraw.enableDoubleBuffering();

        for (double t = 0; t < T; t = t + dt) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            for (int i = 0; i < num; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-Radius, Radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, background);
            StdDraw.show();
            for (Planet planet : Planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        System.out.println(num);
        System.out.printf("%.2e\n", Radius);
        for (Planet planet : Planets) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel, planet.yyVel, planet.mass,
                    planet.imgFileName);
        }

        // StdDraw.show();
        // String planetsTxtPath = "./data/planets.txt";
        // readRadius(planetsTxtPath);
    }

    // ** return the Radius of Universe */
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    // ** return the Planets of Universe */
    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int num = in.readInt();
        in.readDouble();
        Planet[] Planets = new Planet[num];
        int i = 0;
        while (i < num) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Planet(xP, yP, xV, yV, m, img);
            i += 1;
        }
        return Planets;
    }

}
