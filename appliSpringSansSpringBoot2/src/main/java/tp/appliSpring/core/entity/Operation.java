package tp.appliSpring.core.entity;

import java.util.Date;

public class Operation {
	private Long numOp;  //id, auto_incr
	private String label; //ex: "achat xy" 
	private Double montant; //ex: -50 ou +60
	private Date dateOp; //ex: 2022-11-07 en base
	
	//plusieurs opérations pour un compte 
	//avec clef_etrangère (fk) : numCompte
	private Compte compte;
}
