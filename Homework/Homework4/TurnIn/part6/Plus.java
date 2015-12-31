//Polymorphic Overloaded Plus
public class Plus {
    //part 6
    public static Seq plus(Constant c1, Constant c2) {
        int ccnum;
        int ccvalue;
        if (c1.num <= c2.num)
            ccnum = c1.num;
        else
            ccnum = c2.num;
        ccvalue = c1.value + c2.value;
        Seq cc = new Constant(ccnum, ccvalue);
        return cc;
    }
    public static Seq plus(Delta d1, Delta d2) {
        int ddnum;
        int ddinitial;
        int dddelta;
        if (d1.num <= d2.num)
            ddnum = d1.num;
        else
            ddnum = d2.num;
        ddinitial = d1.initial + d2.initial;
        dddelta = d1.delta + d2.delta;
        Seq dd = new Delta(ddnum, ddinitial, dddelta);
        return dd;
    }
    public static Seq plus(Jumble j1, Jumble j2) {
        int jjindex;
        if (j1.values.length <= j2.values.length)
            jjindex = j1.values.length;
        else
            jjindex = j2.values.length;

        int [] jjvalues = new int [jjindex];
        for(int i = 0; i < jjindex; i++)
            jjvalues[i] = j1.values[i] + j2.values[i];
        Seq jj = new Jumble(jjvalues);
        return jj;
    }
}
