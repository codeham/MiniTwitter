public abstract class CounterImplement {
    private int count;

    CounterImplement(){
        this.count = 0;
    }

    public int getCounter(){
        return count;
    }

    public void increment(){
        count++;
    }
}
