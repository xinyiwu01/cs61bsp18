/**
 * cs61b2018 - project0
 * Planet class
 * @author Xinyi
 */
public class Planet {
	/**
	 * current x position
	 */
	public double xxPos;
	/**
	 * current y position
	 */
	public double yyPos;
	/**
	 * current velocity in the x direction
	 */
	public double xxVel;
	/**
	 * current velocity in the y direction
	 */
	public double yyVel;
	/**
	 * it's mass
	 */
	public double mass;
	/**
	 * the name of the file that contains the image to depict the planet
	 */
	public String imgFileName;
	/**
	 * gravitational constant
	 */
	public static final double G = 6.67e-11;
	/**
	 * constructor with instance variable
	 */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	/**
	 * constructor with an identical Planet
	 */
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	/**
	 * calculates the distance between two planets
	 * @param p the given planet
	 * @return returns the distance between current planet and another one
	 */
	public double calcDistance(Planet p){
		return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
	}
	/**
     * calculates the force exerted on the planet by the given planet
     * @param p the given planet
     * @return returns the force exerted on the current plenet by the given one
     */
	public double calcForceExertedBy(Planet p) {
		return G * mass * p.mass / ((this.calcDistance(p)) * (this.calcDistance(p)));
	}
	/**
	 * force exerted in the X direction
	 * @param p the given planet
	 * @return returns the force exerted on the planet in the X direction by the given one
	 */
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - xxPos;
		return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
	}
	/**
	 * force exerted in the Y direction
	 * @param p the given planet
	 * @return returns the force exerted on the planet in the Y direction by the given one
	 */
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - yyPos;
		return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
	}
	/**
	 * net force exerted in the X direction
	 * @param allP the given array of planets
	 * @return returns the net force exerted on the planet in the X direction 
	 */
	public double calcNetForceExertedByX(Planet[] allP) {
		double netForceX = 0;
		for (Planet p : allP) {
			//.equals method to identify if  the given planet is this planet
			if (!this.equals(p)) {
				netForceX += this.calcForceExertedByX(p);
			}
		}
		return netForceX;
	}
	/**
	 * net force exerted in the Y direction
	 * @param allP the given array of planets
	 * @return returns the net force exerted on the planet in the Y direction 
	 */
	public double calcNetForceExertedByY(Planet[] allP) {
		double netForceY = 0;
		for (Planet p : allP) {
			//.equals to identify if  the given planet is this one
			if (!this.equals(p)) {
				netForceY += this.calcForceExertedByY(p);
			}
		}
		return netForceY;
	}
	/** 
	 * updates the planet's velocity and position
	 * @param dt the given time period
	 * @param fx the net force in X direction
	 * @param fy the net force in Y direction
	 */
	public void update(double dt, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
	/**
	 * draws the planet
	 */
	public void draw() {
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);
	}

}
