package com.starchee.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PadNumberFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener, MainActivityPadOperationListener {

    private Button delButton;
    private Button clrButton;
    private  boolean ClrButtonVisible = false;
    private PadNumberFragmentOnClickListener padNumberFragmentOnClickListener;

    public interface PadNumberFragmentOnClickListener{
        void numberButtonOnClickListener(String text);
        void dotButtonOnClickListener(String text);
        void delButtonOnClickListener();
        void delButtonOnLongClickListener();
        void clrButtonOnClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.pad_number_fragment, container, false);

        Button zeroButton = rootView.findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(this);

        Button oneButton = rootView.findViewById(R.id.one_button);
        oneButton.setOnClickListener(this);

        Button twoButton = rootView.findViewById(R.id.two_button);
        twoButton.setOnClickListener(this);

        Button threeButton = rootView.findViewById(R.id.three_button);
        threeButton.setOnClickListener(this);

        Button fourButton = rootView.findViewById(R.id.four_button);
        fourButton.setOnClickListener(this);

        Button fiveButton = rootView.findViewById(R.id.five_button);
        fiveButton.setOnClickListener(this);

        Button sixButton = rootView.findViewById(R.id.six_button);
        sixButton.setOnClickListener(this);

        Button sevenButton = rootView.findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(this);

        Button eightButton = rootView.findViewById(R.id.eight_button);
        eightButton.setOnClickListener(this);

        Button nineButton = rootView.findViewById(R.id.nine_button);
        nineButton.setOnClickListener(this);

        Button dotButton = rootView.findViewById(R.id.dot_button);
        dotButton.setOnClickListener(this);


        delButton = rootView.findViewById(R.id.del_button);
        delButton.setOnClickListener(this);
        delButton.setOnLongClickListener(this);


        clrButton = rootView.findViewById(R.id.clr_button);
        clrButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            padNumberFragmentOnClickListener = (PadNumberFragment.PadNumberFragmentOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadNumberFragment.PadNumberFragmentOnClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dot_button){
            padNumberFragmentOnClickListener.dotButtonOnClickListener(((Button) view).getText().toString());
        } else if (view.getId() == R.id.del_button){
            padNumberFragmentOnClickListener.delButtonOnClickListener();
        } else if (view.getId() == R.id.clr_button){
            padNumberFragmentOnClickListener.clrButtonOnClickListener();
            clrButton.setVisibility(View.INVISIBLE);
            delButton.setVisibility(View.VISIBLE);
        } else {
            padNumberFragmentOnClickListener.numberButtonOnClickListener(((Button) view).getText().toString());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        padNumberFragmentOnClickListener.delButtonOnLongClickListener();
        return true;
    }

    @Override
    public void setClrButtonVisible() {
        delButton.setVisibility(View.INVISIBLE);
        clrButton.setVisibility(View.VISIBLE);
    }
}
