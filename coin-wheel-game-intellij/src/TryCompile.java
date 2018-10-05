public class TryCompile {

    private int myInt;

    public TryCompile(final int n) {
        this.myInt = n;
    }

    public int getInt(){
        return this.myInt;
    }

    public String getString(){
        return Integer.toString(this.myInt);
    }
}
