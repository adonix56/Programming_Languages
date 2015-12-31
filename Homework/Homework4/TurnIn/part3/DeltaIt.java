//Delta Iterator
public class DeltaIt implements SeqIt {
    //part 3
    private int dindex;
    private int dvalue;
    private Delta k;

    public DeltaIt(Delta s){
        k = s;
        //k.num = s.num;
        //k.initial = s.initial;
        //k.delta = s.delta;
        dindex = 0;
        dvalue = s.initial;
    }

    // any more elements?
    public boolean hasNext() {
        if(dindex >= k.num)
            return false;
        else
            return true;
    }
    // return the next element and advance iterator to following item.
    public int next() {
        if(hasNext()){
            if(dindex == 0) {
                ++dindex;
            }
            else {
                ++dindex;
                dvalue = dvalue + k.delta;
            }
        }
        else {
            System.err.println("DeltaIt called past end");
            System.exit(1);
        }
        return dvalue;
    }
}






/*

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
*/
