package com.example.androidpractice.ndk.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidpractice.R;
import com.example.androidpractice.databinding.FragmentNdk2Binding;
import com.example.androidpractice.databinding.FragmentNdkBinding;


public class NdkFragment extends Fragment {
    private FragmentNdk2Binding binding;

    public native String helloWorld();

    public NdkFragment() {
        System.loadLibrary("ndktest");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNdk2Binding.inflate(getLayoutInflater());
        binding.textView.setText(helloWorld());
        return binding.getRoot();
    }
}