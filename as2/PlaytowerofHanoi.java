class PlayTowerofHanoi {
	public static void main (String[] args) {
		TowersofHanoi toh = new TowersofHanoi(4);
		toh.showTowerStates();
		toh.solvegame();
		toh.showTowerStates();
	}
}
