public class PositiveCounter extends CounterImplement implements Visitable{
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
