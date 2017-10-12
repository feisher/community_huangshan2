package com.yusong.community.ui.clock;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/14
 * Time: 10:02
 * FIXME
 */
class Db {
    private Db() {

    }
    static final class RecordTable {
        static final String TABLE_NAME = "clock_record";
        static final String COLUMN_ROLEID = "roleid";
        static final String COLUMN_TYPE = "roletype";
        static final String COLUMN_IS_OPEN = "is_open";
        static final String COLUMN_DATE = "date";
        //编译器会自动优化为StringBuild方式,不用担心效率问题
        static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ROLEID + " TEXT PRIMARY KEY," +
                        COLUMN_TYPE + " TEXT ," +
                        COLUMN_IS_OPEN + " TEXT," +
                        COLUMN_DATE + " TEXT" +
                        ") ";

        static ContentValues insert(ClockRecords mission) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ROLEID, mission.getRoleId());
            values.put(COLUMN_TYPE, mission.getRoleType());
            values.put(COLUMN_IS_OPEN, mission.getIsOpen());
            values.put(COLUMN_DATE, mission.getDate());
            return values;
        }

        static ContentValues update(ClockRecords mission) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ROLEID, mission.getRoleId());
            values.put(COLUMN_TYPE, mission.getRoleType());
            values.put(COLUMN_IS_OPEN, mission.getIsOpen());
            values.put(COLUMN_DATE, mission.getDate());
            return values;
        }

        static ClockRecords read(Cursor cursor) {
            ClockRecords record = new ClockRecords();
            if (cursor.moveToNext()) {
                record.setRoleId(cursor.getString(cursor.getColumnIndexOrThrow(RecordTable.COLUMN_ROLEID)));
                record.setDate(cursor.getString(cursor.getColumnIndexOrThrow(RecordTable.COLUMN_DATE)));
                record.setIsOpen(cursor.getString(cursor.getColumnIndexOrThrow(RecordTable.COLUMN_IS_OPEN)));
                record.setRoleType(cursor.getString(cursor.getColumnIndexOrThrow(RecordTable.COLUMN_TYPE)));
            }
            return record;
        }
    }
}
