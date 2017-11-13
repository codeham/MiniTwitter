public class GroupCounter extends CounterImplement implements Visitable{
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
