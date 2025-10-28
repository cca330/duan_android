package com.example.duanlonmain.writing.data.remote;

import java.util.List;

public class LanguageToolResponse {
    public List<Match> matches;

    public static class Match {
        public String message;
        public int offset, length;
        public List<Replacement> replacements;

        public static class Replacement {
            public String value;
        }
    }
}
