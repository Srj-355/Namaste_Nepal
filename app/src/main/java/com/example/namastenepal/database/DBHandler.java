package com.example.namastenepal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.namastenepal.movie.MovieResults;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Asian";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "Movie";

    // below variable is for our id column.
    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name" + " TEXT,"
                + "Description" + " TEXT,"
                + "ReleaseDate" + " TEXT,"
                + "PosterPath" + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    public ArrayList<MovieResults> getData() {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "Name",
                "ID"
        };

// Filter results WHERE "title" = 'My Title'
        String selection = "Name = ?";

        String[] selectionArgs = {"My Title"};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                "Name DESC";

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<MovieResults> resultsData = new ArrayList<MovieResults>();
        if (cursor.moveToFirst()) {
            do {
                MovieResults results = new MovieResults();
                results.setId(cursor.getInt(0));
                results.setTitle(cursor.getString(1));
                results.setOverview(cursor.getString(2));
                results.setReleaseDate(cursor.getString(3));
                results.setPosterPath(cursor.getString(4));
                resultsData.add(results);
                // on below line we are adding the data from cursor to our array list.

            } while (cursor.moveToNext());
            // moving our cursor to next.
        }


        Log.d("Data Length", String.valueOf(resultsData.size()));
        cursor.close();
        return resultsData;
    }

    public void deleteMovieData() {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " +TABLE_NAME);
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");

        // 2. Reset the auto-increment counter (works for SQLite 3.35.0+)
        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "';");
        db.close();
    }

    // this method is use to add new course to our sqlite database.
    public void addMovieData(String name, String description, String releaseDate, String posterPath) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();


        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("Name", name);
        values.put("Description", description);
        values.put("PosterPath", posterPath);
        values.put("ReleaseDate", releaseDate);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
