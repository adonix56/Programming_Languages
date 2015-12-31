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

    //part 7
    public static Seq plus(Constant c, Delta d) {
        int cdnum;
        int cdinitial;
        int cddelta;
        if (c.num <= d.num)
            cdnum = c.num;
        else
            cdnum = d.num;
        cdinitial = c.value + d.initial;
        cddelta = d.delta;
        Seq cd = new Delta(cdnum, cdinitial, cddelta);
        return cd;
    }
    public static Seq plus(Delta d, Constant c) {
        int dcnum;
        int dcinitial;
        int dcdelta;
        if (c.num <= d.num)
            dcnum = c.num;
        else
            dcnum = d.num;
        dcinitial = c.value + d.initial;
        dcdelta = d.delta;
        Seq dc = new Delta(dcnum, dcinitial, dcdelta);
        return dc;
    }
    public static Seq plus(Constant c, Jumble j) {
        int cjindex;
        if (c.num <= j.values.length)
            cjindex = c.num;
        else
            cjindex = j.values.length;

        int [] cjvalues = new int [cjindex];
        for(int i = 0; i < cjindex; i++)
            cjvalues[i] = c.value + j.values[i];
        Seq cj = new Jumble(cjvalues);
        return cj;
    }
    public static Seq plus(Jumble j, Constant c) {
        int jcindex;
        if (c.num <= j.values.length)
            jcindex = c.num;
        else
            jcindex = j.values.length;

        int [] jcvalues = new int [jcindex];
        for(int i = 0; i < jcindex; i++)
            jcvalues[i] = c.value + j.values[i];
        Seq jc = new Jumble(jcvalues);
        return jc;
    }
    public static Seq plus(Delta d, Jumble j) {
        int djindex;
        if (d.num <= j.values.length)
            djindex = d.num;
        else
            djindex = j.values.length;

        int [] djvalues = new int [djindex];
        for(int i = 0; i < djindex; i++)
            djvalues[i] = (d.initial + (d.delta * i)) + j.values[i];
        Seq dj = new Jumble(djvalues);
        return dj;
    }
    public static Seq plus(Jumble j, Delta d) {
        int jdindex;
        if (d.num <= j.values.length)
            jdindex = d.num;
        else
            jdindex = j.values.length;

        int [] jdvalues = new int [jdindex];
        for(int i = 0; i < jdindex; i++)
            jdvalues[i] = (d.initial + (d.delta * i)) + j.values[i];
        Seq jd = new Jumble(jdvalues);
        return jd;
    }
}
