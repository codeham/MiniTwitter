public interface Visitor {
    void visit(GroupCounter groupCount);
    void visit(MessageCounter messageCount);
    void visit(PositiveCounter positiveCount);
    void visit(UserCounter userCount);
}
