package com.example.project.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.Entity.Result;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private final static String databaseName = "MovieDB";
    private final static int databaseVersion = 1;
    private String MOVIE_TABLE = "movies";
    private static DB dbInstance = null;


    private DB(Context context)
    {
        super(context,databaseName,null,databaseVersion);
    }

    public synchronized static DB getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DB(context.getApplicationContext());
        }

        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + MOVIE_TABLE + " ("
                + " imdbid INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " year INTEGER,"
                + " title TEXT,"
                + " type TEXT,"
                + " poster TEXT"
                + " )";

        sqLiteDatabase.execSQL(createQuery);
    }

    public void addNewMovies(int imdbid,String poster,String title,String type,int year)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("imdbid",imdbid);
        contentValues.put("poster",poster);
        contentValues.put("year",year);
        contentValues.put("title",title);
        contentValues.put("type",type);

        sqLiteDatabase.insert(MOVIE_TABLE,null,contentValues);

        sqLiteDatabase.close();
    }

    public ArrayList<Result> getWMovieList()
    {
        ArrayList<Result> movieArrayList = new ArrayList<Result>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(MOVIE_TABLE,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {

            }
            while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return movieArrayList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("ALTER TABLE "+ PERSON_TABLE + " ADD COLUMN age INTEGER");
    }

}