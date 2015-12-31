//Constant Iterator
public class ConstantIt implements SeqIt {
    //part 3
    private int cindex;
    private Constant k;
    public ConstantIt(Constant s){
        k = s;
        cindex = 0;
    }

    // any more elements?
    public boolean hasNext() {
        if(cindex >= k.num)
            return false;
        else
            return true;
    }
    // return the next element and advance iterator to following item.
    public int next() {
        if(hasNext()) {
            ++cindex;
        }
        else {
            System.err.println("ConstantIt called past end");
            System.exit(1);
        }

            return k.value;
    }
}





/*
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
}*/
