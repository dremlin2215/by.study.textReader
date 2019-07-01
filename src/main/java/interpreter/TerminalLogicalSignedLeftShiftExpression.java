package interpreter;

public class TerminalLogicalSignedLeftShiftExpression extends AbstractLogicalExpression {
    public void interpret(Context context) {
        int first = context.popValue();
        int second = context.popValue();
        context.pushValue(second << first);
    }
}
