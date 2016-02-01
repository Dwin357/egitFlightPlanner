package flightPlanner;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Interface portal;
		if(args.length > 0){
			portal = new Interface(args[0]);
		} else {
			portal = new Interface();
		}
		portal.run();
	}

}
