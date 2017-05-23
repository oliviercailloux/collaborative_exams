package mainPackage;

import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ApplicationScoped
public class SubjectManager 
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    EntityManager em; 
    Subject subjectT;
    public void createSubject(String name)
    {
    	this.subjectT = new Subject(name);
    }
    public Subject getSubject()
    {
    	return this.subjectT;
    }
    public void setQuestion (Question e)
	{
		this.subjectT.insertSubject(e);
	}
    public void openSubject()
	{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(this.subjectT);
	}
    public void addQuestion(String id)
	{
		StringTokenizer st = new StringTokenizer(id, "+"); 
		String array[] = new String[3];
		int i=0;
		while (st.hasMoreTokens()) {
			array[i] = st.nextToken();
			i=i+1;
	     }
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(array[0]));
        Question searchList = (Question) q.getResultList().get(0);
        em.persist(searchList);
        searchList.setSubject(this.getSubject());
        this.subjectT.insertSubject(searchList);
        //System.out.println("Size: " + searchList.get(0).enonceQ);
	}
    public void commitSubject()
	{   
		em.getTransaction().commit();
        em.close();
	}
    public List<String> getSubjectName()
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.subjectName FROM Subject u", Subject.class);
        List<String> listQuestionA;
        listQuestionA = q.getResultList();
       // Question test = userList;
        em.close();
		return listQuestionA;
    	
    }
    public List<Question> getSubjectQuestions(String nameSubject)
    {
    	 factory = Persistence.createEntityManagerFactory("questT");
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Subject u where u.subjectName like :arg1", Subject.class);
        q.setParameter("arg1", nameSubject);
        List<Subject> subjectTemp = q.getResultList();
        em.close();
		return subjectTemp.get(0).getSubjectQuestions();
    	
    }
    
    
    
}
