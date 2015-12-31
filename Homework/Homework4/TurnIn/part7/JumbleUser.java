//Jumble User
public class JumbleUser {
    //part 3
    public static int lengthLongestNDCSS1(Jumble j) {
        int temp;
        int jvalue;
        int jindex = 1; // needed to initialize for part 4
        int longest = 1; // needed to initialize for part 4
        JumbleIt ji = new JumbleIt(j);
        if(!ji.hasNext())
            return 0;
        else {
            try{ // introduced exceptions for part 4
                temp = ji.next();
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
                return longest;
            }
            catch (UsingIteratorPastEndException e) {
                return longest;
            }                    
        }
    }

    //part 4
    public static int lengthLongestNDCSS2(Jumble j){
        int temp;
        int jvalue;
        int jindex = 1;
        int longest = 1;
        JumbleIt ji = new JumbleIt(j);
    
        try{
            temp = ji.next();
        }
        catch (UsingIteratorPastEndException e) {
            return 0; //if empty Jumble, return 0
        }
        try{
            while(true) {
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
        catch (UsingIteratorPastEndException e) {
            return longest;
        }
    }
}
