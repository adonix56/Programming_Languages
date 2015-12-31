//Constant
public class Constant extends Seq {
    //part 1
    protected int num;
    protected int value;
    public Constant(int cnum, int cvalue) {
        num = cnum;
        if (cnum == 0)
            value = 0;
        else
            value = cvalue;
    }
    public String toString() {
        return "[ " + Integer.toString(num) + " : " + Integer.toString(value) + " ]";
    }

    //part 2
    public int min() {
        return value;
    }

    //part 5
    public SeqIt createSeqIt() {
        ConstantIt c = new ConstantIt(this);
        return c;
    }
}
