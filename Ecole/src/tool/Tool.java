package tool;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {
    public static void show(ArrayList<String[]> liste) {
        for (int i = 0; i < liste.size(); i++) {
            for (int j = 0; j < liste.get(i).length; j++) {
                System.out.print(liste.get(i)[j]);
            }
            System.out.println();
        }
    }

    public static boolean match(String string, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
