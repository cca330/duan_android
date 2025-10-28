package com.example.duanlonmain.utils;

public class WritingUtils {
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public static String formatFeedback(int score) {
        if (score >= 90) return "Không có gì gọi là hoàn hảo cả. Nó chỉ mang tính chất tương đối mà thôi !!";
        if (score >= 80) return "Tuyệt vời! Bài viết rất mạch lạc.";
        if (score >= 70) return "Tốt! Cần cải thiện từ vựng.";
        return "Cố lên! Hãy luyện tập thêm.";
    }
}