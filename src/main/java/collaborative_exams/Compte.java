package collaborative_exams;

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
	public boolean connexion(String id, String password, List<Compte> listeC)
	{
		for (int i= 0; i<listeC.size() ; i++)
		{
			
			if(listeC.get(i).mdp.equals(password) && listeC.get(i).identifiant.equals(id))return true;
		}
		
		return false;
		
	}
	public Compte creerCompte(String id,String password,String firstname, String name, String email)
	{
		Compte temp = new Compte();
		temp.mail = email;
		temp.identifiant = id;
		temp.prenom = firstname;
		temp.nom = name;
		temp.mdp = password;
		return temp;
	}
	public boolean existeDeja(List<Compte> listeC, String m)
	{
		for (int i= 0; i<listeC.size() ; i++)
		{
			System.out.println(listeC.get(i).mail);
			System.out.println(m);
			if(listeC.get(i).mail.equals(m))return true;
		}
		
		return false;
		
	}


}
