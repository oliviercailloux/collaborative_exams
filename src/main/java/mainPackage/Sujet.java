package mainPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

public class Sujet 
{	
	//@Inject
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
        this.listeQuestionSujet= new ArrayList <Question>();
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
