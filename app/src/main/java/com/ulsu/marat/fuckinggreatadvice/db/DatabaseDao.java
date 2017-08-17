package com.ulsu.marat.fuckinggreatadvice.db;

import android.content.Context;

import com.ulsu.marat.fuckinggreatadvice.db.modelSpec.AdviceModel;
import com.yahoo.squidb.android.AndroidOpenHelper;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

/**
 * Created by Marat on 17.07.2017.
 */

public class DatabaseDao extends SquidDatabase {

    private final static int VERSION = 1;
    private Context context;
    private static  DatabaseDao instance;

    private DatabaseDao(Context context) {
        super();
        this.context = context;
    }

    public static DatabaseDao getDBInstance(Context context){
        if(instance == null){
            return instance = new DatabaseDao(context);
        }
        return instance;
    }

    @Override
    public String getName() {
        return "advice_database.db";
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                AdviceModel.TABLE
        };
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return true;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return new AndroidOpenHelper(context,databaseName,delegate,version);
    }
}
