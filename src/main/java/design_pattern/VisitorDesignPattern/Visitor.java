package design_pattern.VisitorDesignPattern;

public interface Visitor {
    void visit(PdfFile pdfFile);

    void visit(PPTFile pdfFile);

    void visit(WordFile pdfFile);

    void visit(XMLFile pdfFile);
}
