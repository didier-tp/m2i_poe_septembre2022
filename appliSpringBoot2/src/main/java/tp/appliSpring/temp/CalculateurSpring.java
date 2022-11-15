package tp.appliSpring.temp;

import org.springframework.stereotype.Component;

@Component
public class CalculateurSpring {
	
	private double somme;//=0;
	private int n;//=0;
	
	public CalculateurSpring() {
		somme=0; n=0;
	}
	
	public void addVal(double x) {
		somme += x;
		n++;
	}
	
	public double getMoyenne() {
		return somme/n;
	}
	
	public double racineCarree(double x) { return Math.sqrt(x); }
	
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
