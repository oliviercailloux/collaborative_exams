package QuestionV1;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Question {

    private static String[] competences = { "Mathematique", "Langage C", "UML" };
    private static String[] langue = { "Français", "Anglais", "Espagnol" };
    String enonceQ;
    String nomAuteur;
    String langueQ;
    String competenceQ;
    String variante;
    List <Reponse> listeR;
    int idQ;
    
    public Question(){
          
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.listeR= new ArrayList <Reponse>();
        this.variante="-";
      } 
    public Question(int idPere,String auteurPere)
    {
        
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.listeR= new ArrayList <Reponse>();
        this.variante="auteurPere+"+idPere;
      } 
    public void setReponse(Reponse e)
    {
      this.listeR.add(e);
    }
    public String getCompetence()
    {
      return this.competenceQ;
    }
    public String getLangue()
    {
     String str2 = new String(this.langueQ.getBytes(),Charset.forName("UTF-8"));
     System.out.println("laaaaaaaaaaaaaaaaa   "+ str2 );
      return str2;
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
        if(matiere.isEmpty())
        	return listeQ;
        for (Question quest : listeQ) 
        {
            if (quest.getCompetence().equalsIgnoreCase(matiere)) 
            {
                questionT.add(quest);
            }
        }
        return questionT;
    }
    
    public static String[] trouveLangueP() 
    {
        return langue;
    } 

    public static String[] trouveCompetenceP() 
    {
        return competences;
    }
}