package collaborative_exams;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@ApplicationScoped
@Entity
@Table(name="APP.QUESTION")
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int idtechnique;
    int idTechvisible;
    String statement;
    String authorName;
    String language;
    String skill;
    String variant;
    String opinion;
    int idQ;
    @OneToMany(mappedBy = "questionLink", cascade = CascadeType.PERSIST)
    List <Answer> listeA;
    @ManyToOne(optional = true,fetch=FetchType.LAZY)
    @JoinColumn(name="SUBJECT_ID", nullable=true)
    Subject subjectLink;
    @ManyToMany
    @JoinColumn(name="QUESTION_ID", nullable=true)
    List<Questionnary> questionnaryLink;
    //Attribut niveau de type String
    String level;
    //Element de pour noter la pertinence de la question
    int nbVoteRelevance;
    int totalRelevanceMark;
    
    public Question(){
          
        this.statement= "";
        this.authorName="";
        this.language ="";
        this.skill ="";
        this.idQ = 0;
        this.opinion ="";
        this.listeA= new ArrayList <>();
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
        this.listeA= new ArrayList <>();
        this.variant=pere;
        this.level="";
        this.nbVoteRelevance = 0;
        this.totalRelevanceMark = 0;
      } 
    
    public void setListAnswer(List <Answer> e)
    {
      this.listeA = e;
    }
    public void setAnswer(Answer e)
    {
      this.listeA.add(e);
    }
    public void setSubject(Subject e)
    {
      this.subjectLink = e;
    }
    public void setQuestionnaireNew()
    {
    	this.questionnaryLink = new ArrayList <>();
    }
    public void setQuestionnaire( Questionnary e)
    {
      this.questionnaryLink.add(e);
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
      String str2 = new String(this.language.getBytes(),Charset.forName("UTF-8"));
      return str2;
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
    public void setAut(String auteur)
    {
      this.authorName = auteur;
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
    public int getIdt()
    {
      return this.idtechnique;
    }
    public void setIdV(int id)
    {
      this.idTechvisible = id;
    }
    /***
     		!!!!! A mettre dans Une gestion Récupération Variante !!!!
     								***/
    public String getVar()
    {
    	/***
    	 	variante de Type:
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
	  String NewLine=System.getProperty("line.separator"); 
      return "- Identifiant question : "+array[2]+NewLine+" - Auteur:"+array[1];
    }
    
    public String getVariant()
    {
    	return this.variant;
    }
    
    public static List <Question> findQuestionByMatter(String matter, List <Question> listQ) 
    {
        List <Question> questionT = new ArrayList <>();
        if(matter.isEmpty())
        	return listQ;
        for (Question quest : listQ) 
        {
            if (quest.getSkill().equalsIgnoreCase(matter)) 
            {
                questionT.add(quest);
            }
        }
        return questionT;
    }
    
    /***
		On recupere une question selon son idTech et Auth
     ***/
    public static Question getQuestion(String id, List <Question> listQ) 
    {
        Question questionT = new Question();
        int idTemp = Integer.parseInt(id);
        for (Question quest : listQ) 
        {
        }
        return questionT;
    }
  
    public List <Answer> answerA() 
    {
        return listeA;
    }
    
    public void setLevel(String l) {
        level = l;
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
    	return (subjectLink==null) ? "-":subjectLink.getNameSubject();
    }
    

    
}
