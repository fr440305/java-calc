import java.util.Scanner;

class PlayTowerofHanoi {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		int tower_cap;
		int move_from;
		int move_to;
		int step_cost = 0;
		int min_step;
		while (true) {
			System.out.println("How many disks would you like: ");
			tower_cap = input.nextInt();
			if (tower_cap <= 0)
				System.out.println("Can not be negative. Input again.");
			else
				break;
		}
		TowersofHanoi toh = new TowersofHanoi(tower_cap);
		min_step = (int)Math.pow(2, tower_cap) - 1;

		System.out.println("\n\n\t1. Play it.");
		System.out.println("\t2. See the solution.");
		System.out.println("\n1 or 2 : ");
		while (true) {
			switch (input.nextInt()) {
				case 1:
					while (true) {
						toh.showTowerStates();
						System.out.println("move from (1 or 2 or 3): ");
						move_from = input.nextInt();
						System.out.println("move to: ");
						move_to = input.nextInt();
						if ( ! toh.move(move_from, move_to)) {
							System.out.println("Illegal move. try again.");
						} else {
							step_cost ++;
						}
						if (step_cost >= min_step) {
							if (toh.done()) {
								System.out.println("Congratulation.");
							} else {
								System.out.println("GameOver.");
							}
							return ;
						}
					}
				case 2:
					toh.showTowerStates();
					toh.solvegame();
					toh.showTowerStates();
					return;
				default:
					System.out.println("Just 1 or 2. input again.");
			}
		}
	}
}
