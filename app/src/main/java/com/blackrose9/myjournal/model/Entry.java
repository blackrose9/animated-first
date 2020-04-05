package com.blackrose9.myjournal.model;

public class Entry {
    public String index;
    public String entryTitle;
    public String entryBody;
    public Notebook notebook;


    public Entry() {
    }

    public Entry(String entryTitle, String entryBody) {
        this.index = "not specified";
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
    }

    public Entry(String entryTitle, String entryBody, Notebook notebook) {
        this.index = "not specified";
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
        this.notebook = notebook;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryBody() {
        return entryBody;
    }

    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }
}
