package by.epam.task11.controller.command.dialog;


import by.epam.task11.entities.Composite;
import by.epam.task11.service.chain.impl.ParagraphHandler;

public class Request {

    private String fileName;
    private ParagraphHandler abstractHandler;
    private Composite composite;
    private Character letter;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setAbstractHandler(ParagraphHandler abstractHandler) {
        this.abstractHandler = abstractHandler;
    }

    public ParagraphHandler getAbstractHandler() {
        return abstractHandler;
    }

    public void setComposite(Composite composite) {
        this.composite = composite;
    }

    public Composite getComposite() {
        return composite;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }
}
