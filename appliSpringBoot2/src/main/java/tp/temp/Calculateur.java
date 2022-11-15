package tp.temp;

public class Calculateur {
	
	private double somme;//=0;
	private int n;//=0;
	
	public Calculateur() {
		somme=0; n=0;
	}
	
	public void addVal(double x) {
		somme += x;
		n++;
	}
	
	public double carre(double x) { return x*x; }

	public double getSomme() {
		return somme;
	}

	public void setSomme(double somme) {
		this.somme = somme;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	
}
