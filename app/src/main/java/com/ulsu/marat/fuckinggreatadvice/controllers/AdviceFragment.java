package com.ulsu.marat.fuckinggreatadvice.controllers;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulsu.marat.fuckinggreatadvice.R;
import com.ulsu.marat.fuckinggreatadvice.db.DBHelper;
import com.ulsu.marat.fuckinggreatadvice.db.DatabaseDao;
import com.ulsu.marat.fuckinggreatadvice.db.modelSpec.AdviceModel;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;
import com.ulsu.marat.fuckinggreatadvice.net.GetAdvice;
import com.ulsu.marat.fuckinggreatadvice.utils.Const;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EFragment(R.layout.fragment_advice)
public class AdviceFragment extends Fragment {

    @ViewById(R.id.advice_text)
    TextView mAdviceText;

    @Click(R.id.add_button)
    public void AddData(){
        addToDB(mFAdvice);
    }

    @Click(R.id.refresh_button)
    public void refreshClick() {
        Request();
    }

    @Click(R.id.card_title)
    public void click(){
        ReqFromDb();
    }

    @InstanceState
    String text;

    private FAdvice mFAdvice;
    private DatabaseDao db;

    @AfterViews
    public void bind() {
        mAdviceText.setMovementMethod(new ScrollingMovementMethod());
        db = DatabaseDao.getDBInstance(getActivity().getApplicationContext());
    }

    private void Request() {
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
                String textFix = Html.fromHtml(response.body().getText()).toString();
                mFAdvice = response.body();
                mFAdvice.setText(textFix);
                mAdviceText.setText(mFAdvice.getText());
            }

            @Override
            public void onFailure(Call<FAdvice> call, Throwable t) {
                Log.d("TAG", t.toString());
            }
        });
    }

    private void addToDB(FAdvice fAdvice){
        int flag = DBHelper.addAdviceToDB(db,fAdvice);
        if (flag == -1) {
            PrintToast("Уже есть");
        }
    }

    private void ReqFromDb(){
        List<FAdvice> allAdvices = DBHelper.getAllAdvices(db);
        for (FAdvice advice : allAdvices) {
               Log.d("DB", advice.getId() + " " + advice.getText() + "\n");
        }
    }

    private void PrintToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
