package collaborative_exams;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@ApplicationScoped
public class GestionReponse {
Reponse reponse;
private static final String PERSISTENCE_UNIT_NAME = "questT";
private static EntityManagerFactory factory;

	public Reponse createReponse(Question questionA, String textRep, String pos)
    {
		reponse = new Reponse();
    	reponse.setTextRep(textRep);
    	reponse.setTrueRep(pos);
    	System.out.println(questionA.getIdTech()+ " test");
    	reponse.setIdQ(questionA.getIdTech());
    	reponse.questionLien = questionA;
    	
		return reponse;
    }
	public Reponse getReponse()
	{
		return this.reponse;
	}
	@SuppressWarnings("unused")
	public void addReponse(Reponse rep) throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reponse test = new Reponse();
        em.getTransaction().begin();
        em.persist(rep);
        em.getTransaction().commit();
        em.close();
    }
	public void commitReponse()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reponse test = new Reponse();
        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        em.close();
	}

}
