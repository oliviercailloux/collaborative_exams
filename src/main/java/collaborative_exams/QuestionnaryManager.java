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
public class QuestionnaryManager
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    @PersistenceContext(unitName = "questT")
    EntityManager em; 
    Questionnary questionnaryT;
    public void createQuestionnary(String nom)
    {
    	this.questionnaryT = new Questionnary(nom);
    }
    public Questionnary getQuestionnary()
    {
    	return this.questionnaryT;
    }
    public void setQuestion (Question e)
	{
		this.questionnaryT.insertQuestionnary(e);
	}
    public void openQuestionnary()
	{
        em.persist(this.questionnaryT);
	}
    public void addQuestion(Question app)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", app.getIdTech());
        Question searchList = (Question) q.getResultList().get(0);
        em.merge(searchList);
        searchList.setQuestionnaire(this.getQuestionnary());
        this.questionnaryT.insertQuestionnary(searchList);
	}
    public void commitQuestionnary()
	{   
    	em.detach(this.questionnaryT);
		em.merge(this.questionnaryT);
	}
    public List<String> getNameQuestionnaries()
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nameQuestionnary FROM Questionnary u", Questionnary.class);
        List<String> listeQuestionA;
        listeQuestionA = q.getResultList();
		return listeQuestionA;
    	
    }
    public List<Question> getQuestionsQuestionnary(String nameQuestionnary)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnary u where u.nameQuestionnary like :arg1", Questionnary.class);
        q.setParameter("arg1", nameQuestionnary);
        List<Questionnary> QuestionnaryTemp = q.getResultList();
		return QuestionnaryTemp.get(0).getQuestionsQuestionnary();
    	
    }
    public List<Question> getQuestions(int identifiant)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Questionnary u where u.idQuestionnary = :arg1", Questionnary.class);
        q.setParameter("arg1", identifiant);
        List<Questionnary> QuestionnaireTemp = q.getResultList();
		return QuestionnaireTemp.get(0).getQuestionsQuestionnary();
    	
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
