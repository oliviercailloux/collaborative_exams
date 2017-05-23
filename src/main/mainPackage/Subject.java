package mainPackage;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@ApplicationScoped
@Entity
@Table(name="App.Sujets")
public class Subject 
{	
	//@Inject
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idSubject;
	@OneToMany(mappedBy = "subjectLink", cascade = CascadeType.PERSIST)
	 List <Question> listQuestionSubject;


	 String subjectName;
	 public Subject()
	 {
        this.subjectName= "";
        this.listQuestionSubject= new ArrayList <>();
	 } 
	 public Subject(String nSubject)
	 {
        this.subjectName= nSubject;
        this.listQuestionSubject= new ArrayList <>();
	 } 
	 
	 public List<Question> getSubjectQuestions()
	 {
		 return this.listQuestionSubject;
	 }
	 public void insertSubject(Question questionSubject)
	 {
		 this.listQuestionSubject.add(questionSubject);
	 }
	 
	 public String getSubjectName()
	 {
		 return this.subjectName;
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
	            if (subjectTemp.getSubjectName().equalsIgnoreCase(name)) 
	            {
	               subjectT =subjectTemp;
	            }
	        }
	        return subjectT;
	    }
}
