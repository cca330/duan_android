package com.example.duanlonmain.writing.viewmodel;

import androidx.lifecycle.*;

import com.example.duanlonmain.writing.data.local.model.WritingTopic;
import java.util.*;

public class WritingHomeViewModel extends ViewModel {
    private final MutableLiveData<List<WritingTopic>> topics = new MutableLiveData<>();

    public WritingHomeViewModel() {
        List<WritingTopic> list = new ArrayList<>();
        list.add(new WritingTopic(1, "My Best Friend", "Viết ít nhất 80 từ về người bạn thân nhất.", 80) {});
        list.add(new WritingTopic(2, "A Memorable Trip", "Mô tả chuyến đi đáng nhớ.", 100));
        list.add(new WritingTopic(3, "My Favorite Hobby", "Nói về sở thích yêu thích.", 70));
        list.add(new WritingTopic(4, "A Place I Want to Visit", "Địa điểm bạn muốn đến trong tương lai và lý do.", 70));
        list.add(new WritingTopic(5, "My Hobby", "Sở thích của bạn và khi nào bạn thường làm điều đó.", 70));
        list.add(new WritingTopic(6, "The Importance of Learning English", "Tại sao việc học tiếng Anh lại quan trọng.", 90));
        list.add(new WritingTopic(7, "How to Stay Healthy", "Cách để giữ gìn sức khỏe tốt.", 90));
        list.add(new WritingTopic(8, "Advantages and Disadvantages of the Internet", "Lợi ích và tác hại của Internet.", 100));
        list.add(new WritingTopic(9, "Environmental Protection", "Làm thế nào để bảo vệ môi trường.", 100));
        list.add(new WritingTopic(10, "A Memorable Trip", "Một chuyến đi đáng nhớ trong cuộc đời bạn.", 90));
        list.add(new WritingTopic(11, "The Role of Technology in Education", "Vai trò của công nghệ trong giáo dục.", 120));
        list.add(new WritingTopic(12, "Social Media and Its Impact on Teenagers", "Ảnh hưởng của mạng xã hội đến giới trẻ.", 120));
        list.add(new WritingTopic(13, "How to Achieve Your Goals", "Cách để đạt được mục tiêu của bản thân.", 110));
        list.add(new WritingTopic(14, "The Importance of Time Management", "Tầm quan trọng của việc quản lý thời gian.", 110));
        list.add(new WritingTopic(15, "My Opinion on Online Learning", "Quan điểm của bạn về việc học trực tuyến.", 100));

        topics.postValue(list);
    }

    public LiveData<List<WritingTopic>> getTopics() { return topics; }
}