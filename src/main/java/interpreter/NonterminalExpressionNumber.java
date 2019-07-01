package interpreter;

public class NonterminalExpressionNumber extends AbstractLogicalExpression {
    private int number;

    public NonterminalExpressionNumber(int number) {
        this.number = number;
    }

    public void interpret(Context context) {
        context.pushValue(number);
    }

}
