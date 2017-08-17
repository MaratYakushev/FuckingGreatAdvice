package com.ulsu.marat.fuckinggreatadvice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ulsu.marat.fuckinggreatadvice.R;
import com.ulsu.marat.fuckinggreatadvice.db.DBHelper;
import com.ulsu.marat.fuckinggreatadvice.db.DatabaseDao;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;

import java.util.List;

/**
 * Created by Marat on 17.07.2017.
 */

public class FavoritesAdviceAdapter extends RecyclerView.Adapter<FavoritesAdviceAdapter.ViewHolder> {



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView favoritesAdvice;
        Button deleteAdvice;


        public ViewHolder(View itemView) {
            super(itemView);
            favoritesAdvice = (TextView) itemView.findViewById(R.id.favorite_advice_text);
            deleteAdvice = (Button) itemView.findViewById(R.id.delete_favorites_advice);
        }
    }

    private Context context;
    private List<FAdvice> list;

    public FavoritesAdviceAdapter(Context context, List<FAdvice> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorites_advice,parent,false);
        return new ViewHolder(view);
    }

    public void addElement(FAdvice fAdvice){
        list.add(fAdvice);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FAdvice advice = list.get(position);
        holder.favoritesAdvice.setText(advice.getText());
        holder.deleteAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.deleteDataById(DatabaseDao.getDBInstance(context.getApplicationContext()),Integer.parseInt(list.get(position).getId()));
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
