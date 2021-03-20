/**
 * cs61b2018 - project0
 * NBody class
 * @author Xinyi
 */
public class NBody{
	/**
	 * reads radius of a planet
	 * @param fileName the name of the input file
	 * @return returns radius in the input file
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	/**
	 * reads an array of planets
	 * @param fileName the name of the input file
	 * @return returns an array of planets in the input file
	 */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[number];
		for (int i = 0; i < number; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return planets;
	}


	public static void main(String[] args) {
		/*end time*/
		double T = Double.parseDouble(args[0]);
		/*time interval*/
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet[] planets = readPlanets(fileName);
		/*draw motionless planets*/
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p : planets) {
			p.draw();
		}
		StdDraw.show();

	    /*animation*/
	    //avoid flickering
	    StdDraw.enableDoubleBuffering();
	    StdDraw.clear();
	    /*current time*/
		double time = 0;
		int n = planets.length;
		while (time < T) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			//first calculate net forces
			for (int i = 0; i < n; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);

			}
			//then update positions and velocities
			for (int i = 0; i < n; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			//draw
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
			    p.draw(); //picture()
		    }
		    //show
		    StdDraw.show();
		    //pause
		    StdDraw.pause(10);
		    time += dt;
		}

		/*print the universe*/
		    StdOut.printf("%d\n", n);
		    StdOut.printf("%.2e\n", radius);
		    for (int i = 0; i < n; i++) {
		    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
		    		          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		    		          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		    }

	}

}