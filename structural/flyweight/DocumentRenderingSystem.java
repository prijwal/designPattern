package documentRendering;

import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface DocumentStyle {
    void applyStyle(String content);
}

// Concrete flyweight class for heading style
class HeadingStyle implements DocumentStyle {
    private final String styleType = "Heading Style";

    @Override
    public void applyStyle(String content) {
        System.out.println("Applying " + styleType + " to: " + content);
    }
}

// Concrete flyweight class for paragraph style
class ParagraphStyle implements DocumentStyle {
    private final String styleType = "Paragraph Style";

    @Override
    public void applyStyle(String content) {
        System.out.println("Applying " + styleType + " to: " + content);
    }
}

// Flyweight Factory
class StyleFactory {
    private static Map<String, DocumentStyle> styleMap = new HashMap<>();

    public static DocumentStyle getStyle(String styleType) {
        DocumentStyle style = styleMap.get(styleType);
        if (style == null) {
            switch (styleType) {
                case "heading":
                    style = new HeadingStyle();
                    break;
                case "paragraph":
                    style = new ParagraphStyle();
                    break;
            }
            styleMap.put(styleType, style);
        }
        return style;
    }
}

// Client code
public class DocumentRenderingSystem {

    public static void main(String[] args) {
        DocumentStyle headingStyle = StyleFactory.getStyle("heading");
        DocumentStyle paragraphStyle = StyleFactory.getStyle("paragraph");

        headingStyle.applyStyle("Chapter 1: Introduction");
        paragraphStyle.applyStyle("This is the first paragraph of the document.");

        // Reusing styles
        DocumentStyle headingStyle2 = StyleFactory.getStyle("heading");
        headingStyle2.applyStyle("Chapter 2: Background");
        
        System.out.println("Total distinct style objects created: " + StyleFactory.styleMap.size());
    }
}

