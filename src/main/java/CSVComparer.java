import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVComparer {

    private String expectedCsv;
    private String actualCsv;
    private String logFile;

    public CSVComparer(String expectedCsv, String actualCsv, String logFile) {
        this.expectedCsv = expectedCsv;
        this.actualCsv = actualCsv;
        this.logFile = logFile;
    }

    public boolean compareCSV() {
        WorkWithFiles.writeLineIntoFile('\n' +"==============================================", logFile);
        List<String> exp = WorkWithFiles.readLines(expectedCsv);
        List<String> act = WorkWithFiles.readLines(actualCsv);
        int unequalRows  = 0;
        boolean isEquals;

        for (int i = 0; i < exp.size(); i++) {
            if (act.size() - 1 < i) {
                break;
            } else {
                String result = diff(exp.get(i), act.get(i)).second;
                if (!result.isEmpty()) {
                    unequalRows++;
                    WorkWithFiles.writeLineIntoFile("id : "+getId(exp.get(i))+ " . Difference in text : "+result + " . Expect : " +exp.get(i)+" . Actual : " + act.get(i), logFile);
                }
            }
        }
        int expSize = exp.size();
        exp.removeAll(act);
        if(exp.size() == 0) {
            WorkWithFiles.writeLineIntoFile("All rows are equal", logFile);
            isEquals = true;
        }else {
            WorkWithFiles.writeLineIntoFile("Not all rows are equal", logFile);
            isEquals = false;
        }

        WorkWithFiles.writeLineIntoFile("Total rows in expected csv : " + expSize, logFile);
        WorkWithFiles.writeLineIntoFile("Total rows in actual csv : " + act.size(), logFile);
        WorkWithFiles.writeLineIntoFile("Total unequal rows : " + unequalRows, logFile);
        WorkWithFiles.writeLineIntoFile('\n' +"==============================================", logFile);
        return isEquals;
    }

    private String getId(String row) {
        StringBuffer total = new StringBuffer();
        String[] n = row.split(""); //array of strings
        for (int i = 0; i < n.length; i++) {
            if ((n[i].matches("[0-9]+"))) {// validating numbers
                total.append(n[i]); //appending
            } else return total.toString();
        }
        return total.toString();
    }

    public static Pair<String> diff(String a, String b) {
        return diffHelper(a, b, new HashMap<Long, Pair<String>>());
    }


    private static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        long key = ((long) a.length()) << 32 | b.length();
        if (!lookup.containsKey(key)) {
            Pair<String> value;
            if (a.isEmpty() || b.isEmpty()) {
                value = new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                value = diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    value = new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    value = new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
            lookup.put(key, value);
        }
        return lookup.get(key);
    }

    public static class Pair<T> {
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public final T first, second;

        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
