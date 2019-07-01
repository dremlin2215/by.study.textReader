package parser;

import entity.ITextPart;
import entity.TextPart;
import entity.TextPartType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser {
    private static final String SENTENCE_PATTERN = "(\\ |\\t)([^.!?]+([.!?]|[.]{3}))";
    private SentenceParser sentenceParser = new SentenceParser();

    public ITextPart parse(String inputData) {
        ITextPart paragraph = new TextPart(TextPartType.PARAGRAPH);
        Matcher sentenceMatcher = Pattern.compile(SENTENCE_PATTERN).matcher(inputData);
        while (sentenceMatcher.find()) {
            String sentence = sentenceMatcher.group();
            if (!sentence.isEmpty()) {
                paragraph.addComponent(sentenceParser.parse(sentence));
            }
        }

        return paragraph;
    }
}
