package entity;

public class Leaf implements ITextPart, Cloneable {
    private String component;
    private TextPartType type;


    public Leaf(String component, TextPartType type) {
        this.component = component;
        this.type = type;
    }

    public String getChildren() {
        return component;
    }


    public int getChildAmount() {
        return 1;
    }

    @Override
    public TextPartType getPartType() {
        return type;
    }

    public void addComponent(ITextPart part) {
        throw new UnsupportedOperationException();
    }

    public void removeComponent(ITextPart part) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return component;
    }

}
