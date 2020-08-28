package com.example.h_saveme.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.h_saveme.R;
import com.example.h_saveme.directory;
import com.example.h_saveme.search;
import com.example.h_saveme.zzz;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);//ربط
        final Button button1 = root.findViewById(R.id.button1);
              Button button2 = root.findViewById(R.id.button2);
       // homeViewModel.getText().observe(this, new Observer<String>() {
       //     @Override
        //    public void onChanged(@Nullable String s) {
        //       textView.setText(s);
        //   }
       //  });
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
   startActivity(new Intent(getContext(), directory.class));
    }
});
button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      startActivity(new Intent(getContext(), search.class));
    }
});
        return root;
    }
}