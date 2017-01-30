package QuestionV1;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Compte {
	String identifiant;
	String mdp;
	String prenom;
	String nom;
	String mail;
	
	public Compte()
	{
		identifiant = "";
		mdp = "";
		prenom = "";
		nom = "";
		mail = "";
	}
	public boolean connexion(String identifiant, String mdp, List<Compte> listeC)
	{
		for (int i= 0; i<listeC.size() ; i++)
		{
			
			if(listeC.get(i).mdp.equals(mdp) && listeC.get(i).identifiant.equals(identifiant))return true;
		}
		
		return false;
		
	}
	public Compte creerCompte(String identifiant,String mdp,String prenom, String nom, String mail)
	{
		Compte temp = new Compte();
		temp.mail = mail;
		temp.identifiant = identifiant;
		temp.prenom = prenom;
		temp.nom = nom;
		temp.mdp = mdp;
		return temp;
	}
	public boolean existeDeja(List<Compte> listeC, String mail)
	{
		for (int i= 0; i<listeC.size() ; i++)
		{
			System.out.println(listeC.get(i).mail);
			System.out.println(mail);
			if(listeC.get(i).mail.equals(mail))return true;
		}
		
		return false;
		
	}


}
