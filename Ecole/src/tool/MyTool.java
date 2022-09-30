package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connection.Bdd;
public class MyTool {
    public static String user;
    public static String password;
    public static String separateur;
    public static String filesep = "#";
    public static String root = "";
    public static String uploadpath = "";
    public static String workspace = "";

    public static String getPath(String path) {
        return MyTool.root + MyTool.workspace + path;
    }

    public static void workspace() {
        MyTool.workspace = "lib/";
    }

    public static int countSep(String str, String sep) {
        return str.split(sep).length - 1;
    }

    public static String repeat(String target, int times) {
        String answer = "";
        for (int i = 0; i < times; i++) {
            answer += target;
        }
        return answer;
    }

    public static String getData(ArrayList<String[]> liste, String indice) throws Exception {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i)[0].equals(indice)) {
                return liste.get(i)[1];
            }
        }
        throw new Exception("Data Not Found");
    }

    public static String generate(ArrayList<String[]> liste, String form) throws Exception {
        int ind = 0;
        String replace = "";
        String ans = "";
        char[] forms = form.toCharArray();
        for (int i = 0; i < forms.length; i++) {
            if (forms[i] == '%') {
                if (ind == 0) {
                    ind++;
                } else {
                    ans += MyTool.getData(liste, replace);
                    replace = "";
                    ind = 0;
                }
            } else {
                if (ind == 0) {
                    ans += String.copyValueOf(forms, i, 1);
                } else {
                    replace += String.copyValueOf(forms, i, 1);
                }
            }
        }
        return ans;
    }

    public static void change(int[] a) {
        a[0] = 5;
    }

    public static String[] include(String[] a1, String[] a2, int ind) {
        String[] str = new String[a1.length + a2.length - 1];
        int indice = 0;
        for (int i = 0; i < ind; i++) {
            str[indice] = a1[i];
            indice++;
        }
        for (int i = 0; i < a2.length; i++) {
            str[indice] = a2[i];
            indice++;
        }
        for (int i = ind + 1; i < a1.length; i++) {
            str[indice] = a1[i];
            indice++;
        }
        return str;
    }

  

    public static String getLock(String str) {
        int len = 3;
        String[] liste = new String[len];
        liste[0] = "(";
        liste[1] = "{";
        liste[2] = "[";
        String[] listep = new String[len];
        listep[0] = ")";
        listep[1] = "}";
        listep[2] = "]";
        for (int i = 0; i < liste.length; i++) {
            if (str.equals(liste[i])) {
                return listep[i];
            }
        }
        return "";
    }

    public static String ignore(char[] chr, int ind, String lock, int[] indice) {
        String answer = "";
        String lockp = MyTool.getLock(lock);
        String t = "";
        int prof = 1;
        for (int i = ind; i < chr.length; i++) {
            t = String.copyValueOf(chr, i, 1);
            if (t.compareTo(lock) == 0) {
                prof++;
            }
            if (t.compareTo(lockp) == 0) {
                prof--;
            }
            indice[0] = i;
            if (prof == 0) {
                break;
            }
            answer += t;
        }
        return answer;
    }

    public static boolean iscontour(String str) {
        String reg = "[\\(\\[\\{\'\"]";
        return MyTool.match(str, reg);
    }

    public static ArrayList<String> identify(String string) {
        ArrayList<String> answer = new ArrayList<>();
        String[] grp = new String[] {
                "[a-zA-Z0-9]",
                "[\\(\\)\\[\\]]",
                "[\\ \\,\\:\\;]",
                "[\\+\\-\\*/]"
        };
        int ind = 0;
        int first = 0;
        String temp = "";
        String t = "";
        String t1 = "";
        char[] chr = string.toCharArray();
        int[] indice = new int[1];
        for (int i = 0; i < chr.length; i++) {
            t = String.copyValueOf(chr, i, 1);
            if (MyTool.iscontour(t)) {
                answer.add(temp);
                answer.add(t);
                t1 = MyTool.ignore(chr, i + 1, t, indice);
                answer.add(t1);
                i = indice[0] - 1;
                temp = "";
                continue;
            }
            if (MyTool.match(t, grp[ind])) {
                temp += t;
            } else {
                if (first != 0 || !temp.equals("")) {
                    answer.add(temp);
                    temp = "";
                } else {
                    first = 1;
                }
                for (int j = 0; j < grp.length; j++) {
                    if (MyTool.match(t, grp[j])) {
                        ind = j;
                        break;
                    }
                }
                temp += t;
            }

            if (i == chr.length - 1) {
                answer.add(temp);
            }
        }
        return answer;
    }

    public static ArrayList<String> without(ArrayList<String> liste) {
        ArrayList<String> answer = new ArrayList<>();
        String regex = "[\\ \\(\\)\\[\\]:\\,]";
        for (int i = 0; i < liste.size(); i++) {
            if (MyTool.match(liste.get(i), regex)) {
                continue;
            }
            answer.add(liste.get(i));
        }
        return answer;
    }

    public static ArrayList<String[]> identify(String first, String second) {
        ArrayList<String[]> answer = new ArrayList<>();
        ArrayList<String> list1 = MyTool.identify(first);
        ArrayList<String> list2 = MyTool.identify(second);
        String plus = first;
        String reg = "[\\ \\(\\)\\[\\]:\\,]";
        while (list1.size() < list2.size()) {
            plus += "," + first;
            list1 = MyTool.identify(plus);
        }
        for (int i = 0; i < list1.size(); i++) {
            if (MyTool.match(list1.get(i), reg) && MyTool.match(list2.get(i), reg)) {
                continue;
            }
            answer.add(new String[] { list1.get(i), list2.get(i) });
        }
        return answer;
    }

    public static String withoutSp(String str) {
        int ind = 0;
        String ans = "";
        String[] splt = str.split("\\ ");
        for (int i = 0; i < splt.length; i++) {
            ans += ((ind != 0) ? " " : "") + splt[i];
            ind = ((splt[i].equals("")) ? 0 : 1);
        }
        return ans;
    }

    public static boolean match(String string, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static String[] toStrings(ArrayList<String> str) {
        String[] answer = new String[str.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = str.get(i);
        }
        return answer;
    }

    public static ArrayList<String[]> getAll(String sql, int len) throws ClassNotFoundException, SQLException {
        ArrayList<String[]> answer = new ArrayList<>();
        Connection connection = Bdd.getConnection(MyTool.user, MyTool.password);
        Statement stat = connection.createStatement();
        ResultSet res = stat.executeQuery(sql);
        String[] liste = null;
        int i = 0;
        while (res.next()) {
            liste = new String[len];
            for (i = 0; i < len; i++) {
                liste[i] = res.getString(i + 1);
            }
            answer.add(liste);
        }
        res.close();
        stat.close();
        connection.close();
        return answer;
    }

    public static ArrayList<String> accessFic(String path) throws IOException {
        ArrayList<String> answer = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = read.readLine();
            while (line != null) {
                answer.add(line);
                line = read.readLine();
            }
            read.close();
            return answer;
        }
        throw new IOException("File not Found" + path);
    }

    public static String[][] split(String[] liste, String splt) {
        String[][] answer = new String[liste.length][];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = liste[i].split(splt);
        }
        return answer;
    }
}