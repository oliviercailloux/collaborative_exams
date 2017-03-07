package mainPackage;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@ApplicationScoped
@Entity
@Table(name="App.Questions")
@PrimaryKeyJoinColumn(referencedColumnName="id_question")

public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idtechnique;
	
	@Column(name="idTechvisible", table="App.Questions")
    int idTechvisible;
	@Column(name="enonceQ", table="App.Questions")
    String enonceQ;
	@Column(name="nomAuteur", table="App.Questions")
    String nomAuteur;
	@Column(name="langueQ", table="App.Questions")
    String langueQ;
	@Column(name="competenceQ", table="App.Questions")
    String competenceQ;
	@Column(name="variante", table="App.Questions")
    String variante;
	@Column(name="opinion", table="App.Questions")
    String opinion;
	@Column(name="idQ", table="App.Questions")
    int idQ;
    @OneToMany(mappedBy = "questionLien", cascade = CascadeType.PERSIST)
    List <Reponse> listeR;
    @ManyToOne(optional = true,fetch=FetchType.LAZY)
    @JoinColumn(name="SUJET_ID", nullable=true)
    Sujet sujetLien;
    //Attribut niveau de type String
    String niveau;
    //Element de pour noter la pertinence de la question
    int nbVotePertinence;
    int totalNotePertinence;
    
    public Question(){
          
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeR= new ArrayList <>();
        this.variante="-";
        this.niveau="";
        this.nbVotePertinence = 0;
        this.totalNotePertinence = 0;
       
      } 
    public Question(String pere)
    {
        this.enonceQ= "";
        this.nomAuteur="";
        this.langueQ ="";
        this.competenceQ ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeR= new ArrayList <>();
        this.variante=pere;
        this.niveau="";
        this.nbVotePertinence = 0;
        this.totalNotePertinence = 0;
      } 
    
    public void setListReponse(List <Reponse> e)
    {
      this.listeR = e;
    }
    public void setReponse(Reponse e)
    {
      this.listeR.add(e);
    }
    public void setSujet(Sujet e)
    {
      this.sujetLien = e;
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
      this.idTechvisible = this.idtechnique;
    }
    public void setIdV(int id)
    {
      this.idTechvisible = id;
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
    
    public String getVariante()
    {
    	return this.variante;
    }
    public static List <Question> trouveQuestionParMatiere(String matiere, List <Question> listeQ) 
    {
        List <Question> questionT = new ArrayList <>();
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
    
    public void setNiveau(String p) {
        niveau = p;
    }

    public String getNiveau() {
        return niveau;
    }
    
    public void setNotePertinence(int note){
    	nbVotePertinence ++;
    	totalNotePertinence = totalNotePertinence + note;
    }
    
    public float getNotePertinence() {
    	return 	(nbVotePertinence == 0) ? 0 : totalNotePertinence/nbVotePertinence ;
    }
    
    public int getNbVotePertinence() {
    	return nbVotePertinence;
    }
    
    public String getNomSujet() 
    {
    	
    	return (sujetLien==null) ? "-":sujetLien.getNomSujet();
    }
    

    
}
