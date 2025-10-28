package com.example.duanlonmain.writing.data.remote;

import java.util.List;

public class GeminiRequest {
    public List<Content> contents;

    public GeminiRequest(String prompt, String essay) {
        this.contents = List.of(
                new Content("user", prompt + "\n\nEssay: " + essay),
                new Content("model", "Chấm điểm từ 0-100, sửa lỗi, phản hồi ngắn gọn.")
        );
    }

    static class Content {
        public String role;
        public List<Part> parts;
        public Content(String role, String text) {
            this.role = role;
            this.parts = List.of(new Part(text));
        }
    }

    static class Part {
        public String text;
        public Part(String text) { this.text = text; }
    }
}
