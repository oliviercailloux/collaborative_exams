package mainPackage;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@ApplicationScoped
public class AnswerManager {
Answer answer;
private static final String PERSISTENCE_UNIT_NAME = "questT";
private static EntityManagerFactory factory;

	public Answer createAnswer(Question questionA, String textAnswer, String pos)
    {
		answer = new Answer();
    	answer.setTextAnswer(textAnswer);
    	answer.setTrueFalse(pos);
    	System.out.println(questionA.getIdTech()+ " test");
    	answer.setIdQ(questionA.getIdTech());
    	answer.questionLink = questionA;
    	
		return answer;
    }
	public Answer getAnswer()
	{
		return this.answer;
	}
	@SuppressWarnings("unused")
	public void addAnswer(Answer answer) throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Answer test = new Answer();
        em.getTransaction().begin();
        em.persist(answer);
        em.getTransaction().commit();
        em.close();
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
