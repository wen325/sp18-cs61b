public class Planet {

    /** Planet instance */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    // ** First Plane constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // ** Initialize an identical Planet object */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // ** calculates the distance between current Planet and Planet q. */
    public double calcDistance(Planet q) {
        double dx = xxPos - q.xxPos;
        double dy = yyPos - q.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // ** calculates the force exterted in current Planet given by Planet q. */
    public double calcForceExertedBy(Planet q) {
        return mass * q.mass * G / (this.calcDistance(q) * this.calcDistance(q));
    }

    // ** calculates the force exterted in X directions. */
    public double calcForceExertedByX(Planet q) {
        double dx = q.xxPos - xxPos;
        return this.calcForceExertedBy(q) * dx / (this.calcDistance(q));
    }

    // ** calculates the force exterted in Y directions. */
    public double calcForceExertedByY(Planet q) {
        double dy = q.yyPos - yyPos;
        return this.calcForceExertedBy(q) * dy / (this.calcDistance(q));
    }

    // ** calculates the X force exterted in current Planet given by all other
    // Planet. */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    // ** calculates the Y force exterted in current Planet given by all other
    // Planet. */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    // **update planet's position and velocity */
    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
