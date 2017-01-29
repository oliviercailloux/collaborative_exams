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
    public void setIdQ(int id)
    {
    	this.idQuestion=id;
    }
    public void setAuteurQ(String auteur)
    {
    	this.auteur=auteur;
    } 
    
    public void setTextRep(String textR)
    {
    	this.textReponse=textR;
    }
    public void setTrueRep(String position)
    {
    	if(position.equalsIgnoreCase("V"))
    	{
    		this.trueRep=1;
    	}
    	else
    	{
    		this.trueRep=0;
    	}
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