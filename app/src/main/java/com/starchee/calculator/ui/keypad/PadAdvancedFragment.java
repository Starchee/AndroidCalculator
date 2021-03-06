package com.starchee.calculator.ui.keypad;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.starchee.calculator.R;
import com.starchee.calculator.Utils.ViewModelProviderFactory;
import com.starchee.calculator.App;
import com.starchee.calculator.viewModels.DisplayViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


public class PadAdvancedFragment extends Fragment  implements View.OnClickListener {

    private PadAdvancedFragmentOnClickListener padAdvancedFragmentOnClickListener;
    private DisplayViewModel displayViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    public interface PadAdvancedFragmentOnClickListener{
        void padAdvanceArrowButtonOnClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pad_advanced_fragment, container, false);

        ((App)getActivity().getApplication()).getAppComponent().inject(this);

        displayViewModel = new ViewModelProvider(requireActivity(),viewModelProviderFactory).get(DisplayViewModel.class);


        ImageView arrowButton = rootView.findViewById(R.id.arrow_pad);
        arrowButton.setOnClickListener(this);

        Button openingBracketButton = rootView.findViewById(R.id.opening_bracket_button);
        openingBracketButton.setOnClickListener(this);

        Button closingBracketButton = rootView.findViewById(R.id.closing_bracket_button);
        closingBracketButton.setOnClickListener(this);

        Button dollarButton = rootView.findViewById(R.id.dollar_button);
        dollarButton.setOnClickListener(this);

        Button euroButton = rootView.findViewById(R.id.euro_button);
        euroButton.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            padAdvancedFragmentOnClickListener = (PadAdvancedFragment.PadAdvancedFragmentOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement PadAdvancedFragment.PadAdvancedFragmentOnClickListener");
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.arrow_pad){
            padAdvancedFragmentOnClickListener.padAdvanceArrowButtonOnClickListener();
        } else if(view.getId() == R.id.dollar_button || view.getId() == R.id.euro_button) {
           displayViewModel.setCurrencyInExpression(((Button) view).getText().toString());
        } else {
            displayViewModel.setBracketInExpression(((Button) view).getText().toString());

        }
    }
}

