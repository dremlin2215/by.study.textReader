package interpreter;

public class TerminalLogicalXOrExpression extends AbstractLogicalExpression {
    public void interpret(Context context) {
        context.pushValue(context.popValue() ^ context.popValue());
    }
}
