package com.yusong.club.ui.clock;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.yusong.club.ui.clock.Db.RecordTable.insert;
import static com.yusong.club.ui.clock.Db.RecordTable.update;


/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/14
 * Time: 10:02
 * FIXME
 */
public class MyDataBaseHelper {
    private volatile static MyDataBaseHelper singleton;
    private final Object databaseLock = new Object();
    private MyDbOpenHelper mDbOpenHelper;
    private volatile SQLiteDatabase readableDatabase;
    private volatile SQLiteDatabase writableDatabase;

    private MyDataBaseHelper(Context context) {
        mDbOpenHelper = new MyDbOpenHelper(context);
    }

    public static MyDataBaseHelper getSingleton(Context context) {
        if (singleton == null) {
            synchronized (MyDataBaseHelper.class) {
                if (singleton == null) {
                    singleton = new MyDataBaseHelper(context);
                }
            }
        }
        return singleton;
    }

    public boolean recordNotExists(String roleid) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query(Db.RecordTable.TABLE_NAME, new String[]{Db.RecordTable.COLUMN_ROLEID}, "roleid=?",
                    new String[]{roleid}, null, null, null);
            return cursor.getCount() == 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public long insertRecord(ClockRecords mission) {
        return getWritableDatabase().insert(Db.RecordTable.TABLE_NAME, null, insert(mission));
    }

    public long updateRecord(String roleid, ClockRecords status) {
        return getWritableDatabase().update(Db.RecordTable.TABLE_NAME, update(status), "roleid=?", new String[]{roleid});
    }


    public void deleteTable() {
        getWritableDatabase().execSQL("drop table " + Db.RecordTable.TABLE_NAME);
    }

    public ClockRecords readSingleRecord(String roleid) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select * from " + Db.RecordTable.TABLE_NAME + " where roleid=?", new String[]{roleid});
            cursor.moveToFirst();
            return Db.RecordTable.read(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public void closeDataBase() {
        synchronized (databaseLock) {
            readableDatabase = null;
            writableDatabase = null;
            mDbOpenHelper.close();
        }
    }

    public ClockRecords readRecord(final String roleid) {
        Cursor cursor = null;

        cursor = getWritableDatabase().rawQuery("select * from " + Db.RecordTable.TABLE_NAME +
                " where " + "roleid=?", new String[]{roleid});
        ClockRecords clockRecords = Db.RecordTable.read(cursor);
        return clockRecords;
    }

    private SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = writableDatabase;
        if (db == null) {
            synchronized (databaseLock) {
                db = writableDatabase;
                if (db == null) {
                    db = writableDatabase = mDbOpenHelper.getWritableDatabase();
                }
            }
        }
        return db;
    }

    private SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db = readableDatabase;
        if (db == null) {
            synchronized (databaseLock) {
                db = readableDatabase;
                if (db == null) {
                    db = readableDatabase = mDbOpenHelper.getReadableDatabase();
                }
            }
        }
        return db;
    }
}