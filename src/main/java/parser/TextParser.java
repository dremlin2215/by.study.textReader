package parser;

import entity.ITextPart;
import entity.TextPart;
import entity.TextPartType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    //Chain can be made via singleton
    private static final String PARAGRAPH_PATTERN = "(\\t|(  )+)([A-Z].*\\n|$)";
    private ParagraphParser paragraphParser = new ParagraphParser();
    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    public ITextPart parse(String inputData){
        ITextPart text = new TextPart(TextPartType.TEXT);
        Matcher paragraphMatcher = Pattern.compile(PARAGRAPH_PATTERN).matcher(inputData);
        while (paragraphMatcher.find()){
            String paragraph = paragraphMatcher.group();
            if (!paragraph.isEmpty()) {
                text.addComponent(paragraphParser.parse(paragraph));
            }
        }
        LOGGER.info("Method TextParser used \n");
        return text;
    }
}
