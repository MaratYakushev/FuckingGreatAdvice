package com.ulsu.marat.fuckinggreatadvice.db;

import com.ulsu.marat.fuckinggreatadvice.db.modelSpec.AdviceModel;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Delete;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static List<FAdvice> getAllAdvices(DatabaseDao db) {
        List<FAdvice> adviceList = new ArrayList<>();
        Query query = Query.select().from(AdviceModel.TABLE);
        SquidCursor<AdviceModel> adviceCursor = db.query(AdviceModel.class, query);
        try {
            AdviceModel adviceModel = new AdviceModel();
            while (adviceCursor.moveToNext()) {
                adviceModel.readPropertiesFromCursor(adviceCursor);
                FAdvice advice = new FAdvice();
                advice.setId(adviceModel.getAdviceId().toString());
                advice.setText(adviceModel.getText());
                advice.setSound(adviceModel.getSound());
                adviceList.add(advice);
            }
        } finally {
            adviceCursor.close();
        }
        return adviceList;
    }

    public static int addAdviceToDB(DatabaseDao db, FAdvice fAdvice) {
        AdviceModel adviceModel = new AdviceModel();
        int count = db.query(AdviceModel.class, Query.select().from(AdviceModel.TABLE).
                where(AdviceModel.ADVICE_ID.eq(Integer.parseInt(fAdvice.getId())))).getCount();
        if (count > 0) {
            return -1;
        }
        adviceModel.setAdviceId(Integer.parseInt(fAdvice.getId()));
        adviceModel.setText(fAdvice.getText());
        adviceModel.setSound(fAdvice.getSound());
        return db.persist(adviceModel) ? 1 : 0;
    }

    public static int deleteDataById(DatabaseDao db, int id){
        Delete delete = Delete.from(AdviceModel.TABLE).where(AdviceModel.ADVICE_ID.eq(id));
        return db.delete(delete);
    }
}
