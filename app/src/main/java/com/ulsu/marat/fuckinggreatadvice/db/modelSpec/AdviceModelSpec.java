package com.ulsu.marat.fuckinggreatadvice.db.modelSpec;

import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "AdviceModel", tableName = "AdviceTable")
public class AdviceModelSpec {
    private int adviceId;
    private String text;
    private String sound;
}
