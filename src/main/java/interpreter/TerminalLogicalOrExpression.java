package interpreter;

public class TerminalLogicalOrExpression extends AbstractLogicalExpression {
    public void interpret(Context context){
        context.pushValue(context.popValue() | context.popValue());
    }
}
