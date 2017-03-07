package mainPackage;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

@ApplicationScoped
public class GestionReponse {
Reponse reponse;
EntityManager em; 
private static final String PERSISTENCE_UNIT_NAME = "questT";
private static EntityManagerFactory factory;

	public Reponse createReponse(Question questionA, String textRep, String pos)
    {
		reponse = new Reponse();
    	reponse.setTextRep(textRep);
    	reponse.setTrueRep(pos);
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
	
	public List <Reponse> retourneToutesLesReponses()
	{   
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	em = factory.createEntityManager();
    
	    Query q = em.createQuery("SELECT * FROM App.Reponses", Reponse.class);
	    List <Reponse> listeRechercher = q.getResultList();
	    em.close();
	    return listeRechercher;
	}
	
	public void modifierQuestion(Question question, String textRep, String pos)
    {
		factory = Persistence.createEntityManagerFactory( "questT" );
	    em = factory.createEntityManager( );
	    em.getTransaction( ).begin( );
	    Question qReference = em.find(Question.class, question);
	   
    	reponse.setTextRep(textRep);
    	reponse.setTrueRep(pos);
    	reponse.questionLien = qReference;
    	em.getTransaction( ).commit( );
    	
        em.close();
        factory.close();
    }
	

}
