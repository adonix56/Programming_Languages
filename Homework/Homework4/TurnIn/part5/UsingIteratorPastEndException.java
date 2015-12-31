// Using Iterator Past End Exception

class UsingIteratorPastEndException extends Exception {
    //part 4
    static final long serialVersionUID = 98L;  //warning prevention
    public UsingIteratorPastEndException(String msg) {
        super(msg);
    }
}
