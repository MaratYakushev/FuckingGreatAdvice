package com.ulsu.marat.fuckinggreatadvice.controllers;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ulsu.marat.fuckinggreatadvice.R;
import com.ulsu.marat.fuckinggreatadvice.adapters.FavoritesAdviceAdapter;
import com.ulsu.marat.fuckinggreatadvice.db.DBHelper;
import com.ulsu.marat.fuckinggreatadvice.db.DatabaseDao;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_advice_favorites)
public class FavoritesAdviceFragment extends Fragment {

    @ViewById(R.id.recycler_view_list)
    RecyclerView mRecyclerView;

    FavoritesAdviceAdapter adviceAdapter;

    @AfterViews
    public void bindViews() {
        DatabaseDao db = DatabaseDao.getDBInstance(getActivity());
        List<FAdvice> list = DBHelper.getAllAdvices(db);
        adviceAdapter = new FavoritesAdviceAdapter(getActivity(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adviceAdapter);
    }

    public void UpdateList(FAdvice fAdvice){
        adviceAdapter.addElement(fAdvice);
        adviceAdapter.notifyDataSetChanged();
        Log.d("Tag", "UpdateList");
    }
}
