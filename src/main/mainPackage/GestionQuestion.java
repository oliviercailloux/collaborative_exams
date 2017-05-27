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
public class GestionQuestion 
{
	Question questionT;
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
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
        //questionT.setListReponse(Question.retourneReponse(idPere, CreationEtAffichageQuestionForm.listeQ));
        questionT.setNiveau(niveau);
        questionT.nbVotePertinence = 0;
        questionT.totalNotePertinence = 0;
		return questionT;
    }
	public void addReponse(Reponse rep) throws Exception 
	{
		
        em.persist(rep);
    }
	public void ouvertureQuestion()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(this.questionT);
	}
	
	public void initiateQuestion(int id)
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", id);
        this.questionT = new Question();
        this.questionT = (Question) q.getResultList().get(0);
        em.getTransaction().begin();
        em.persist(this.questionT);
	}
	//avec session
	public void commitQuestion()
	{   questionT.setIdV(questionT.idtechnique);
		em.flush();
        em.persist(questionT);
        questionT.setIdV(questionT.idtechnique);
        questionT.setQuestionnaireNew();
		em.getTransaction().commit();
        em.close();
	}
	
	public void commitQuestionReponse()
	{  em.flush();
    em.persist(questionT);
		em.getTransaction().commit();
        em.close();
	}
	public List <Question> retourneToutesQuestions()
	{   
		factory = Persistence.createEntityManagerFactory("questT");
    	em = factory.createEntityManager();
    
	    Query q = em.createQuery("SELECT u FROM Question u", Question.class);
	    List <Question> listeRechercher = q.getResultList();
	    System.out.println("apres req");
	    em.close();
	    return listeRechercher;
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
	    	
	    System.out.println("apres req");
	    em.close();
	    return listeRechercher;
	}
	public Question retourneQuestion(String id)
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
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.close();
        return listeRechercher;
	}
	
	public Question retourneQuestionT(String id)
	{
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.close();
        return listeRechercher;
	}
	public Question retourneQuestionNote(String id, int note)
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
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.getTransaction().begin();
        em.persist(listeRechercher);
        listeRechercher.setNotePertinence(note);
		em.getTransaction().commit();///modif
        em.close();
        return listeRechercher;
	}
	public List <Question> retourneListQuestionComp(String comp)
	{
        factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        
        Query q = em.createQuery("SELECT u FROM Question u where u.competenceQ like :arg1", Question.class);
        q.setParameter("arg1", comp);
        List <Question> listeRechercher = q.getResultList();
        System.out.println("apres req");
        em.close();
        return listeRechercher;
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
		this.questionT = this.retourneQuestionT(tableauEntier[0]);
        int j =0;
        System.out.println("test1");
        while(this.questionT.reponseR().size() > j)
        {
        	if(this.questionT.reponseR().get(j).getid() == Integer.parseInt(tableauEntier[1]))
        	{
        		if(this.questionT.reponseR().get(j).trueRep==1)
        			return true;
				return false;
        	}
        	j++;
        }
        return false;
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
    
}
