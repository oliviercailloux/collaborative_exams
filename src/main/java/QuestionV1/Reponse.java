package QuestionV1;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Reponse {
	
	int idQuestion;
	String auteur;
    String textReponse;
    int trueRep;
    Reponse reponse;
    
    
    public Reponse()
    {
    	this.textReponse = ""; 
    	this.trueRep =2;
    } 
    
    public String getText()
    {
      return this.textReponse;
    }
    public String getPos()
    {
      if(this.trueRep==1)
      {
    	  return "Vrai";
      }
      else
      {
    	  return "Faux";
      }
    }
    public Reponse setReponse(String texteR, String position)
    {
    	reponse = new Reponse();
    	if(position.equalsIgnoreCase("V"))
    	{
    		reponse.trueRep=1;
    	}
    	else
    	{
    		reponse.trueRep=0;
    	}
        reponse.textReponse = texteR;
        return reponse;
    }
    public int getIdQuestion()
    {
      return this.idQuestion;
    }
    public String getAut()
    {
      return this.auteur;
    }
    

}