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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="App.Questionnaries")
public class Questionnary 
{	
	//@Inject
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idQuestionnary;
	@ManyToMany(mappedBy = "questionnaryLink", cascade = CascadeType.PERSIST)
	List <Question> listQuestionQuestionnary;
	String nameQuestionnary;
	
	 public Questionnary()
	 {
        this.nameQuestionnary= "";
        this.listQuestionQuestionnary= new ArrayList <>();
	 } 
	 public Questionnary(String nQuestionnary)
	 {
        this.nameQuestionnary= nQuestionnary;
        this.listQuestionQuestionnary= new ArrayList <>();
	 } 
	 
	 public List<Question> getQuestionsQuestionnary()
	 {
		 return this.listQuestionQuestionnary;
	 }
	 public void insertQuestionnary(Question questionQuestionnary)
	 {
		 this.listQuestionQuestionnary.add(questionQuestionnary);
	 }
	 
	 public String getNomQuestionnary()
	 {
		 return this.nameQuestionnary;
	 }
	
}
