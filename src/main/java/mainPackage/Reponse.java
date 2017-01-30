package mainPackage;
import javax.enterprise.context.ApplicationScoped;

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
      return (this.trueRep==1)?"Vrai":"Faux";
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