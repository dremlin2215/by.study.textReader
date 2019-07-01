package entity;

public interface ITextPart {
    void addComponent(ITextPart part);
    void removeComponent(ITextPart part);
    Object getChildren();
    int getChildAmount();
    TextPartType getPartType();
}
