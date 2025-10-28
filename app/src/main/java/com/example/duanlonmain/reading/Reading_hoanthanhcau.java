package com.example.duanlonmain.reading;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;

import com.example.duanlonmain.R;

public class Reading_hoanthanhcau extends AppCompatActivity {
    ImageView btnPrey, btnNext,btnBack;
    ReadingAdapter adapter;

    RecyclerView recyclerQuestions;
    ArrayList<Reading_quetion_hoanthanhcau> questions;

    final int[] currentPosition = {0};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reading_hoanthanhcau);

        btnPrey = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> {
            finish();
        });
        recyclerQuestions = findViewById(R.id.recyclerQuestions);



        questions = new ArrayList<>();






        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. addition",
                "B. additions",
                "C. additional",
                "D. additionlly"},
                "The coordinator concluded that Thursday's workshop will need ___ tables.",
                "文A Người điều phối kết luận rằng hội thảo thứ năm sẽ cần 'thêm' bàn",
                2,
                new String[]{"文A (n)sự bổ sung","文A sự bổ sung(danh từ số nhiều)","文A (adj)bổ sung","文A (adv)thêm vào đó"},
                1,
                new String[]{"Chỗ trống cần một tính từ để bổ nghĩa cho danh từ 'tables'",
                        "A. addition là một danh từ có nghĩa là 'hành động thêm vào' hoặc 'thứ được thêm vào'. Nó không thể trực tiếp bổ sung nghĩa cho danh từ 'tables'",
                        "B. additions là dạng số nhiều của danh từ addition. Tương tự như addition, nó cũng là một danh từ và không thể hoạt động như một tính từ để mô tả tables",
                        "C. additional là một tính từ, có nghĩa là 'được thêm vào, phụ thêm, hoặc bổ sung'. Nó bổ nghĩa chính xác danh từ tables, chỉ ra rằng cần thêm bàn. Điều anyf hoàn toàn phù hớp với ngữ cảnh, ngụ ý rằng buổi hội thảo caanf nhiều bàn hơn số lượng hiện có.",
                        "D. additionally là một trạng từ có nghĩa là 'ngoiaf ra, cũng vậy'. Trạng từ bổ nghĩa cho động từ ,tính từ hoặc các trạng từ khác, nhưng chúng không trực tiếp bổ sung nghĩa cho danh từ. Do đó sai về mựt ngữ pháp ở vị trí này.",
                        "Vì vậy, additional là lựa chọn đúng"}));





        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. it",
                "B. it's",
                "C. it's own",
                "D. itself"},
                "Regarding the package delayed during shipping, we will assume accountability for ___ .",
                "文A  Về kiện hàng bị chậm trễ trong quá trình vận chuyển, chúng tôi sẽ chịu trách 'nhiệm'",
                0,
                new String[]{"文A nó, việc đó (đại từ, tân ngữ)","文A của nó (tính từ sở hữu)","文A tự nó","文A bản thân nó (đại từ phân thân)"},
                2,
                new String[]{"Cụm từ 'asume accountability for' có nghĩa là 'chịu trách nhiệm về'. Chỗ trống càn một tân ngữ để chỉ 'the package delayed dủing shipping' .",
                        "A. it là một đại từ tân ngữ(số ít, trung tính). Nó có thể trực tiếp theo sau giới từ 'for' và chỉ 'the package'. Điều này phù hợp với yêu caauf ngữ pháp và ý nghĩa",
                        "B. í's là tính từ sở hữu/từ hạn định hoặc đại từ sở hữu. Với vai trò tính từ sở hữu, nó phải đứng trước một danh từ. Nó không thể đứng một mình sau giới từ, nên nó không phù hợp trong ngữ cảnh này",
                        "C. its own là một cụm từ sở hữu được dùng để nhấn mạnh, thường chỉ ra một cái gì đó thuộc về riêng chủ ngữ hoặc hành động độc lập. Nó thường bổ nghĩa cho một danh từ (ví dụ: its own unique features - những đặc điểm độc đáo của riêng nó) hoặc có thể hoạt động như một đại từ sở hữu khi danh từ được ngụ ý. Trong ngữ cảnh này, 'accountability for its own' không có nghĩa về mặt ngữ pháp hoặc ngữ nghĩa.",
                        "D. itself là một đại từ phản thân hoặc đại từ nhấn mạnh. Với vai trò đại từ phản thân, nó phản ánh hành động trở lại chủ ngữ (ví dụ: 'The package moved itself' - Gói hàng tự di chuyển). Chủ ngữ ở đây là 'we' (chúng tôi), không phải 'the package'. Với vai trò đại từ nhấn mạnh, nó nhấn mạnh một danh từ hoặc đại từ (ví dụ: 'The package itself was damaged' - Bản thân gói hàng bị hỏng). Nó không phù hợp sau 'for' như một tân ngữ trực tiếp chỉ 'the package' khi chủ ngữ là" +
                                "we.",
                        "Do đó it là lựa chọn đúng vì nó là đại từ tân ngữ phù hợp để chỉ 'the package'"}));





        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. we",
                "B. our",
                "C. ours",
                "D. ourselves"},
                "Switch to a different courier service if Apex Logistics cannot speed up delivery of ___ shipment",
                "文A Chuyển sang một dịch vụ giao hàng khác nếu Apax Logistics khoong thể đaayr nhanh việc gia lô hàng của 'chúng ta'",
                1,
                new String[]{"文A chúng ta (đại từ nhân xưng)","文A của chúng ta (tính từ sở hữu)","文A cái của chúng ta (đại từ sở hữu)","文A chính của chúng ta (đại từ phân thân)"},
                3,
                new String[]{"Chỗ trống cần một tính từ sở hữu (possessive adjective) để bổ nghĩa cho danh từ 'shipment' (lỗ hàng).",
                        "A. we là đại từ chủ ngữ (subject pronoun). Nó được dùng làm chủ ngữ của động từ (ví dụ: 'We will switch'). Nó không thể bổ nghĩa cho danh từ. ",
                        "B. our là tính từ sở hữu (possessive adjective). Nó được dùng trước một danh từ để chỉ sự sở hữu (ví dụ: 'our car'). Trong câu này, 'our shipment' có nghĩa là 'lô hàng thuộc về chúng tôi', hoàn toàn phù hợp với ngữ cảnh. ",
                        "C. ours là đại từ sở hữu (possessive pronoun). Nó đứng một mình và thay thế một cụm danh từ sở hữu (ví dụ: 'The car is ours'). Nó không đứng trước danh từ.",
                        "D. ourselves là đại từ phản thân/nhấn mạnh (reflexive/intensive pronoun). Nó dùng để chỉ lại chủ ngữ hoặc nhấn" +
                                "mạnh chủ ngữ (ví dụ: 'We did it ourselves'). Nó không thể bổ nghĩa cho danh từ theo cách này.",
                        "Do đó our lả lựa chọn đúng vì nó là một tính từ sở hữu được sử dụng chính xác trước danh từ shipment để chỉ sở hữu"}));






        questions.add(new Reading_quetion_hoanthanhcau(new String[]{"A. easy",
                "B. easa",
                "C. easiest",
                "D. easily"},
                "The promotion for Lynn's Backery was ___ Peak Media's top campagn ò the season.",
                "文A Chương trình quảng bá cho Lynn's Bakery rất 'dễ dàng' để trở thành chiến dịch hàng đầu của công ty Peak Media",
                3,
                new String[]{"文A (adj) dễ dàng","文A (v)giảm nhẹ, (n0 sự dễ dàng","文A dễ dàng nhất (tính từ so sánh nhất)","文A (adv) một cách dễ dàng"},
                4,
                new String[]{"Câu này yêu cầu một trạng từ (adverb) để mô tả cách thức mà chương trình khuyến mãi đã trở thành chiến dịch hàng đầu của Peak Media trong mùa'.",
                        "A. easy là một tính từ (adjective). Tính từ mô tả danh từ. 'Easy” không thể trực tiếp bổ nghĩa cho 'Peak Media’s top campaign' trong ngữ cảnh này, cũng như không thể mô tả động từ liên kết 'was” theo cách này. Ví dụ, chúng ta nói 'The campaign was easy” (Chiến dịch dễ dàng), nhưng không nói 'The promotion was easy Peak Media's top campaign'.",
                        "B. ease là một danh từ (noun) có nghĩa là 'sự dễ dàng' hoặc 'sự thoải mái'. Một danh từ không thể đặt ở vị trí này trong câu để mô tả động từ hoặc trạng thái. ",
                        "C. easiest là dạng so sánh nhất của tính từ 'easy” (dễ nhất). Đây cũng là một tính từ. Nó không thể đượC SỬ dụng ở vị trí này theo cách tương tự như 'easy”. Chúng ta nói 'This was the easiest campaign' (Đây là chiến dịch dễ nhất). nhưng không nói 'The promontion was easiest Peak Media's top campaign",
                        "D. easily là một trạng từ (adverb). Trạng từ bổ nghĩa cho động từ, tính từ hoặc các trạng từ khác. Trong câu này, 'easily” bổ nghĩa cho ý nghĩa chung của động từ 'was' và cụm danh từ theo sau, cho thấy rằng chương trình khuyến mãi đó, không nghi ngờ gì hay không gặp khó khăn gì, là chiến dịch hàng đầu. Nó mô tả 'cách thức' chương trình khuyến mãi được coi là hoặc được công nhận là chiến dịch hàng đầu. 'Easily” thường được sử dụng trước một cụm danh từ là bổ ngữ cho động từ 'to be' để nhấn mạnh rằng một điều gì đó là không thể tranh cãi là tốt nhất hoặc nổi bật nhất (ví dụ: 'She was easily the best candidate' - Cô ấy dễ dàng là ứng cử viên tốt nhất). Điều này hoàn toàn phù hợp với ngữ cảnh ở đây.",
                        "Do đó, easily là lựa chọn đúng vì nó là trạng từ duy nhất mô tả cách thức chương trình khuyến mãi được coi là chiến dịch hàng đầu."}));






        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerQuestions.setLayoutManager(layoutManager);
        adapter = new ReadingAdapter(this, questions);
        recyclerQuestions.setAdapter(adapter);



        btnNext.setOnClickListener(v -> {
            if (currentPosition[0] < questions.size() - 1) {
                currentPosition[0]++;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
            }
        });



        btnPrey.setOnClickListener(v -> {
            if (currentPosition[0] > 0) {
                currentPosition[0]--;
                recyclerQuestions.smoothScrollToPosition(currentPosition[0]);
            }
        });


        // hiển thị 1 câu / trang
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerQuestions);

    }
}