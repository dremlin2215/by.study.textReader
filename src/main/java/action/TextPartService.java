package action;

import entity.ITextPart;
import entity.TextPartType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * All methods of this class accept only @ITextPart component  with only @TextPartType = TEXT parameter.
 */

public class TextPartService {
    private static final Logger LOGGER = LogManager.getLogger(TextPartService.class);

    public void sortParagraphsBySentencesNumber(ITextPart part) {
        //This sort is max >> min
        if (part.getPartType() != TextPartType.TEXT) {
            LOGGER.info("Incorrect input data in sortParagraphsBySentencesAmmount method of TextPartService Class");
            throw new IllegalArgumentException();
        }
        List<ITextPart> listOfParagraph = (List<ITextPart>) part.getChildren();
        listOfParagraph.sort((o1, o2) -> o2.getChildAmount() - o1.getChildAmount());
    }

    public void sortLexemesInSentencesByWordLength(ITextPart part) {
        if (part.getPartType() != TextPartType.TEXT) {
            LOGGER.info("Incorrect input data in sortParagraphsBySentencesAmount method of TextPartService Class");
            throw new IllegalArgumentException();
        }
        List<ITextPart> listOfParagraph = (List<ITextPart>) part.getChildren();
        for (ITextPart paragraph : listOfParagraph) {
            List<ITextPart> sentences = (List<ITextPart>) paragraph.getChildren();
            for (ITextPart sentence : sentences) {
                List<ITextPart> lexemes = (List<ITextPart>) sentence.getChildren();

                lexemes.sort((o1, o2) ->
                        (((List<ITextPart>) o2.getChildren()).toString().length()) -
                                (((List<ITextPart>) o1.getChildren()).toString()).length());

            }
        }
    }
}