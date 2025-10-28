package com.example.duanlonmain.result;

public class EvaluationResult {
    private String feedback;
    private String error;

    public EvaluationResult(String feedback) {
        this.feedback = feedback;
    }

    public EvaluationResult(String feedback, String error) {
        this.feedback = feedback;
        this.error = error;
    }

    public String getFeedback() { return feedback; }
    public String getError() { return error; }
}
