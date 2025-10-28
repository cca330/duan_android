package com.example.duanlonmain.writing.data.remote;

import java.util.List;

public class GeminiResponse {

    public List<Candidate> candidates;  // public

    // === CANDIDATE: PUBLIC ===
    public static class Candidate {
        public Content content;  // public
    }

    // === CONTENT: PUBLIC ===
    public static class Content {
        public List<Part> parts;  // public
    }

    // === PART: PUBLIC ===
    public static class Part {
        public String text;  // public
    }
}