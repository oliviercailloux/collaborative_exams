package collaborative_exams;

public class Donnees 
{
	private static String[] competences = { "Mathematique", "Langage C", "UML" };
    private static String[] langue = { "Français", "Anglais", "Espagnol" };
    private static String[] typeQ = { "Avec propositions", "Réponse libre"};
    private static String[] niveau = { "Simple", "Moyen", "Complexe" };
    private static Integer[] coef = { 1, 2, 3, 4};
    
    public static String[] trouveLangueP() 
    {
        return langue;
    } 

    public static String[] trouveCompetenceP() 
    {
        return competences;
    }
    
    public static String[] trouveTypeQ(){
    	return typeQ;
    	
    }
    public static String[] trouveNiveau(){
    	return niveau;
    	
    }
    public static Integer[] trouveCoef(){
    	return coef;
    	
    }
}