package QuestionV1;

public class Donnees 
{
	private static String[] competences = { "Mathematique", "Langage C", "UML" };
    private static String[] langue = { "Français", "Anglais", "Espagnol" };
    private static String[] typeQ = { "Avec propositions", "Réponse libre"};
    private static Integer[] coefficient = { 1, 2, 3, 4};
    
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
    
    public static Integer[] trouveCoefficient(){
    	return coefficient;
    	
    }
}