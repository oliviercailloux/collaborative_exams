import java.util.ArrayList;
import java.util.List;

public class Reponse {

	int idQuestion;
	String auteur;
    String textReponse;
    
    
    
    
    public Reponse(int idQuestion, String auteur, String textReponse){
        this.idQuestion= idQuestion;
        this.auteur= auteur;
        this.textReponse = textReponse;
        
      } 
    
    public String getText()
    {
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
