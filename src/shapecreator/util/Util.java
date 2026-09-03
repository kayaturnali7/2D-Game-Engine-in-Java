package shapecreator.util;

public class Util {

    public static String replaceBrackets(String list){
        String newString = list.replace("[", "{");
        newString = newString.replace("]", "}");
        return newString;
    }


}
