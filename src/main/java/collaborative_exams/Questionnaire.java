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
@Table(name="App.Questionnaires")
public class Questionnaire 
{	
	//@Inject
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int idQuestionnaire;
	@ManyToMany(mappedBy = "questionnaireLien", cascade = CascadeType.PERSIST)
	List <Question> listeQuestionQuestionnaire;


	 String nomQuestionnaire;
	 public Questionnaire()
	 {
        this.nomQuestionnaire= "";
        this.listeQuestionQuestionnaire= new ArrayList <>();
	 } 
	 public Questionnaire(String nQuestionnaire)
	 {
        this.nomQuestionnaire= nQuestionnaire;
        this.listeQuestionQuestionnaire= new ArrayList <>();
	 } 
	 
	 public List<Question> getQuestionsQuestionnaire()
	 {
		 return this.listeQuestionQuestionnaire;
	 }
	 public void insertQuestionnaire(Question questionQuestionnaire)
	 {
		 this.listeQuestionQuestionnaire.add(questionQuestionnaire);
	 }
	 
	 public String getNomQuestionnaire()
	 {
		 return this.nomQuestionnaire;
	 }
	 
	/* public static Questionnaire getQuestionnaire(String nom, List <Questionnaire> QuestionnaireQ) 
	    {
	        Questionnaire QuestionnaireT = new Questionnaire();
	        if(nom.isEmpty())
	        {
	        	QuestionnaireT.listeQuestionQuestionnaire=CreationEtAffichageQuestionForm.listeQ;
	        	return QuestionnaireT;
	        	
	        }
	        for (Questionnaire questionnaireTemp : questionnaireQ) 
	        {
	            if (questionnaireTemp.getNomQuestionnaire().equalsIgnoreCase(nom)) 
	            {
	               QuestionnaireT =QuestionnaireTemp;
	            }
	        }
	        return QuestionnaireT;
	    }*/
}
