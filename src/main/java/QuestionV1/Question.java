package QuestionV1;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Question {

    private static String[] competences = { "Mathematique", "Langage C", "UML" };
    private static String[] langue = { "Français", "Anglais", "Espagnol" };
    protected static int idtechnique = 0;
    int idTechvisible;
    String enonceQ;
    String nomAuteur;
    String langueQ;
    String competenceQ;
    String variante;
    String opinion;
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
        this.idtechnique = this.idtechnique +1;
        this.idTechvisible = this.idtechnique;
      } 
    public Question(String idPere)
    {
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeR= new ArrayList <Reponse>();
        this.variante=idPere;
        this.idtechnique = this.idtechnique +1;
        this.idTechvisible = this.idtechnique;
      } 
    public void setReponse(Reponse e)
    {
      this.listeR.add(e);
    }
    public void setVar(String v)
    {
      this.variante=v;
    }
    public String getOpinion()
    {
      return this.opinion;
    }
    public String getCompetence()
    {
      return this.competenceQ;
    }
    public int getIdTech()
    {
      return this.idTechvisible;
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
    public String getVar()
    {
    	if(this.variante.equalsIgnoreCase("-"))
    		return this.variante;
		StringTokenizer st = new StringTokenizer(this.variante, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
	  String Newligne=System.getProperty("line.separator"); 
      return "- Idention question :"+tableauEntier[2]+Newligne+"- Auteur:"+tableauEntier[1];
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
    
    
    public static Question getQuestion(String id, List <Question> listeQ) 
    {
    	StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
        Question questionT = new Question();
        int idTemp = Integer.parseInt(tableauEntier[0]);
        for (Question quest : listeQ) 
        {
            if (quest.getIdTech() == idTemp) 
            {
            	if(quest.getAut().equalsIgnoreCase(tableauEntier[1]))
            	{
            		questionT= quest;
            	}
                
            }
        }
        return questionT;
    }
    
    public static List <Reponse> retourneReponse(String id, List <Question> listeQ) 
    {
    	StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
        Question questionT = new Question();
        int idTemp = Integer.parseInt(tableauEntier[0]);
        for (Question quest : listeQ) 
        {
            if (quest.getIdTech() == idTemp) 
            {
            	if(quest.getAut().equalsIgnoreCase(tableauEntier[1]))
            	{
            		questionT= quest;
            	}
                
            }
        }
        return questionT.listeR;
    }
    public List <Reponse> reponseR() 
    {
        return listeR;
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