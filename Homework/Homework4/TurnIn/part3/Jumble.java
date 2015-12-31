//Jumble
public class Jumble extends Seq {
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
}
