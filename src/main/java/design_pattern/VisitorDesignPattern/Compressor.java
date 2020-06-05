package design_pattern.VisitorDesignPattern;

public class Compressor implements Visitor {
    @Override
    public void visit(PPTFile pptFile) {
        //...
        System.out.println("Compress PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println("Compress PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println("Compress WORD.");
    }

    @Override
    public void visit(XMLFile pdfFile) {
        //...
        System.out.println("Compress XML.");
    }

}
