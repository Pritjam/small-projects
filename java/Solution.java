public class Solution {
    public static int solution(String s) {
        char[] chars = s.toCharArray();
        int rights = 0;
        int salutes = 0;
        for (char c : chars) {
            if(c == '-') {
                continue;
            } else if(c == '>') {
                rights++;
            } else if(c == '<') {
                salutes += rights;
            }
        }
        return salutes * 2;
    }
}