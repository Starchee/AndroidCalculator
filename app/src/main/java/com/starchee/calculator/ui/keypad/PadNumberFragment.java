package com.starchee.calculator.ui.keypad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.starchee.calculator.R;
import com.starchee.calculator.Utils.ViewModelProviderFactory;
import com.starchee.calculator.App;
import com.starchee.calculator.viewModels.DisplayViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class PadNumberFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private Button delButton;
    private Button clrButton;
    private DisplayViewModel displayViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.pad_number_fragment, container, false);

        ((App)getActivity().getApplication()).getAppComponent().inject(this);

        displayViewModel = new ViewModelProvider(requireActivity(), viewModelProviderFactory).get(DisplayViewModel.class);
        displayViewModel.getVisibleClrLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean visibleClrLiveData) {
                if (visibleClrLiveData){
                    setClrButtonVisible();
                } else {
                    setClrButtonInvisible();
                }
            }
        });


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
    public void onClick(View view) {
        if (view.getId() == R.id.dot_button){
            displayViewModel.setDotInExpression(((Button) view).getText().toString());
        } else if (view.getId() == R.id.del_button){
            displayViewModel.deleteExpressionToken();
        } else if (view.getId() == R.id.clr_button){
            displayViewModel.clearDisplay();
            setClrButtonInvisible();
        } else {
            displayViewModel.setOperandInExpression(((Button) view).getText().toString());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        displayViewModel.clearDisplay();
        return true;
    }

    private void setClrButtonVisible() {
        delButton.setVisibility(View.INVISIBLE);
        clrButton.setVisibility(View.VISIBLE);
    }

    private void setClrButtonInvisible() {
        clrButton.setVisibility(View.INVISIBLE);
        delButton.setVisibility(View.VISIBLE);
    }
}
