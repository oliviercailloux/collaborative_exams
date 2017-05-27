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
public class SubjectManager 
{
	private static final String PERSISTENCE_UNIT_NAME = "questT";
    private static EntityManagerFactory factory;
    @PersistenceContext(unitName = "questT")
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
        em.persist(this.subjectT);
	}
    public void addQuestion(String id)
	{
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Question u where u.idTechvisible =:arg1", Question.class);
        q.setParameter("arg1", Integer.parseInt(id));
        Question searchList = (Question) q.getResultList().get(0);
        searchList.setSubject(this.getSubject());
        this.subjectT.insertSubject(searchList);
        em.merge(searchList);
	}
    public void commitSubject()
	{   
        em.detach(this.subjectT);
		em.merge(this.subjectT);
	}
    public List<String> getNameSubjects()
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u.nameSubject FROM Subject u", Subject.class);
        List<String> listQuestionA;
        listQuestionA = q.getResultList();
		return listQuestionA;
    }
    public List<Question> getQuestionsSubject(String nameSubject)
    {
        // Read the existing entries and write to console
        Query q = em.createQuery("SELECT u FROM Subject u where u.nameSubject like :arg1", Subject.class);
        q.setParameter("arg1", nameSubject);
        List<Subject> subjectTemp = q.getResultList();
		return subjectTemp.get(0).getQuestionsSubject();
    }
    
    public Subject getSubjectByName(String name){
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        // Read the existing entries and write to console
        Query query = em.createQuery("SELECT u FROM Subject u where u.nameSubject like :arg1", Subject.class);
        query.setParameter("arg1", name);
        Subject subject = (Subject) query.getResultList().get(0);
        em.close();
        return subject; 
    }
    
}
