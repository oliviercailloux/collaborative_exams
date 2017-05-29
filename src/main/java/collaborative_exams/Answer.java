package collaborative_exams;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="App.Answers")
public class Answer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
	int idQuestion;
	String author;
    String textAnswer;
    @ManyToMany
    @JoinColumn(name="QUESTION_ID")
    List<Question> questionLink;
    int true_false;
    
      
    
    public Answer()
    {
    	this.textAnswer = ""; 
    	this.true_false =2;
    	this.idQuestion = 0;
    	questionLink = new ArrayList<>();
    	
    } 
    
    public String getText()
    {
      return this.textAnswer;
    }
    public String getPos()
    {
      return (this.true_false==1)?"Vrai":"Faux";
    }
    public void setIdQ(int id)
    {
    	this.idQuestion=id;
    }
    
    
    
    public void setQuestion(Question q)
    {
    	this.questionLink.add(q);
    	this.idQuestion=q.getId();
    }
    
    
    public void setAuteurQ(String author)
    {
    	this.author=author;
    } 
    
    public void setTextAnswer(String textR)
    {
    	this.textAnswer=textR;
    }
    
    
    public void setTrueFalse(String position)
    {
    	if(position.equalsIgnoreCase("V"))
    	{
    		this.true_false=1;
    	}
    	else
    	{
    		this.true_false=0;
    	}
    }
    
    
    public int getIdQuestion()
    {
      return this.idQuestion;
    }
    
    
    public String getAut()
    {
      return this.author;
    }
    
    
    public int getid()
    {
      return this.id;
    }
    

}