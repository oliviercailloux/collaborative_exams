package QuestionV1;
import java.util.ArrayList;
import java.util.List;

public class Reponse {

	int idQuestion;
	String auteur;
    String textReponse;
    int trueRep;
    
    
    
    public Reponse(String textReponse,String pos)
    {
    	if(pos.equalsIgnoreCase("V"))
    	{
    		this.trueRep=1;
    	}
    	else
    	{
    		this.trueRep=0;
    	}
        this.textReponse = textReponse;
        System.out.println("test constr");
        
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
    public int getIdQuestion()
    {
      return this.idQuestion;
    }
    public String getAut()
    {
      return this.auteur;
    }
    

}
