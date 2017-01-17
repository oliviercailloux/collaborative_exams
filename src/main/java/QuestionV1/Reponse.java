package QuestionV1;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Reponse {

	int idQuestion;
	String auteur;
    String textReponse;
    int trueRep;
    
    
    
    public Reponse()
    {
    	this.textReponse = ""; 
    	this.trueRep =2;
    } 
    
    public String getText()
    {
    	System.out.println("test text");
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
    public void setReponse(String texteR, String position)
    {
    	if(position.equalsIgnoreCase("V"))
    	{
    		this.trueRep=1;
    	}
    	else
    	{
    		this.trueRep=0;
    	}
        this.textReponse = texteR;
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
