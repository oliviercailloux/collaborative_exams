package mainPackage;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="App.Reponses")

public class Reponse {
	
	@Id
	@PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name="idQuestion", table="App.Reponses")
	int idQuestion;
	@Column(name="auteur", table="App.Reponses")
	String auteur;
	@Column(name="textReponse", table="App.Reponses")
    String textReponse;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    Question questionLien;
    int trueRep;
    
      
    
    public Reponse()
    {
    	this.textReponse = ""; 
    	this.trueRep =2;
    	questionLien = new Question();
    	
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
    

}