package QuestionV1;
import java.util.ArrayList;
import java.util.List;

public class Reponse {

	int idQuestion;
	String auteur;
    String textReponse;
    
    
    
    
    public Reponse(String textReponse)
    {
   
        this.textReponse = textReponse;
        System.out.println("test constr");
        
    } 
    
    public String getText()
    {
    	System.out.println("test text");
      return this.textReponse;
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
