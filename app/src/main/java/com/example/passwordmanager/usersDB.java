package com.example.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class usersDB {
    //Database, zwei Tables und Version
    private final static String DATABASE_NAME = "usersDB";
    private final static String USERS_TABLE = "usersTable";
    private final static String PASSWORDS_TABLE = "passwordsTable";
    private final int DATABASE_VERSION = 1;

    //Table für Login
    public final static String row_id = "_id";
    public final static String row_email = "_email";
    public final static String row_password = "_password";

    //Table für Passwörter
    public final static String password_row_id = "_id";
    public final static String password_row_userid = "_userid";
    public final static String password_row_username = "_username";
    public final static String password_row_password = "_password";
    public final static String password_row_url = "_url";

    public Context context;
    private DBhelper myHelper;

    private SQLiteDatabase mydb;

    public usersDB(Context con) {

        context = con;
    }

    private class DBhelper extends SQLiteOpenHelper {

        public DBhelper(Context con) {

            super(con, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            String userTableQuery = "CREATE TABLE " + USERS_TABLE + "(" +
                    row_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    row_email + " TEXT NOT NULL, " +
                    row_password + " TEXT NOT NULL);";

            db.execSQL(userTableQuery);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void open() {
        myHelper = new DBhelper(context);
        mydb = myHelper.getWritableDatabase();
    }

    public void close() {

        myHelper.close();
    }
    public void addNewUser(String username, String pass) {
        ContentValues values = new ContentValues();
        values.put(row_email, username);
        values.put(row_password, pass);

        mydb.insert(USERS_TABLE, null, values);
    }

    public boolean verifyUser(String username, String password) {
        String[] columns = {row_id};

        // Define the selection criteria
        String selection = row_email + "=? AND " + row_password + "=?";
        String[] selectionArgs = {username, password};

        // Execute the query
        Cursor cursor = mydb.query(USERS_TABLE, columns, selection, selectionArgs, null, null, null);

        // Check if the query returned any rows
        boolean matchFound = cursor.getCount() > 0;

        cursor.close();

        return matchFound;
    }

    public boolean usernameExists(String uname) {
        String[] columns = {row_id};

        // Define the selection criteria
        String selection = row_email + "=?";
        String[] selectionArgs = {uname};

        // Execute the query
        Cursor cursor = mydb.query(USERS_TABLE, columns, selection, selectionArgs, null, null, null);

        boolean usernameExists = cursor.getCount() > 0;

        cursor.close();

        return usernameExists;
    }
}
