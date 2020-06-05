package design_pattern.VisitorDesignPattern;

public class XMLFile extends ResourceFile {
    public XMLFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }
}
