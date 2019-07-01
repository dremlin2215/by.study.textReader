package parser;

import entity.ITextPart;
import entity.Leaf;
import entity.TextPart;
import entity.TextPartType;

public class WordParser {

    public ITextPart parse(String inputData) {
        ITextPart word = new TextPart(TextPartType.WORD);
        for (char ch : inputData.toCharArray()) {
            Leaf symbol = new Leaf(String.valueOf(ch), TextPartType.SYMBOL);
            word.addComponent(symbol);
        }
        return word;
    }
}
