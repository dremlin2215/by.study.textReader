package entity;

import java.util.LinkedList;
import java.util.List;

public class TextPart implements ITextPart, Cloneable {

    private List<ITextPart> components = new LinkedList<>();
    private TextPartType type;

    public TextPart(TextPartType type) {
        this.type = type;
    }

    public TextPart(ITextPart part) {
        this.components = (List<ITextPart>) part.getChildren();
        this.type = part.getPartType();
    }

    @Override
    public void addComponent(ITextPart part) {
        components.add(part);
    }

    @Override
    public void removeComponent(ITextPart part) {
        components.remove(part);
    }

    @Override
    public List getChildren() {
        return components;
    }

    @Override
    public int getChildAmount() {
        return components.size();
    }

    @Override
    public TextPartType getPartType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type.getBegin());

        components.forEach(iTextPart -> builder.append(iTextPart.toString()));

        builder.append(type.getEnd());
        String preparedString = builder.toString();

        if (this.type == TextPartType.TEXT) {
            preparedString = "\t" + preparedString.trim();
            preparedString = preparedString.replaceAll(" +\n", "\n");
            preparedString = preparedString.replaceAll(" +", " ");
        }

        return preparedString;
    }
}
