package parser;

import entity.ITextPart;
import entity.TextPart;
import entity.TextPartType;
import interpreter.Client;
import interpreter.StringToReversePolishNotationConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {
    private static final String LEXEME_PATTERN = "\\s+[(]*[a-zA-Z-]+[',:\\-.?!)]*";
    private static final String INTERPRETER_PATTERN = "[0-9(][0-9(>><<|&^)]+";
    private static final String COMBINED_PATTERN = LEXEME_PATTERN + '|' + INTERPRETER_PATTERN;
    private LexemeParser lexemeParser = new LexemeParser();

    public ITextPart parse(String inputData) {
        ITextPart sentence = new TextPart(TextPartType.SENTENCE);
        Matcher lexemeMatcher = Pattern.compile(COMBINED_PATTERN).matcher(inputData);
        while (lexemeMatcher.find()) {
            String lexeme = lexemeMatcher.group();
            if (isMathExpression(lexeme)) {
                lexeme = String.valueOf(calculateExpression(lexeme));
            }
            sentence.addComponent(lexemeParser.parse(lexeme));
        }

        return sentence;
    }

    private boolean isMathExpression(String sentencePart) {
        Matcher mathMatcher = Pattern.compile(INTERPRETER_PATTERN).matcher(sentencePart);
        return mathMatcher.find();
    }

    private int calculateExpression(String sentencePart) {

        String sequence = StringToReversePolishNotationConverter.getInstance()
                .convertStringToReversePolishNotation(sentencePart);
        Client interpreter = new Client(sequence);
        return interpreter.calculate().intValue();
    }
}