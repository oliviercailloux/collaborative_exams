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
public class GestionSujet 
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    @PersistenceContext(unitName = "questT")
    EntityManager em; 
    Sujet sujetT;
    public void createSujet(String nom)
    {
    	this.sujetT = new Sujet(nom);
    }
    public Sujet getSujet()
    {
    	return this.sujetT;
    }
    public void setQuestion (Question e)
	{
		this.sujetT.insertSujet(e);
	}
    public void ouvertureSujet()
	{
        em.persist(this.sujetT);
	}
    public void addQuestion(String id)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question listeRechercher = (Question) q.getResultList().get(0);
        listeRechercher.setSujet(this.getSujet());
        this.sujetT.insertSujet(listeRechercher);
        em.merge(listeRechercher);
        //System.out.println("Size: " + listeRechercher.get(0).enonceQ);
	}
    public void commitSujet()
	{   
        em.detach(this.sujetT);

		em.merge(this.sujetT);
	}
    public List<String> getNomSujets()
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nomSujet FROM Sujet u", Sujet.class);
        List<String> listeQuestionA;
        listeQuestionA = q.getResultList();
       // Question test = userList;
		return listeQuestionA;
    	
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
