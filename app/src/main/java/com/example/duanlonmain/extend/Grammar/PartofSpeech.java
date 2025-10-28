package com.example.duanlonmain.extend.Grammar;

public class PartofSpeech {
    private String title;
    private String definition;
    private String types;
    private String examples;
    private int imageResId;

    public PartofSpeech(String title, String definition, String types, String examples, int imageResId) {
        this.title = title;
        this.definition = definition;
        this.types = types;
        this.examples = examples;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }

    public String getTypes() {
        return types;
    }

    public String getExamples() {
        return examples;
    }

    public int getImageResId() {
        return imageResId;
    }
}

