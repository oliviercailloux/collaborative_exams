package collaborative_exams;

public class Data 
{
	private static String[] skills = { "Mathematique", "Langage C", "UML", "JAVA", "Management", "Marketing" };
    private static String[] languages = { "Francais", "Anglais", "Espagnol", "Arabe" };
    private static String[] typeQ = { "Avec propositions", "Réponse libre"};
    private static String[] level = { "Simple", "Moyen", "Complexe" };
    private static Integer[] coef = { 1, 2, 3, 4};
    
    public static String[] findLanguage() 
    {
        return languages;
    } 

    public static String[] findSkills() 
    {
        return skills;
    }
    
    public static String[] findTypeQ(){
    	return typeQ;
    	
    }
    public static String[] findLevel(){
    	return level;
    	
    }
    public static Integer[] findCoef(){
    	return coef;
    	
    }
}