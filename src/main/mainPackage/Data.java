package mainPackage;

public class Data 
{
	private static String[] skill = { "Mathematique", "Langage C", "UML" };
    private static String[] language = { "Français", "Anglais", "Espagnol" };
    private static String[] typeQ = { "Avec propositions", "Réponse libre"};
    private static String[] level = { "Simple", "Moyen", "Complexe" };
    private static Integer[] coef = { 1, 2, 3, 4};
    
    public static String[] findLanguage() 
    {
        return language;
    } 

    public static String[] findSkill() 
    {
        return skill;
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