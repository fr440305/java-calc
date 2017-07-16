class TowersofHanoi {
	private ListStack<Integer>[] rods;
	private int n;
	public TowersofHanoi (int towercapacity) {
		this.n = towercapacity;
		this.rods = new ListStack[3];
		for (int i = 0; i < 3; i++) {
			this.rods[i] = new ListStack<Integer>(towercapacity);
		}
		for (int i = towercapacity; i >= 1; i--) {
			this.rods[0].push(new Integer(i));
		}
	}
	public Boolean legalMove (int a, int b) {
		if (this.rods[a].isEmpty())
			return new Boolean(false);
		if (this.rods[b].isEmpty()) {
			return new Boolean(true);
		} else {
			if (this.rods[a].peek().intValue() > this.rods[b].peek().intValue() )
				return new Boolean(false);
			else
				return new Boolean(true);
		}
	}
	public Boolean move(int a, int b) {
		if (! this.legalMove(a, b).booleanValue() )
			return new Boolean(false);
		this.rods[b].push(this.rods[a].pop());
		System.out.println(a + " -> " + b);
		return new Boolean(true);
	}
	public Boolean move(int m, int a, int b, int c) {
		// XXX
		return new Boolean(false);
	}
	public void showTowerStates () {
		System.out.println("rods[0]: " + this.rods[0].toString());
		System.out.println("rods[1]: " + this.rods[1].toString());
		System.out.println("rods[2]: " + this.rods[2].toString());
	}
	public void solvegame () {
		int total_steps = (int)Math.pow(2, this.n) - 1;
		int src = 0;
		int aux;
		int dest;
		if (this.n % 2 == 0) {
			dest = 1;
			aux = 2;
		} else {
			dest = 2;
			aux = 1;
		}
		for (int i = 1; i <= total_steps; i++) {
			if (i % 3 == 1) {
				if (this.move(src, dest).booleanValue() || this.move(dest, src).booleanValue());
			} else if (i % 3 == 2) {
				if (this.move(src, aux).booleanValue() || this.move(aux, src).booleanValue());
			} else { // i % 3 == 0
				if (this.move(aux, dest).booleanValue() || this.move(dest, aux).booleanValue());
			}
		}
	}
	public boolean done () {
		if (this.rods[0].isEmpty() && this.rods[1].isEmpty()) return true; else return false;
	}
}
