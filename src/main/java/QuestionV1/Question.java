package QuestionV1;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@ApplicationScoped
public class Question {

    
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
    Question questionT;
    
    //Liste permettant de calculer la note de pertinence de la question
    //Element 0, somme de des notes attribué
    //Element 1, nombre personne ayant voté
    //List<Integer> notePertinence;

    //Attribut niveau de type String
    String niveau;
    
    public Question(){
          
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeR= new ArrayList <Reponse>();
        this.variante="-";
        this.niveau="";
        //this.notePertinence.add(0,0);
        //this.notePertinence.add(1,0);
       
      } 
    public Question(String pere)
    {
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeR= new ArrayList <Reponse>();
        this.variante=pere;
        this.niveau="";
        //this.notePertinence.add(0,0);
        //this.notePertinence.add(1,0);
        
      } 
    
    public Question createQuestionV(String nom, String langueI, String competenceI, String enonce, String idPere, int identifiant, String opinionI, String niveau)
    {
    	questionT = new Question();
    	questionT.setAut(nom);
    	questionT.setLangue(langueI);
    	questionT.setCompetence(competenceI);
    	questionT.setEnonce(enonce);
    	questionT.setVar(idPere);
    	questionT.setId(identifiant);
        questionT.setOpinion(opinionI);
        questionT.setListReponse(Question.retourneReponse(idPere, CreationEtAffichageQuestionForm.listeQ));
        questionT.setNiveau(niveau);
		return questionT;
    	
    }
    public void setListReponse(List <Reponse> e)
    {
    	
      this.listeR = e;
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
    public void setOpinion(String op)
    {
      this.opinion = op;
    }
    
    public String getCompetence()
    {
      return this.competenceQ;
    }
    public void setCompetence(String competence)
    {
      this.competenceQ = competence;
    }
    
    public int getIdTech()
    {
      return this.idTechvisible;
    }
    
    public String getLangue()
    {
     String str2 = new String(this.langueQ.getBytes(),Charset.forName("UTF-8"));
      return str2;
    }
    public void setLangue(String langue)
    {
      this.langueQ = langue;
    }
    
    public String getEnonce()
    {
      return this.enonceQ;
    }
    public void setEnonce(String enonce)
    {
      this.enonceQ = enonce;
    }
    
    public String getAut()
    {
      return this.nomAuteur;
    }
    public void setAut(String auteur)
    {
      this.nomAuteur = auteur;
    }
    
    public int getId()
    {
      return this.idQ;
    }
    public void setId(int id)
    {
      this.idQ=id;
      this.idtechnique = this.idtechnique +1;
      this.idTechvisible = this.idtechnique;
    }
    /***
     		Récupération Variante
     								***/
    public String getVar()
    {
    	/***
    	 	variante de Type:
    	 		- 	idTech +
    	 		-	Auteur +
    	 		-	Id
    	 */
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
      return "- Identifiant question : "+tableauEntier[2]+Newligne+" - Auteur:"+tableauEntier[1];
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
    
    /***
		On recupere une question selon son idTech et Auth
     ***/
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
    /***
			On récupère l'ensemble des Reponses d'une question
     															***/
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
    
    /*public void setNotePertinence (int note){
    	int x = notePertinence.get(1);
    	notePertinence.set(1, x++);
    	notePertinence.set(0, notePertinence.get(0)+note);
    	
    }
    
    public int getNotePertinence() 
    {
        return notePertinence.get(0)/notePertinence.get(1);
    } */
    
    public void setNiveau(String p) {
        niveau = p;
    }

    public String getNiveau() {
        return niveau;
    }
    
    
}
