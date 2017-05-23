package mainPackage;
import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.derby.tools.sysinfo;

@ApplicationScoped
public class QuestionManager 
{
	Question questionT;
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    EntityManager em; 
	public void createQuestion(String name, String language, String skill, String stmnt, int id, String niveau)
    {
    	this.questionT = new Question();
    	this.questionT.setAut(name);
    	this.questionT.setLanguage(language);
    	this.questionT.setSkill(skill);
    	this.questionT.setStatement(stmnt);
    	this.questionT.setId(id);
    	this.questionT.setLevel(niveau);
    	this.questionT.nbVoteRelevance = 0;
    	this.questionT.totalRelevanceMark = 0;
    }
	public void setAnswerG (Answer e)
	{
		this.questionT.setAnswer(e);
	}
	public Question getQuestion()
	{
		return this.questionT;
	}
	public Question createQuestionV(String name, String language, String skill, String stmnt, String idPere, int id, String opinionI, String niveau)
    {
    	questionT = new Question(idPere);
    	questionT.setAut(name);
    	questionT.setLanguage(language);
    	questionT.setSkill(skill);
    	questionT.setStatement(stmnt);
    	questionT.setVar(idPere);
    	questionT.setId(id);
        questionT.setOpinion(opinionI);
        //questionT.setListReponse(Question.retourneReponse(idPere, CreationEtAffichageQuestionForm.listeQ));
        questionT.setLevel(niveau);
        questionT.nbVoteRelevance = 0;
        questionT.totalRelevanceMark = 0;
		return questionT;
    }
	public void addAnswer(Answer ans) throws Exception 
	{
        em.persist(ans);
    }
	public void openQuestion()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(this.questionT);
	}
	public void commitQuestion()
	{   questionT.setIdV(questionT.idtechnique);
		System.out.println("Avant   " +questionT.idTechvisible); // A ENLEVER
		em.flush();
        em.persist(questionT);
        questionT.setIdV(questionT.idtechnique);
		System.out.println("Apres    " +questionT.idTechvisible); // A ENLEVER
		em.getTransaction().commit();
        em.close();
	}
	public List <Question> returnAllQuestions()
	{   
		factory = Persistence.createEntityManagerFactory("questT");
    	em = factory.createEntityManager();
    
	    Query q = em.createQuery("SELECT u FROM Question u", Question.class);
	    List <Question> searchList = q.getResultList();
	    System.out.println("apres req"); // A ENLEVER
	    em.close();
	    return searchList;
	}
	public Question returnQuestion(String id)
	{
		StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(tableauEntier[0]));
        Question searchList = (Question) q.getResultList().get(0);
        em.close();
        return searchList;
	}
	
	public Question returnQuestionT(String id)
	{
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question searchList = (Question) q.getResultList().get(0);
        em.close();
        return searchList;
	}
	public Question returnQuestionNote(String id, int note)
	{
		StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(tableauEntier[0]));
        Question searchList = (Question) q.getResultList().get(0);
        em.getTransaction().begin();
        em.persist(searchList);
        searchList.setRelevanceMark(note);
		em.getTransaction().commit(); ///modif
        em.close();
        return searchList;
	}
	public List <Question> returnListQuestionSkill(String comp)
	{
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        Query q = em.createQuery("SELECT u FROM Question u where u.competenceQ like :arg1", Question.class);
        q.setParameter("arg1", comp);
        List <Question> searchList = q.getResultList();
        System.out.println("apres req");
        em.close();
        return searchList;
	}
	
	public boolean verifyR( String id)
	{
        System.out.println("test2");
		StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[2];
		int i=0;
		while (st.hasMoreTokens()) 
		{
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
		this.questionT = this.returnQuestionT(tableauEntier[0]);
        int j =0;
        System.out.println("test1");
        while(this.questionT.answerA().size() > j)
        {
        	if(this.questionT.answerA().get(j).getid() == Integer.parseInt(tableauEntier[1]))
        	{
        		if(this.questionT.answerA().get(j).trueRep==1)
        			return true;
        		else
        			return false;
        	}
        	j++;
        }
        return false;
	}
	
    public void modifierQuestion(String name, String language, String skill, String stmnt, int id, String niveau)
    {
        factory = Persistence.createEntityManagerFactory( "questT" );
        em = factory.createEntityManager( );
        em.getTransaction( ).begin( );
        Question quest = em.find(Question.class, id);
       
        this.questionT.setAut(name);
        this.questionT.setLanguage(language);
        this.questionT.setSkill(skill);
        this.questionT.setStatement(stmnt);
        this.questionT.setLevel(niveau);
        em.getTransaction( ).commit( );
        
        System.out.println(quest);
        em.close();
        factory.close();
    }
    
    public void supprimerQuestion( int id)
    {
        factory = Persistence.createEntityManagerFactory( "questT" );
        em = factory.createEntityManager( );
        em.getTransaction( ).begin( );
        Question quest = em.find(Question.class, id);
        
        System.out.println(quest);
        em.remove(quest);
        em.getTransaction( ).commit( );
        em.close();
        factory.close();
    }
    
}
