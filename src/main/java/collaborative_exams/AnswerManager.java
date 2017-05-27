package collaborative_exams;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class AnswerManager {
Answer answer;
private static final String PERSISTENCE_UNIT_NAME = "questT";
private static EntityManagerFactory factory;

	public void createAnswer(Question questionA, String textAnswer, String pos)
    {
		answer = new Answer();
    	answer.setTextAnswer(textAnswer);
    	answer.setTrueFalse(pos);
    	answer.setIdQ(questionA.getIdTech());
    	answer.questionLink.add(questionA);
    	
		//return reponse;
    }
	public Answer getAnswer()
	{
		return this.answer;
	}
	public void commitAnswer()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Answer test = new Answer();
        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        em.close();
	}

}
