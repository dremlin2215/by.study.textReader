package interpreter;

public class TerminalLogicalAndExpression extends AbstractLogicalExpression {
    public void interpret(Context context){
        context.pushValue(context.popValue() & context.popValue());
    }
}
