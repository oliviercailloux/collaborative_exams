package mainPackage;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="App.Reponses")
public class Answer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
	int idQuestion;
	String author;
    String textAnswer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    Question questionLink;
    int trueRep;
    
    public Answer()
    {
    	this.textAnswer = ""; 
    	this.trueRep =2;
    	this.idQuestion = 0;
    	questionLink = new Question();
    	
    } 
    
    public String getText()
    {
      return this.textAnswer;
    }
    public String getPos()
    {
      return (this.trueRep==1)?"Vrai":"Faux";
    }
    public void setIdQ(int id)
    {
    	this.idQuestion=id;
    }
    public void setQuestion(Question q)
    {
    	this.questionLink = q;
    	this.idQuestion=q.getId();
    }
    public void setauthorQ(String author)
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
    		this.trueRep=1;
    	}
    	else
    	{
    		this.trueRep=0;
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