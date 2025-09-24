package com.example.duanlonmain.chat_bot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatwithAI extends AppCompatActivity {

    private EditText editMessage;
    private ImageButton btnSend;
    ImageButton btnBack;
    ImageButton btn_input_Image;
    ImageButton btnMic;

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<MessageEntity> messages = new ArrayList<>();

    private AppDatabase db;
    private MessageDao messageDao;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    // AI model
    GenerativeModel generativeModelInstance = new GenerativeModel(
            "gemini-2.5-flash",
                                                                                                                                                                    "AIzaSyDIet7saT20mNZ734GfuJGJhAdVEUTHP9g"
    );
    GenerativeModelFutures model = GenerativeModelFutures.from(generativeModelInstance);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwith_ai);

        recyclerView = findViewById(R.id.recycler_vvv);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        btn_input_Image = findViewById(R.id.btn_input_Image);
        btnMic = findViewById(R.id.btnMic);


        btn_input_Image.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*"); // Chỉ lấy ảnh
            startActivityForResult(intent, 11); // 11 là requestCode
        });




        btnMic.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vn-VN"); // Hoặc "en-US"
            try {
                startActivityForResult(intent, 22); // 22 là requestCode cho mic
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Thiết bị không hỗ trợ nhập giọng nói", Toast.LENGTH_SHORT).show();
            }
        });




        // DB
        db = AppDatabase.getDatabase(this);
        messageDao = db.messageDao();

        // RecyclerView
        adapter = new MessageAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load tin nhắn cũ
        loadMessages();

        // Xử lý gửi tin
        btnSend.setOnClickListener(v -> {
            String userMsg = editMessage.getText().toString().trim();
            if (!userMsg.isEmpty()) {
                addMessage(userMsg, true); // lưu + hiển thị
                callGemini(userMsg);
                editMessage.setText(""); // clear input
            } else {
                Toast.makeText(ChatwithAI.this, "Vui lòng nhập tin nhắn.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMessages() {
        executor.execute(() -> {
            List<MessageEntity> oldMessages = messageDao.getAllMessages();
            runOnUiThread(() -> {//quay lại UI thread để cập nhật giao diện
                messages.clear();
                messages.addAll(oldMessages);
                adapter.notifyDataSetChanged();//Báo cho RecyclerView chỉ cần vẽ thêm 1 item mới
                recyclerView.scrollToPosition(messages.size() - 1);
            });
        });
    }

    private void addMessage(String text, boolean isUser) {
        MessageEntity message = new MessageEntity(text, isUser);
        executor.execute(() -> messageDao.insert(message));
        messages.add(message);
        adapter.notifyItemInserted(messages.size() - 1);
        recyclerView.scrollToPosition(messages.size() - 1);
    }

    private void callGemini(String userMsg) {
        Content content = new Content.Builder()
                .addText(userMsg)
                .build();

        ListenableFuture<GenerateContentResponse> responseFuture = model.generateContent(content);//gửi request đến Gemini model.

        Futures.addCallback(responseFuture, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String reply = result.getText();
                runOnUiThread(() -> addMessage(reply, false));
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() ->
                        Toast.makeText(ChatwithAI.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_LONG).show()
                );
                t.printStackTrace();
            }
        }, executor);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 11: // Chọn ảnh
                    Uri selectedImageUri = data.getData();
                    Toast.makeText(this, "Đã chọn ảnh: " + selectedImageUri, Toast.LENGTH_SHORT).show();
                    // bạn có thể gửi ảnh vào RecyclerView như tin nhắn
                    break;

                case 22: // Nhập giọng nói
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result != null && !result.isEmpty()) {
                        editMessage.setText(result.get(0)); // đưa text vào ô nhập
                    }
                    break;
            }
        }
    }
}
