package com.example.duanlonmain.vocabulary;

import java.util.List;

public class WordResponse {
    public String word;
    public List<Phonetic> phonetics;
    public List<Meaning> meanings;

    public static class Phonetic {
        public String text;
        public String audio;
    }

    public static class Meaning {
        public String partOfSpeech;
        public List<Definition> definitions;
    }

    public static class Definition {
        public String definition;
        public String example;
    }
}

