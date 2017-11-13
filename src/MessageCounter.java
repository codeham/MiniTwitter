public class MessageCounter extends CounterImplement implements Visitable {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
