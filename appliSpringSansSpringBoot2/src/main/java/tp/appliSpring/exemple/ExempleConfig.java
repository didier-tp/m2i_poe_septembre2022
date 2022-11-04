package tp.appliSpring.exemple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tp.traducteur.Traducteur;
import tp.traducteur.TraducteurFrancaisAnglais;
import tp.traducteur.TraducteurFrancaisEspagnol;

@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.exemple" })
public class ExempleConfig {
	/*
	 * @ComponentScan() pour demander à spring de parcourir les classes de certains
	 * packages pour y trouver des annotations @Component , @Service , @Autowired à
	 * analyser et interpréter
	 */
	
	@Bean //id/name par défaut = traducteurFrancaisAnglais = nom de la méthode
	public Traducteur traducteurFrancaisAnglais() {
		Traducteur traducteurFrancaisAnglais = new TraducteurFrancaisAnglais();
		return traducteurFrancaisAnglais;
	}
	
	@Bean //id/name par défaut = traducteurFrancaisEspagnol = nom de la méthode
	public Traducteur traducteurFrancaisEspagnol() {
		Traducteur traducteurFrancaisEspagnol = new TraducteurFrancaisEspagnol();
		return traducteurFrancaisEspagnol;
	}
	
	
}
