//Jumble User
public class JumbleUser {
    //part 3
    public static int lengthLongestNDCSS1(Jumble j) {
        int temp;
        int jvalue;
        int jindex;
        int longest;
        JumbleIt ji = new JumbleIt(j);
        if(!ji.hasNext())
            return 0;
        else {
            temp = ji.next();
            longest = 1;
            jindex = 1;
            while (ji.hasNext()) {
                jvalue = ji.next();
                if (jvalue >= temp)
                    jindex = jindex + 1;
                else
                    jindex = 1;
                if (jindex > longest)
                    longest = jindex;
                temp = jvalue;
            }                    
        }
        return longest;
    }
}
