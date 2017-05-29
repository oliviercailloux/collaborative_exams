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
    @ManyToMany(mappedBy = "questionLink", cascade = CascadeType.PERSIST)
    List <Answer> listeA;
    @ManyToMany
    @JoinColumn(name="SUBJECT_ID", nullable=true)
    List<Subject> subjectLink;
    
    @ManyToMany
    @JoinColumn(name="QUESTION_ID", nullable=true)
    List<Questionnary> questionnaryLink;
    //String Level attributs 
    String level;
    //Elements to give a mark to the relevance of the question
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
      this.subjectLink.add(e);
    }
    public void setSubjectNew()
    {
    	this.subjectLink = new ArrayList <>();
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
    public void setVar()
    {
      this.variant="-";
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
    	
    	return this.variant;
    }
    
    public String getVariant()
    {
    	return this.variant;
    }
    //Find a question using its matter in a list
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
		We get the question according to its idTech and Auth
     ***/
  
  
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
    
    public int getNbVoteRelevance() 
    {
    	return nbVoteRelevance;
    }
    
   
    public String getNameSubject() 
    {
    	String nameSubject="";
    	for(int i= 0; i<this.subjectLink.size();i++ )
    	{
    		if(i==(subjectLink.size()-1))
    		{
    			nameSubject= nameSubject + subjectLink.get(i).getNameSubject();
    		}
    		else
    		{
    			nameSubject = nameSubject +subjectLink.get(i).getNameSubject()+"-";
    		}
    	}
    	return nameSubject;
    }
    
    public String getSubjectName() 
    {
    	return (subjectLink==null) ? "-":this.getNameSubject();
    }
    

    
}
