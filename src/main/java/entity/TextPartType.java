package entity;

public enum TextPartType {
    TEXT,
    PARAGRAPH{
        {
            this.begin = "\t";
            this.end = "\n";
        }
    },
    SENTENCE{
        {
            this.end = " ";
        }
    },
    LEXEME{
        {
            this.end = " ";
        }
    },
    WORD,
    SYMBOL,
    PUNCTUATION;

    String begin = "";
    String end = "";


    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }
}
