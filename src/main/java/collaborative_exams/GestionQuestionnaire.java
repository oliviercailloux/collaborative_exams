package collaborative_exams;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class GestionQuestionnaire
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    @PersistenceContext(unitName = "questT")
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

        em.persist(this.questionnaireT);
	}
    public void addQuestion(Question app)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", app.getIdTech());
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.merge(listeRechercher);
        listeRechercher.setQuestionnaire(this.getQuestionnaire());
        this.questionnaireT.insertQuestionnaire(listeRechercher);
	}
    public void commitQuestionnaire()
	{   
    	em.detach(this.questionnaireT);
		em.merge(this.questionnaireT);
	}
    public List<String> getNomQuestionnaires()
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nomQuestionnaire FROM Questionnaire u", Questionnaire.class);
        List<String> listeQuestionA;
        listeQuestionA = q.getResultList();
		return listeQuestionA;
    	
    }
    public List<Question> getQuestionsQuestionnaire(String nomQuestionnaire)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnaire u where u.nomQuestionnaire like :arg1", Questionnaire.class);
        q.setParameter("arg1", nomQuestionnaire);
        List<Questionnaire> QuestionnaireTemp = q.getResultList();
		return QuestionnaireTemp.get(0).getQuestionsQuestionnaire();
    	
    }
    public List<Question> getQuestions(int identifiant)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnaire u where u.idQuestionnaire = :arg1", Questionnaire.class);
        q.setParameter("arg1", identifiant);
        List<Questionnaire> QuestionnaireTemp = q.getResultList();
		return QuestionnaireTemp.get(0).getQuestionsQuestionnaire();
    	
    }
    
    public Question addQuestionListTemp(String id)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        return listeRechercher;
        
	}
    
    
}
