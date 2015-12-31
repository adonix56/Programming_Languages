//Jumble
public class JumbleIt implements SeqIt {
    //part 3
    private int jindex;
    private int jvalue;
    private Jumble k;

    public JumbleIt(Jumble s){
        k = s;
        jindex = 0;
    }

    // any more elements?
    public boolean hasNext() {
        if(jindex >= k.values.length)
            return false;
        else
            return true;
    }
    // return the next element and advance iterator to following item.
    public int next() {
        if(hasNext()){
                jvalue = k.values[jindex];
                ++jindex;
        }
        else {
            System.err.println("JumbleIt called past end");
            System.exit(1);
        }
        return jvalue;
    }
}







/*
    //part 1
    protected int [] values;
    public Jumble(int [] jvalues) {
	int [] copyValues = new int[jvalues.length];
        System.arraycopy(jvalues, 0, copyValues, 0, jvalues.length);
        values = copyValues;
    }

    public String toString() {
        String strvalue = "";
        for (int x: values)
            strvalue = strvalue + x + " ";
        return "{ " + values.length + " : " + strvalue + "}";
    }

    //part 2
    public int min() {
        if (values.length == 0)
            return 0;
        else {
            int temp = values[0];
            for(int x: values) {
                if (x < temp)
                    temp = x;
            }
            return temp;
        }
    }
}*/
