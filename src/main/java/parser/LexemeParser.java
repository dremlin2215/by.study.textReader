package parser;

import entity.ITextPart;
import entity.Leaf;
import entity.TextPart;
import entity.TextPartType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser {

    private static final String LEXEME_PUNCTUATION_PATTERN = "\\p{Punct}";
    private static final String WORD_PATTERN = "\\p{Alnum}+";
    private static final String COMBINED_PATTERN = LEXEME_PUNCTUATION_PATTERN + '|' + WORD_PATTERN;

    private WordParser wordParser = new WordParser();

    public ITextPart parse(String inputData) {
        ITextPart lexeme = new TextPart(TextPartType.LEXEME);
        Matcher matcher = Pattern.compile(COMBINED_PATTERN).matcher(inputData);

        while (matcher.find()) {
            String temp = matcher.group();
            if (isWordExpression(temp)) {
                lexeme.addComponent(wordParser.parse(temp));
            } else {
                Leaf punctuation = new Leaf(temp, TextPartType.PUNCTUATION);
                lexeme.addComponent(punctuation);
            }
        }
        return lexeme;
    }


    private boolean isWordExpression(String lexemePart) {
        Matcher matcher = Pattern.compile(WORD_PATTERN).matcher(lexemePart);
        return matcher.find();
    }
}
