package com.example.mobileappdev_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "datanotes.db";
    private static final String TABLE_NAME = "notes";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDULNOTES = "judulnotes";
    private static final String COLUMN_KONTENNOTES = "kontennotes";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_NOTES = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JUDULNOTES + " VARCHAR(50) NOT NULL, " +
                COLUMN_KONTENNOTES + " VARCHAR(50) NOT NULL)";
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private SQLiteDatabase database;

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    private String[] allColumns =
            {COLUMN_ID, COLUMN_JUDULNOTES, COLUMN_KONTENNOTES};

    private Notes cursorToNotes(Cursor cursor){
        Notes notes = new Notes();

        notes.setID(cursor.getLong(0));
        notes.setJudulNotes(cursor.getString(1));
        notes.setKontenNotes(cursor.getString(2));
        return notes;
    }

    public void createNotes(String JudulNotes, String KontenNotes) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_JUDULNOTES, JudulNotes);
        values.put(COLUMN_KONTENNOTES, KontenNotes);

        database.insert(TABLE_NAME, null, values);
    }

    public Notes getNotes(Long id){
        Notes notes = new Notes();

        Cursor cursor = database.query(TABLE_NAME,allColumns,"_id="+id,null,null,null,null);
        cursor.moveToFirst();
        cursor.close();
        return notes;
    }

    public ArrayList<Notes> getAllNotes(){
        ArrayList<Notes> daftarnotes = new ArrayList<Notes>();
        Cursor cursor = database.query(TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Notes notes = cursorToNotes(cursor);
            daftarnotes.add(notes);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarnotes;
    }

    public void updateNotes(Notes notes){
        String filter = "_id="+ notes.getID();
        ContentValues args = new ContentValues();
        args.put(COLUMN_JUDULNOTES, notes.getJudulNotes());
        args.put(COLUMN_KONTENNOTES, notes.getKontenNotes());

        database.update(TABLE_NAME, args, filter, null);
    }

    public void deleteNotes(Long id){
        String filter = "_id="+id;

        database.delete(TABLE_NAME, filter, null);
    }
}
