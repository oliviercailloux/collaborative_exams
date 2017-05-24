package collaborative_exams;

import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ApplicationScoped
public class GestionQuestionnaire
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    EntityManager em; 
    Questionnaire questionnaireT;
    public void createQuestionnaire(String nom)
    {
    	this.questionnaireT = new Questionnaire(nom);
    }
    public Questionnaire getQuestionnaire()
    {
    	return this.questionnaireT;
    }
    public void setQuestion (Question e)
	{
		this.questionnaireT.insertQuestionnaire(e);
	}
    public void ouvertureQuestionnaire()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(this.questionnaireT);
	}
    public void addQuestion(Question app)
	{
		/*StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }*/
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", app.getIdTech());
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.persist(listeRechercher);
        listeRechercher.setQuestionnaire(this.getQuestionnaire());
        this.questionnaireT.insertQuestionnaire(listeRechercher);
        //System.out.println("Size: " + listeRechercher.get(0).enonceQ);
	}
    public void commitQuestionnaire()
	{   
		em.getTransaction().commit();
        em.close();
	}
    public List<String> getNomQuestionnaires()
    {
    	factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nomQuestionnaire FROM Questionnaire u", Questionnaire.class);
        List<String> listeQuestionA;
        listeQuestionA = q.getResultList();
       // Question test = userList;
        em.close();
		return listeQuestionA;
    	
    }
    public List<Question> getQuestionsQuestionnaire(String nomQuestionnaire)
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnaire u where u.nomQuestionnaire like :arg1", Questionnaire.class);
        q.setParameter("arg1", nomQuestionnaire);
        List<Questionnaire> QuestionnaireTemp = q.getResultList();
        em.close();
		return QuestionnaireTemp.get(0).getQuestionsQuestionnaire();
    	
    }
    public List<Question> getQuestions(int identifiant)
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnaire u where u.idQuestionnaire = :arg1", Questionnaire.class);
        q.setParameter("arg1", identifiant);
        List<Questionnaire> QuestionnaireTemp = q.getResultList();
        em.close();
		return QuestionnaireTemp.get(0).getQuestionsQuestionnaire();
    	
    }
    
    public Question addQuestionListTemp(String id)
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
    
    
}
