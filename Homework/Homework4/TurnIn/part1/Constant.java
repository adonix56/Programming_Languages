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
}
