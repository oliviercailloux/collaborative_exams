package mainPackage;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GestionReponse {
Reponse reponse;
	
	public Reponse createReponse(int idQ, String textRep, String pos)
    {
		reponse = new Reponse();
    	reponse.setIdQ(idQ);
    	reponse.setTextRep(textRep);
    	reponse.setTrueRep(pos);
		return reponse;
    }
	public Reponse getReponse()
	{
		return this.reponse;
	}

}
