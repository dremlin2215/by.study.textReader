package interpreter;

import exception.NonImplementedOperatorException;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static String IS_OPERATOR_EXPRESSION = "[\\^|\\&|\\|]|<<|>>";
    private List<AbstractLogicalExpression> listExpression;

    public Client(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression) { // синтаксический анализ
        for (String part : expression.split("\\s+")) {
            if (isNumber(part)) {
                listExpression.add(
                        new NonterminalExpressionNumber((Integer.parseInt(part))));
            } else if (isOperator(part)) {
                switch (part) {
                    case "&":
                        listExpression.add(new TerminalLogicalAndExpression());
                        break;
                    case "|":
                        listExpression.add(new TerminalLogicalOrExpression());
                        break;
                    case "^":
                        listExpression.add(new TerminalLogicalXOrExpression());
                        break;
                    case "<<":
                        listExpression.add(new TerminalLogicalSignedLeftShiftExpression());
                        break;
                    case ">>":
                        listExpression.add(new TerminalLogicalSignedRightShiftExpression());
                        break;
                    default:
                        throw new NonImplementedOperatorException();
                }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for (AbstractLogicalExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    public static boolean isOperator(String checkedString) {
        return IS_OPERATOR_EXPRESSION.contains(checkedString);
    }

    public static boolean isNumber(String checkedString) {
        try {
            Integer.parseInt(checkedString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}