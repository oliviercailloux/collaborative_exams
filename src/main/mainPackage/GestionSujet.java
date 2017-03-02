package mainPackage;

import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ApplicationScoped
public class GestionSujet 
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
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
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(this.sujetT);
	}
    public void addQuestion(String id)
	{
		StringTokenizer st = new StringTokenizer(id, "+"); 
		String tableauEntier[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			tableauEntier[i] = st.nextToken();
			i=i+1;
	     }
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(tableauEntier[0]));
        Question listeRechercher = (Question) q.getResultList().get(0);
        em.persist(listeRechercher);
        listeRechercher.setSujet(this.getSujet());
        this.sujetT.insertSujet(listeRechercher);
        //System.out.println("Size: " + listeRechercher.get(0).enonceQ);
	}
    public void commitSujet()
	{   
		em.getTransaction().commit();
        em.close();
	}
    public List<String> getNomSujets()
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nomSujet FROM Sujet u", Sujet.class);
        List<String> listeQuestionA;
        listeQuestionA = q.getResultList();
       // Question test = userList;
        em.close();
		return listeQuestionA;
    	
    }
    public List<Question> getQuestionsSujet(String nomSujet)
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Sujet u where u.nomSujet like :arg1", Sujet.class);
        q.setParameter("arg1", nomSujet);
        List<Sujet> sujetTemp = q.getResultList();
        em.close();
		return sujetTemp.get(0).getQuestionsSujet();
    	
    }
    
    
    
}
