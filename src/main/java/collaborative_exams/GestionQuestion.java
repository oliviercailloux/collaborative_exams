package collaborative_exams;
import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.derby.tools.sysinfo;

@RequestScoped
@Transactional
public class GestionQuestion 
{
	Question questionT;
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
	@PersistenceContext(unitName = "questT")
    EntityManager em; 
	public void createQuestion(String nom, String langueI, String competenceI, String enonce, int identifiant, String niveau)
    {
    	this.questionT = new Question();
    	this.questionT.setAut(nom);
    	this.questionT.setLangue(langueI);
    	this.questionT.setCompetence(competenceI);
    	this.questionT.setEnonce(enonce);
    	this.questionT.setId(identifiant);
    	this.questionT.setNiveau(niveau);
    	this.questionT.nbVotePertinence = 0;
    	this.questionT.totalNotePertinence = 0;
    	questionT.setQuestionnaireNew();

    }
	public void setReponseG (Reponse e)
	{
		this.questionT.setReponse(e);
	}
	public Question getQuestion()
	{
		return this.questionT;
	}
	public Question createQuestionV(String nom, String langueI, String competenceI, String enonce, String idPere, int identifiant, String opinionI, String niveau)
    {
    	questionT = new Question(idPere);
    	questionT.setAut(nom);
    	questionT.setLangue(langueI);
    	questionT.setCompetence(competenceI);
    	questionT.setEnonce(enonce);
    	questionT.setVar(idPere);
    	questionT.setId(identifiant);
        questionT.setOpinion(opinionI);
        questionT.setNiveau(niveau);
        questionT.nbVotePertinence = 0;
        questionT.totalNotePertinence = 0;
        
		return questionT;
    }
	public void addReponse(Reponse rep) throws Exception 
	{
		
        em.persist(rep);
        em.flush();
        this.setReponseG(rep);
        
    }
	public void ouvertureQuestion(String nom, String langueI, String competenceI, String enonce, int identifiant, String niveau)
	{
		System.out.println("je suis ici");
		this.createQuestion(nom, langueI, competenceI, enonce, identifiant, niveau);
		System.out.println("je suis ici  "+this.questionT.getAut());
		em.persist(this.questionT);
		questionT.setIdV(questionT.idtechnique);
		System.out.println("pleure" + this.questionT.getIdTech());
		em.flush();
		questionT.setIdV(this.questionT.idtechnique);
	}
	
	public void initiateQuestion(int id)
	{
		System.out.println("test ici");
		Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", id);
        this.questionT = new Question();
        this.questionT = (Question) q.getResultList().get(0);
        //em.persist(this.questionT);
		System.out.println("test la");

	}
	//avec session
	public void commitQuestion()
	{   
		em.persist(questionT);
	}
	
	public void commitQuestionReponse()
	{  	em.merge(questionT);
		em.flush();
	}
	public List <Question> retourneToutesQuestions()
	{   
		System.out.println("test");
	    Query q = em.createQuery("SELECT u FROM Question u", Question.class);
	    List <Question> listeRechercher = q.getResultList();
	    System.out.println("apres req");
	    return listeRechercher;
	}
	public void retourneToutesQuestion()
	{   
	    Query q = em.createQuery("SELECT u FROM Question u", Question.class);
	    List <Question> listeRechercher = q.getResultList();
	    System.out.println("apres req");
	    System.out.println(listeRechercher.get(0).getAut());
	}
	public List <Question> retourneDiff(List <Question> questTemp)
	{   
		factory = Persistence.createEntityManagerFactory("questT");
    	em = factory.createEntityManager();
    
	    Query q = em.createQuery("SELECT u FROM Question u", Question.class);
	    List <Question> listeRechercher = q.getResultList();
	    
	    for(Question s : questTemp)
	    {
	    	for (int i = 0; i<listeRechercher.size(); i++)
	    	{
	    		if(listeRechercher.get(i).getIdTech()==s.getIdTech())
		    	{
		    		listeRechercher.remove(i);
		    	}
	    	}
	    }
	    	
	    em.close();
	    return listeRechercher;
	}
	public Question retourneQuestion(String id)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        return listeRechercher;
	}
	
	public Question retourneQuestionT(String id)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        return listeRechercher;
	}
	public Question retourneQuestionNote(String id, int note)
	{
        
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        listeRechercher.setNotePertinence(note);
        em.merge(listeRechercher);
        return listeRechercher;
	}
	public List <Question> retourneListQuestionComp(String comp)
	{
        Query q = em.createQuery("SELECT u FROM Question u where u.competenceQ like :arg1", Question.class);
        q.setParameter("arg1", comp);
        List <Question> listeRechercher = q.getResultList();
        System.out.println("apres req");
        return listeRechercher;
	}
	
    public void modifierQuestion(String nom, String langueI, String competenceI, String enonce, int identifiant, String niveau)
    {
        factory = Persistence.createEntityManagerFactory( "questT" );
        em = factory.createEntityManager( );
        em.getTransaction( ).begin( );
        Question quest = em.find(Question.class, identifiant);
       
        this.questionT.setAut(nom);
        this.questionT.setLangue(langueI);
        this.questionT.setCompetence(competenceI);
        this.questionT.setEnonce(enonce);
        this.questionT.setNiveau(niveau);
        em.getTransaction( ).commit( );
        
        System.out.println(quest);
        em.close();
        factory.close();
    }
    
    public void supprimerQuestion( int identifiant)
    {
        factory = Persistence.createEntityManagerFactory( "questT" );
        em = factory.createEntityManager( );
        em.getTransaction( ).begin( );
        Question quest = em.find(Question.class, identifiant);
        
        System.out.println(quest);
        em.remove(quest);
        em.getTransaction( ).commit( );
        em.close();
        factory.close();
    }
    public List<Question> getQuestionsSujet(String nomSujet)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Sujet u where u.nomSujet like :arg1", Sujet.class);
        q.setParameter("arg1", nomSujet);
        List<Sujet> sujetTemp = q.getResultList();
        System.out.println("final "+sujetTemp.get(0).getQuestionsSujet().get(0).getAut());
		return sujetTemp.get(0).getQuestionsSujet();
    	
    }
    
}
