package design_pattern.VisitorDesignPattern;

import design_pattern.VisitorDesignPattern.Visitor;

public class Extractor implements Visitor {
    @Override
    public void visit(PPTFile pptFile) {
        //...
        System.out.println("Extract PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println("Extract PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println("Extract WORD.");
    }

    @Override
    public void visit(XMLFile pdfFile) {
        //...
        System.out.println("Extract XML.");
    }
}
