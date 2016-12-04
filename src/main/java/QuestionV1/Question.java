package QuestionV1;
import java.util.ArrayList;
import java.util.List;


public class Question {

    /**
     * La liste de toutes les villes connues dans la base de données.
     */
    private static String[] competences = { "Mathematique", "Langage C", "UML" };
    String enonceQ;
    String nomAuteur;
    String competenceQ;
    int idQ;
    
    public Question(){
          
        this.enonceQ= "";
        this.nomAuteur="";
        this.competenceQ ="";
        this.idQ = 0;
      } 
    public String getCompetence()
    {
      return this.competenceQ;
    }
    public String getEnonce()
    {
      return this.enonceQ;
    }
    public String getAut()
    {
      return this.nomAuteur;
    }
    public int getId()
    {
      return this.idQ;
    }
    public static List <Question> trouveQuestionParMatiere(String matiere, List <Question> listeQ) 
    {
        List <Question> questionT = new ArrayList <Question>();
        for (Question quest : listeQ) 
        {
            if (quest.getCompetence().equalsIgnoreCase(matiere)) 
            {
                questionT.add(quest);
            }
        }
        return questionT;
    }
    

    public static String[] trouveCompetenceP() 
    {
        return competences;
    }
}