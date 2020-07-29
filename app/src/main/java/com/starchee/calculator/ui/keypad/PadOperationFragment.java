package com.starchee.calculator.ui.keypad;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.starchee.calculator.R;
import com.starchee.calculator.viewModels.DisplayViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class PadOperationFragment extends Fragment implements View.OnClickListener {

    private OperationPadOnClickListener operationPadOnClickListener;
    private DisplayViewModel displayViewModel;

    public interface OperationPadOnClickListener{
        void equalsButtonOnClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pad_operation_fragment, container, false);
        displayViewModel = new ViewModelProvider(requireActivity()).get(DisplayViewModel.class);

        Button divideButton = rootView.findViewById(R.id.divide_button);
        divideButton.setOnClickListener(this);

        Button multiplyButton = rootView.findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(this);

        Button subtractionButton = rootView.findViewById(R.id.subtraction_button);
        subtractionButton.setOnClickListener(this);

        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        Button equalsButton = rootView.findViewById(R.id.equals_button);
        equalsButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            operationPadOnClickListener = (PadOperationFragment.OperationPadOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implements PadOperationFragment.OperationPadOnClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.equals_button){
            displayViewModel.setEquals();
            operationPadOnClickListener.equalsButtonOnClickListener();
        } else {
            displayViewModel.setOperatorInExpression(((Button) view).getText().toString());
        }
    }
}
