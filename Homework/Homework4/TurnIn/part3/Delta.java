//Delta
public class Delta extends Seq {
    //part 1
    protected int num;
    protected int initial;
    protected int delta;
    public Delta(int dnum, int dinitial, int ddelta) {
        num = dnum;
        if (dnum == 0) {
            initial = 0;
            delta = 0;
        }
        else {
            initial = dinitial;
            delta = ddelta;
        }
    }
    public String toString() {
        return "< " + Integer.toString(num) + " : " + Integer.toString(initial) + " &" + Integer.toString(delta) + " >";
    }
    //part 2
    public int min() {
        if (delta >= 0)
            return initial;
        else
            return (initial + ((num-1)*delta));
    }
}

