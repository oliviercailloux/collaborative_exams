package collaborative_exams;
import java.util.ArrayList;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="App.Sujets")
public class Sujet 
{	
	//@Inject
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idSujet;
	@OneToMany(mappedBy = "sujetLien", cascade = CascadeType.PERSIST)
	 List <Question> listeQuestionSujet;


	 String nomSujet;
	 public Sujet()
	 {
        this.nomSujet= "";
        this.listeQuestionSujet= new ArrayList <>();
	 } 
	 public Sujet(String nSujet)
	 {
        this.nomSujet= nSujet;
        this.listeQuestionSujet= new ArrayList <>();
	 } 
	 
	 public List<Question> getQuestionsSujet()
	 {
		 return this.listeQuestionSujet;
	 }
	 public void insertSujet(Question questionSujet)
	 {
		 this.listeQuestionSujet.add(questionSujet);
	 }
	 
	 public String getNomSujet()
	 {
		 return this.nomSujet;
	 }
	 
	 public static Sujet getSujet(String nom, List <Sujet> sujetQ) 
	    {
	        Sujet sujetT = new Sujet();
	        if(nom.isEmpty())
	        {
	        	sujetT.listeQuestionSujet=CreationEtAffichageQuestionForm.listeQ;
	        	return sujetT;
	        	
	        }
	        for (Sujet sujetTemp : sujetQ) 
	        {
	            if (sujetTemp.getNomSujet().equalsIgnoreCase(nom)) 
	            {
	               sujetT =sujetTemp;
	            }
	        }
	        return sujetT;
	    }
}
