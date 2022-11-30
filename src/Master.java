public class Master {
	public static void main(String[] args) {
		December riddle = new December1(new Input(args[0]));
		System.out.println("Answer 1: " + riddle.star1());
		System.out.println("Answer 2: " + riddle.star2());
	}
}
