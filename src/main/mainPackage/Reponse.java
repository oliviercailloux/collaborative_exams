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
public class Reponse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
	int idQuestion;
	String auteur;
    String textReponse;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    Question questionLien;
    int trueRep;
    
      
    
    public Reponse()
    {
    	this.textReponse = ""; 
    	this.trueRep =2;
    	this.idQuestion = 0;
    	questionLien = new Question();
    	
    } 
    public int getTrueRep()
    {
    	return this.trueRep;
    }
    
    public String getText()
    {
      return this.textReponse;
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
    	this.questionLien = q;
    	this.idQuestion=q.getId();
    }
    public void setAuteurQ(String auteur)
    {
    	this.auteur=auteur;
    } 
    
    public void setTextRep(String textR)
    {
    	this.textReponse=textR;
    }
    public void setTrueRep(String position)
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
      return this.auteur;
    }
    public int getid()
    {
      return this.id;
    }
    

}