package com.blackrose9.myjournal.model;

public class Entry {
    //    public int entryId;
    public String entryTitle;
    public String entryBody;
    public Notebook notebook;

    public Entry(String entryTitle, String entryBody) {
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
    }

    public Entry(String entryTitle, String entryBody, Notebook notebook) {
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
        this.notebook = notebook;
    }

//    public int getEntryId() {
//        return entryId;
//    }
//
//    public void setEntryId(int entryId) {
//        this.entryId = entryId;
//    }

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
