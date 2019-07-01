package interpreter;


import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToReversePolishNotationConverter {

    public static String PART_EXPRESSION = "[&|^()]|(<<)|(>>)";
    public static String BLANK = " ";

    private static StringToReversePolishNotationConverter ourInstance = new StringToReversePolishNotationConverter();

    private StringToReversePolishNotationConverter() {
    }

    public static StringToReversePolishNotationConverter getInstance(){
        return ourInstance;
    }

    private String convertStringArrayToReversePolishNotation(String[] infixNotation) {
        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("<<", 10);
        precedence.put(">>", 10);
        precedence.put("&", 7);
        precedence.put("^", 6);
        precedence.put("|", 5);
        precedence.put("(", 0);

        Queue<String> reversePolishNotation = new LinkedList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : infixNotation) {
            if ("(".equals(token)) {
                operatorStack.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(operatorStack.peek())) {
                    reversePolishNotation.add(operatorStack.pop());
                }
                operatorStack.pop();
                continue;
            }
            // an operator
            if (precedence.containsKey(token)) {
                while (!operatorStack.empty() && precedence.get(token) <= precedence.get(operatorStack.peek())) {
                    reversePolishNotation.add(operatorStack.pop());
                }
                operatorStack.push(token);
                continue;
            }

            if (Client.isNumber(token)) {
                reversePolishNotation.add(token);
                continue;
            }

            throw new IllegalArgumentException("Invalid input");
        }
        // at the end, pop all the elements from operatorStack to reversePolishNotation
        while (!operatorStack.isEmpty()) {
            reversePolishNotation.add(operatorStack.pop());
        }

        return String.join(BLANK,reversePolishNotation);
    }


    private String addSpaces(String expression){
        String newPart;
        Matcher expMatcher = Pattern.compile(PART_EXPRESSION).matcher(expression);
        while (expMatcher.find()){
            newPart = expMatcher.group();
            expression = expression.replace(newPart, BLANK + newPart + BLANK);
        }
        return expression.replaceAll("( +)", BLANK).trim(); //used to clear extra spaces
    }

    public String convertStringToReversePolishNotation(String expression){
        expression = addSpaces(expression); //preparation of expression for conversion
        return convertStringArrayToReversePolishNotation(expression.split(BLANK));
    }
}
