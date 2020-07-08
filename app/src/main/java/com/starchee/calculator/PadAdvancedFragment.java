package com.starchee.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class PadAdvancedFragment extends Fragment {

    private View.OnClickListener onClickListener;

    private View.OnClickListener mathButtonOnClickListener;



    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pad_advanced_fragment, container, false);

        ImageView arrowButton = rootView.findViewById(R.id.arrow_pad);
        arrowButton.setOnClickListener(onClickListener);

        Button openingBracketButton = rootView.findViewById(R.id.opening_bracket_button);
        openingBracketButton.setOnClickListener(mathButtonOnClickListener);

        Button closingBracketButton = rootView.findViewById(R.id.closing_bracket_button);
        closingBracketButton.setOnClickListener(mathButtonOnClickListener);

        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mathButtonOnClickListener = ((PadFragment.MathPadOnClickListener) context).setMathButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadFragment.MathPadOnClickListener");
        }
    }

}

