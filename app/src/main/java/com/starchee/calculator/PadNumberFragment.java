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

public class PadNumberFragment extends Fragment {

    private View.OnClickListener mathButtonOnClickListener;
    private View.OnClickListener deleteButtonOnClickListener;
    private View.OnLongClickListener deleteButtonOnLongClickListener;
    private View.OnClickListener dotButtonOnClickListener;

    public interface DotButtonOnClickListener{
        View.OnClickListener setDotButtonOnClickListener();
    }

    public interface DeleteButtonOnClickListener{
        View.OnClickListener setDeleteButtonOnClickListener();
    }

    public interface DeleteButtonOnLongClickListener{
        View.OnLongClickListener setDeleteButtonOnLongClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.pad_number_fragment, container, false);

        Button zeroButton = rootView.findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(mathButtonOnClickListener);

        Button oneButton = rootView.findViewById(R.id.one_button);
        oneButton.setOnClickListener(mathButtonOnClickListener);

        Button twoButton = rootView.findViewById(R.id.two_button);
        twoButton.setOnClickListener(mathButtonOnClickListener);

        Button threeButton = rootView.findViewById(R.id.three_button);
        threeButton.setOnClickListener(mathButtonOnClickListener);

        Button fourButton = rootView.findViewById(R.id.four_button);
        fourButton.setOnClickListener(mathButtonOnClickListener);

        Button fiveButton = rootView.findViewById(R.id.five_button);
        fiveButton.setOnClickListener(mathButtonOnClickListener);

        Button sixButton = rootView.findViewById(R.id.six_button);
        sixButton.setOnClickListener(mathButtonOnClickListener);

        Button sevenButton = rootView.findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(mathButtonOnClickListener);

        Button eightButton = rootView.findViewById(R.id.eight_button);
        eightButton.setOnClickListener(mathButtonOnClickListener);

        Button nineButton = rootView.findViewById(R.id.nine_button);
        nineButton.setOnClickListener(mathButtonOnClickListener);

        Button dotButton = rootView.findViewById(R.id.dot_button);
        dotButton.setOnClickListener(dotButtonOnClickListener);

        Button delButton = rootView.findViewById(R.id.del_button);
        delButton.setOnClickListener(deleteButtonOnClickListener);
        delButton.setOnLongClickListener(deleteButtonOnLongClickListener);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mathButtonOnClickListener = ((PadFragment.MathPadOnClickListener) context).setMathButtonOnClickListener();
            deleteButtonOnClickListener = ((PadNumberFragment.DeleteButtonOnClickListener) context).setDeleteButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadNumberFragment.MathPadOnClickListener");
        }

        try {
            dotButtonOnClickListener = ((PadNumberFragment.DotButtonOnClickListener) context).setDotButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadNumberFragment.DotButtonOnClickListener");
        }

        try {
            deleteButtonOnClickListener = ((PadNumberFragment.DeleteButtonOnClickListener) context).setDeleteButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadNumberFragment.DeleteButtonOnClickListener");
        }

        try {
            deleteButtonOnLongClickListener = ((PadNumberFragment.DeleteButtonOnLongClickListener) context).setDeleteButtonOnLongClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadNumberFragment.DeleteButtonOnLongClickListener");
        }
    }
}
