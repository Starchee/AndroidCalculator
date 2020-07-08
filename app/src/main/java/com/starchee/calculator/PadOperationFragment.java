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

public class PadOperationFragment extends Fragment {

    private View.OnClickListener operationButtonOnClickListener;
    private View.OnClickListener equalsButtonOnClickListener;

    public interface OperationPadOnClickListener{
        View.OnClickListener setOperationButtonOnClickListener();
    }

    public interface EqualsOnClickListener{
        View.OnClickListener setEqualsButtonOnClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pad_operation_fragment, container, false);

        Button divideButton = rootView.findViewById(R.id.divide_button);
        divideButton.setOnClickListener(operationButtonOnClickListener);

        Button multiplyButton = rootView.findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(operationButtonOnClickListener);

        Button subtractionButton = rootView.findViewById(R.id.subtraction_button);
        subtractionButton.setOnClickListener(operationButtonOnClickListener);

        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(operationButtonOnClickListener);

        Button equalsButton = rootView.findViewById(R.id.equals_button);
        equalsButton.setOnClickListener(equalsButtonOnClickListener);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            operationButtonOnClickListener = ((PadOperationFragment.OperationPadOnClickListener) context).setOperationButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implements PadOperationFragment.OperationPadOnClickListener");
        }

        try {
            equalsButtonOnClickListener = ((PadOperationFragment.EqualsOnClickListener) context).setEqualsButtonOnClickListener();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implements PadOperationFragment.EqualsOnClickListener");
        }
    }
}
