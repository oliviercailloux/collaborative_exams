package mainPackage;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GestionQuestion 
{
	Question questionT;
	
	public void createQuestion(String nom, String langueI, String competenceI, String enonce, int identifiant, String niveau)
    {
    	this.questionT = new Question();
    	this.questionT.setAut(nom);
    	this.questionT.setLangue(langueI);
    	this.questionT.setCompetence(competenceI);
    	this.questionT.setEnonce(enonce);
    	this.questionT.setId(identifiant);
    	this.questionT.setNiveau(niveau);
    	this.questionT.nbVotePertinence = 0;
    	this.questionT.totalNotePertinence = 0;
    }
	public void setReponseG (Reponse e)
	{
		this.questionT.setReponse(e);
	}
	public Question getQuestion()
	{
		return this.questionT;
	}
	public Question createQuestionV(String nom, String langueI, String competenceI, String enonce, String idPere, int identifiant, String opinionI, String niveau)
    {
    	questionT = new Question(idPere);
    	questionT.setAut(nom);
    	questionT.setLangue(langueI);
    	questionT.setCompetence(competenceI);
    	questionT.setEnonce(enonce);
    	questionT.setVar(idPere);
    	questionT.setId(identifiant);
        questionT.setOpinion(opinionI);
        questionT.setListReponse(Question.retourneReponse(idPere, CreationEtAffichageQuestionForm.listeQ));
        questionT.setNiveau(niveau);
        questionT.nbVotePertinence = 0;
        questionT.totalNotePertinence = 0;
		return questionT;
    }
}
