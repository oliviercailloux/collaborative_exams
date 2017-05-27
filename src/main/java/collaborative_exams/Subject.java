package collaborative_exams;
import java.util.ArrayList;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="App.Subjects")
public class Subject 
{	
	//@Inject
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idSubject;
	@OneToMany(mappedBy = "subjectLink", cascade = CascadeType.PERSIST)
	 List <Question> listQuestionSubject;
	 String nameSubject;
	 
	 public Subject()
	 {
        this.nameSubject= "";
        this.listQuestionSubject= new ArrayList <>();
	 } 
	 public Subject(String nSubject)
	 {
        this.nameSubject= nSubject;
        this.listQuestionSubject= new ArrayList <>();
	 } 
	 
	 public List<Question> getQuestionsSubject()
	 {
		 return this.listQuestionSubject;
	 }
	 public void insertSubject(Question questionSubject)
	 {
		 this.listQuestionSubject.add(questionSubject);
	 }
	 
	 public String getNameSubject()
	 {
		 return this.nameSubject;
	 }
	 
	 public static Subject getSubject(String name, List <Subject> subjectQ) 
	    {
	        Subject subjectT = new Subject();
	        if(name.isEmpty())
	        {
	        	subjectT.listQuestionSubject=CreateDisplayQuestionForm.listQ;
	        	return subjectT;
	        	
	        }
	        for (Subject subjectTemp : subjectQ) 
	        {
	            if (subjectTemp.getNameSubject().equalsIgnoreCase(name)) 
	            {
	               subjectT =subjectTemp;
	            }
	        }
	        return subjectT;
	    }
}
