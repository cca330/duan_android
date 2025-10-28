package com.example.duanlonmain.writing.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;
import com.example.duanlonmain.writing.ui.exercise.WritingExerciseActivity;
import com.example.duanlonmain.writing.viewmodel.WritingHomeViewModel;
import java.util.ArrayList;

public class WritingHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private WritingTopicAdapter adapter;
    private WritingHomeViewModel viewModel;
    private ImageButton btnBack;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_writing_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_topics);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // KHỞI TẠO ADAPTER TRƯỚC
        adapter = new WritingTopicAdapter(new ArrayList<>(), topic -> {
            Intent intent = new Intent(requireContext(), WritingExerciseActivity.class);
            intent.putExtra("TITLE", topic.title);
            intent.putExtra("MIN_WORDS", topic.minWords);
            intent.putExtra("INSTRUCTION", topic.instruction);
            intent.putExtra("MIN_WORDS", topic.minWords);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);  // GẮN TRƯỚC KHI OBSERVE

        viewModel = new ViewModelProvider(this).get(WritingHomeViewModel.class);
        viewModel.getTopics().observe(getViewLifecycleOwner(), topics -> {
            adapter.updateData(topics);
        });
    }
}