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
import javax.persistence.Table;

@ApplicationScoped
@Entity
@Table(name="App.Questions")
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idtechnique;
	
    int idTechvisible;
    String statement;
    String authorName;
    String language;
    String skill;
    String variant;
    String opinion;
    int idQ;
    @OneToMany(mappedBy = "questionLink", cascade = CascadeType.PERSIST)
    List <Answer> listA;
    @ManyToOne(optional = true,fetch=FetchType.LAZY)
    @JoinColumn(name="SUJET_ID", nullable=true)
    Subject subjectLink;
    String level;
    //Element ton note the relevance of the question
    int nbVoteRelevance;
    int totalRelevanceMark;
    
    public Question(){
          
        this.statement= "";
        this.authorName="";
        this.language ="";
        this.skill ="";
        this.idQ = 0;
        this.opinion ="";
        this.listA= new ArrayList <>();
        this.variant="-";
        this.level="";
        this.nbVoteRelevance = 0;
        this.totalRelevanceMark = 0;
       
      } 
    public Question(String pere)
    {
        this.statement= "";
        this.authorName="";
        this.language ="";
        this.skill ="";
        this.idQ = 0;
        this.opinion ="";
        this.listA= new ArrayList <>();
        this.variant=pere;
        this.level="";
        this.nbVoteRelevance = 0;
        this.totalRelevanceMark = 0;
      } 
    
    public void setListAnswer(List <Answer> e)
    {
      this.listA = e;
    }
    public void setAnswer(Answer e)
    {
      this.listA.add(e);
    }
    public void setSubject(Subject e)
    {
      this.subjectLink = e;
    }
    public void setVar(String v)
    {
      this.variant=v;
    }
    
    public String getOpinion()
    {
      return this.opinion;
    }
    public void setOpinion(String op)
    {
      this.opinion = op;
    }
    
    public String getSkill()
    {
      return this.skill;
    }
    public void setSkill(String skill)
    {
      this.skill = skill;
    }
    
    public int getIdTech()
    {
      return this.idTechvisible;
    }
    
    public String getLanguage()
    {
      //String str2 = new String(this.language.getBytes(),Charset.forName("UTF-8"));
      //return str2;
    	return this.language;
    }
    public void setLanguage(String language)
    {
      this.language = language;
    }
    
    public String getStatement()
    {
      return this.statement;
    }
    public void setStatement(String statement)
    {
      this.statement = statement;
    }
    
    public String getAut()
    {
      return this.authorName;
    }
    public void setAut(String author)
    {
      this.authorName = author;
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
     		Get the Variant
     								***/
    public String getVar()
    {
    	/***
    	 	variant de Type:
    	 		- 	idTech +
    	 		-	Auteur +
    	 		-	Id
    	 */
    	if(this.variant.equalsIgnoreCase("-"))
    		return this.variant;
		StringTokenizer st = new StringTokenizer(this.variant, "+"); 
		String array[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			array[i] = st.nextToken();
			i=i+1;
	     }
	  String Newligne=System.getProperty("line.separator"); 
      return "- Identifiant question : "+array[2]+Newligne+" - Auteur:"+array[1];
    }
    
    public String getVariant()
    {
    	return this.variant;
    }
    public static List <Question> trouveQuestionParMatiere(String matiere, List <Question> listeQ) 
    {
        List <Question> questionT = new ArrayList <>();
        if(matiere.isEmpty())
        	return listeQ;
        for (Question quest : listeQ) 
        {
            if (quest.getSkill().equalsIgnoreCase(matiere)) 
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
		String array[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			array[i] = st.nextToken();
			i=i+1;
	     }
        Question questionT = new Question();
        int idTemp = Integer.parseInt(array[0]);
        for (Question quest : listeQ) 
        {
            if (quest.getIdTech() == idTemp) 
            {
            	if(quest.getAut().equalsIgnoreCase(array[1]))
            	{
            		questionT= quest;
            	}
                
            }
        }
        return questionT;
    }
    /***
			Get all the answers of the question
     															***/
   public static List <Answer> returnAnswer(String id, List <Question> listeQ) 
    {
    	StringTokenizer st = new StringTokenizer(id, "+"); 
		String array[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			array[i] = st.nextToken();
			i=i+1;
	     }
        Question questionT = new Question();
        int idTemp = Integer.parseInt(array[0]);
        for (Question quest : listeQ) 
        {
            if (quest.getIdTech() == idTemp) 
            {
            	if(quest.getAut().equalsIgnoreCase(array[1]))
            	{
            		questionT= quest;
            	}
                
            }
        }
        return questionT.listA;
    }
    public List <Answer> answerA() 
    {
        return listA;
    }
    
    public void setLevel(String p) {
        level = p;
    }

    public String getLevel() {
        return level;
    }
    
    public void setRelevanceMark(int note){
    	nbVoteRelevance ++;
    	totalRelevanceMark = totalRelevanceMark + note;
    }
    
    public float getRelevanceMark() {
    	return 	(nbVoteRelevance == 0) ? 0 : totalRelevanceMark/nbVoteRelevance ;
    }
    
    public int getNbVoteRelevance() {
    	return nbVoteRelevance;
    }
    
    public String getSubjectName() 
    {
    	return (subjectLink==null) ? "-":subjectLink.getSubjectName();
    }

}
