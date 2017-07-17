package com.ulsu.marat.fuckinggreatadvice.controllers;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulsu.marat.fuckinggreatadvice.R;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;
import com.ulsu.marat.fuckinggreatadvice.net.GetAdvice;
import com.ulsu.marat.fuckinggreatadvice.utils.Const;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EFragment(R.layout.fragment_advice)
public class AdviceFragment extends Fragment {

    @ViewById(R.id.advice_text)
    TextView mAdviceText;

    @Click(R.id.refresh_button)
    public void refreshClick(){
        Request();
    }

    @AfterViews
    public void bind(){
        mAdviceText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void Request(){
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.RANDOM_ADVICE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GetAdvice getAdvice = retrofit.create(GetAdvice.class);

        Call<FAdvice> call = getAdvice.getRandomAdvice();
        call.enqueue(new Callback<FAdvice>() {
            @Override
            public void onResponse(Call<FAdvice> call, Response<FAdvice> response) {
                Log.d("TAG", response.body().getText());
                try {
                    String res = new String(response.body().getText().getBytes("UTF-8"), "UTF-8");
                    mAdviceText.setText(Html.fromHtml(res));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FAdvice> call, Throwable t) {
                Log.d("TAG",t.toString());
            }
        });
    }
}
