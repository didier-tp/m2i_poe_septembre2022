package tp.appliSpring.exemple2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import tp.traducteur.Traducteur;
import tp.traducteur.TraducteurFrancaisAnglais;
import tp.traducteur.TraducteurFrancaisEspagnol;

@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.exemple2" })
public class Exemple2Config {

	@Bean //id/name par défaut = traducteurFrancaisAnglais = nom de la méthode
	@Profile("v1")
	public Traducteur traducteurFrancaisAnglais() {
		Traducteur traducteurFrancaisAnglais = new TraducteurFrancaisAnglais();
		return traducteurFrancaisAnglais;
	}
	
	@Bean //id/name par défaut = traducteurFrancaisEspagnol = nom de la méthode
	@Profile("v2")
	public Traducteur traducteurFrancaisEspagnol() {
		TraducteurFrancaisEspagnol traducteurFrancaisEspagnol = new TraducteurFrancaisEspagnol();
		//traducteurFrancaisEspagnol.setEnMaj(false);
		traducteurFrancaisEspagnol.setEnMaj(true);
		return traducteurFrancaisEspagnol;
	}
	
	//traducteurFrancaisAnglais en profil "v1" 
	//traducteurFrancaisEspagnol en profil "v2"
	
}
