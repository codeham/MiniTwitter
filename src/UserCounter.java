public class UserCounter extends CounterImplement implements Visitable{
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
