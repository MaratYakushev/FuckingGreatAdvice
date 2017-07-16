package com.ulsu.marat.fuckinggreatadvice.controllers;

import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ulsu.marat.fuckinggreatadvice.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_advice)
public class AdviceFragment extends Fragment {

    @ViewById(R.id.advice_text)
    TextView mAdviceText;

    @AfterViews
    public void bind(){
        mAdviceText.setMovementMethod(new ScrollingMovementMethod());
    }
}
