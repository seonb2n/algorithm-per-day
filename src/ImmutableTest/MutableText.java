package ImmutableTest;

public class MutableText {

    private String text;

    public MutableText(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text.toString();
    }
}
