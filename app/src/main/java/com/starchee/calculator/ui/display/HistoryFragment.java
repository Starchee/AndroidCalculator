package com.starchee.calculator.ui.display;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;
import com.starchee.calculator.viewModels.DisplayViewModel;
import com.starchee.calculator.viewModels.HistoryViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class HistoryFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = HistoryFragment.class.getName();

    private View rootView;
    private ImageView arrowButton;
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private HistoryViewModel historyViewModel;
    private DisplayViewModel displayViewModel;
    private  AlertDialog clearDialog;
    private HistoryFragment.HistoryFragmentOnClickListener historyFragmentOnClickListener;

    public interface HistoryFragmentOnClickListener{
       void arrowButtonOnClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history_fragment, container, false);
        arrowButton = rootView.findViewById(R.id.arrow_display);
        arrowButton.setOnClickListener(this);
        displayViewModel = new ViewModelProvider(requireActivity()).get(DisplayViewModel.class);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        historyAdapter = new HistoryAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_MOVE) {
                    recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Clear history and memory?")
                .setPositiveButton(R.string.clear, (dialogInterface, i) -> {
                    displayViewModel.clearHistory()
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableCompletableObserver() {
                                @Override
                                public void onComplete() {
                                    Toast.makeText(getContext(), "History has been cleared successfully", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e(TAG, e.toString());

                                }
                            });
                    historyFragmentOnClickListener.arrowButtonOnClickListener();
                })
                .setNegativeButton("Dismiss", (dialogInterface, i) -> {

                });

        clearDialog = builder.create();

        LiveData<List<History>> data2 = displayViewModel.getHistoryLiveData();
        data2.observe(getViewLifecycleOwner(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                historyAdapter.setHistories(histories);
            }
        });

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.history_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                historyFragmentOnClickListener.arrowButtonOnClickListener();
                return true;
            case R.id.clear:
                clearDialog.show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            historyFragmentOnClickListener = (HistoryFragment.HistoryFragmentOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implements HistoryFragment.HistoryFragmentOnClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        historyFragmentOnClickListener.arrowButtonOnClickListener();
    }
}
