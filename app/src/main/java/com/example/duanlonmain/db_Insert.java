package com.example.duanlonmain;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class db_Insert extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_blanklayout);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db,1,2); // Xóa bảng cũ nếu đã tồn tại

        // --- INSERT noi_part1 ---
        dbHelper.insertPart1("This Sunday, Jazz Master Coffee Shop invites coffee lovers to a tasting event at our store. Visit the shop between 6 and 9 o’clock to enjoy half price desserts, live music, and our exclusive coffee from Columbia. While you’re here, you can complete a customer survey to receive a pack of free coffee to brew at home.");
        dbHelper.insertPart1("Welcome to the Metropolitan Museum of Art. We hope your tour is informative and enjoyable. Since there are a lot of visitors here today, we will divide into two groups. After completing the tour, you may visit the gift shop on the first floor, enjoy a meal in the cafeteria, or learn about upcoming exhibits at the information desk.");
        dbHelper.insertPart1("Thank you for calling Madison Cinema, Bristol’s favorite movie theater. This week, we are offering special discounts on popcorn, sweets, and other snacks. Please press one to hear the list of this week’s movies, or press two to buy tickets in advance. If you have any other requests, press three to speak to our staff member.");
        dbHelper.insertPart1("On tonight’s news, we will have a report on plans for a new shopping mall in Winchester scheduled to be built over the next 6 months. The center will feature a various selection of clothing, electronics, and living items. If you want to hear more about this project, tune into tonight’s broadcast at 8 P.M.");
        dbHelper.insertPart1("Welcome to USC Career Center, where we help people find jobs. Today, I’d like to introduce our new employee database. You can use this database to search for jobs, receive updates, and even submit your resume with a single click. Moreover, the resources/and database can help you promote yourself to employers who may be interested in your work skills.");

        // --- INSERT noi_part2 ---
        try {
            dbHelper.insertPart2(R.drawable.sample_1);
            dbHelper.insertPart2(R.drawable.sample_2);
            dbHelper.insertPart2(R.drawable.sample_3);
            dbHelper.insertPart2(R.drawable.sample_4);
            dbHelper.insertPart2(R.drawable.sample_5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- INSERT noi_part3 ---
        dbHelper.insertPart3(
                "Imagine that a British marketing company is doing research in your country. You have agreed to participate in a telephone interview about the book discussion",
                "Do you enjoy joining book discussion groups? Why?",
                "Do you think it is a good idea to organize a book discussion group meeting at a restaurant?",
                "Do you think it is necessary to limit the number of participants of that group?"
        );

        dbHelper.insertPart3(
                "Imagine that a local magazine is conducting a survey on purchasing textbooks. You have agreed to join a telephone interview regarding buying books.",
                "Where is the ideal place to buy textbooks in your area? Have you ever bought books there?",
                "Do you think it is a good idea to buy books through online platforms?",
                "Do you like reading electronic books or paper ones?"
        );

        dbHelper.insertPart3(
                "Imagine that a cooking magazine is conducting a survey on cooking habits. You have agreed to join a telephone interview regarding cooking habits.",
                "When was the last time you cooked? What did you make?",
                "How do you usually get recipes when you are cooking a new dish? Why?",
                "Do you like reading food magazines? Why or why not?"
        );

        // --- INSERT noi_part4 ---
        try {
            dbHelper.insertPart4(R.drawable.context_sample_1,
                    "When does the first meeting start and finish? And who will I meet?",
                    "I remember that I will have lunch at noon with Andrew Simmons. Is this right?",
                    "It's supposed that there are some sessions after lunch. Can you give me details of the activities I need to do in the afternoon?"
            );

            dbHelper.insertPart4(R.drawable.context_sample_2,
                    "Do you offer any discount to attendees? And how can I get it?",
                    "All the performances are scheduled on Saturdays, right?",
                    "I am eager to enjoy the festival scheduled in New York City. Can you give me detailed information about them?"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- INSERT noi_part5 ---
        dbHelper.insertPart5("Do you prefer to travel alone or with other people?");
        dbHelper.insertPart5("What advantages a company can receive when it collects online reviews from its customers?");
        dbHelper.insertPart5("Do you agree or disagree that teenagers should try a lot of kinds of hobbies?");
        dbHelper.insertPart5("Do you think that current employees should learn one art course while working at the company?");
        dbHelper.insertPart5("More and more companies use webcam service to hold virtual meetings. What are the disadvantages of doing so?");
    }
}
