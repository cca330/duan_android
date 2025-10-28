package com.example.duanlonmain.result;

public class SpeechResult {
    private String recognizedText;
    private String error;

    public SpeechResult(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public SpeechResult(String recognizedText, String error) {
        this.recognizedText = recognizedText;
        this.error = error;
    }

    public String getRecognizedText() { return recognizedText; }
    public String getError() { return error; }
}
