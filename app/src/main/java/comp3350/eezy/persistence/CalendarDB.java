package comp3350.eezy.persistence;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import androidx.annotation.Nullable;

public class CalendarDB extends SQLiteOpenHelper {

    public CalendarDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
