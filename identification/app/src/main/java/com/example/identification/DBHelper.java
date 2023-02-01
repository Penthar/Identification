package com.example.identification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME = "MyDBName";
    public static final String USERS_COLUMN_ID = "_id";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_NAME = "names";
    public static final String USERS_COLUMN_PASS = "passwords";
    public static final String USERS_COLUMN_AGE = "ages";
    public static final String USER_COLUMNS_MAIL = "mails";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsersTable = String.format("create table %s (%s integer primary key autoincrement, %s text, %s text, %s integer, %s text)"
                ,USERS_TABLE_NAME, USERS_COLUMN_ID, USERS_COLUMN_NAME,USERS_COLUMN_PASS,USERS_COLUMN_AGE,USER_COLUMNS_MAIL);
        sqLiteDatabase.execSQL(createUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean checkNameTaken(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] namecol = new String [1];
        namecol[0] = USERS_COLUMN_NAME;
        Cursor cursor = db.query(USERS_TABLE_NAME, namecol, null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(name.equals(cursor.getString((int)cursor.getColumnIndex(USERS_COLUMN_NAME))))
                return false;
            cursor.moveToNext();
        }
        return true;
    }
    public User getUserFromName(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] table_columns = new String [4];
        table_columns[0] = USERS_COLUMN_NAME;
        table_columns[1] = USERS_COLUMN_PASS;
        table_columns[2] = USERS_COLUMN_AGE;
        table_columns[3] = USER_COLUMNS_MAIL;
        Cursor cursor = db.query(USERS_TABLE_NAME, table_columns, null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(name.equals(cursor.getString((int)cursor.getColumnIndex(USERS_COLUMN_NAME)))){
                String password = cursor.getString((int)cursor.getColumnIndex(USERS_COLUMN_PASS));
                int age = cursor.getInt((int)cursor.getColumnIndex(USERS_COLUMN_AGE));
                String mail = cursor.getString((int)cursor.getColumnIndex(USER_COLUMNS_MAIL));
                return new User(name, password, age, mail);
                }
            cursor.moveToNext();
        }
        return null;
    }
    public void insertNewUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, user.getUsername());
        contentValues.put(USERS_COLUMN_PASS, user.getPassword());
        contentValues.put(USERS_COLUMN_AGE, user.getAge());
        contentValues.put(USER_COLUMNS_MAIL, user.getMail());
        db.insert(USERS_TABLE_NAME, null, contentValues);
        db.close();
    }
    public boolean userIdentification(String name, String password){
        SQLiteDatabase db = getReadableDatabase();
        String[] namecol = new String [2];
        namecol[0] = USERS_COLUMN_NAME;
        namecol[1] = USERS_COLUMN_PASS;
        Cursor cursor = db.query(USERS_TABLE_NAME, namecol, null,
                null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(name.equals(cursor.getString((int)cursor.getColumnIndex(USERS_COLUMN_NAME))) && password.equals(cursor.getString((int)cursor.getColumnIndex(USERS_COLUMN_PASS))))
                return true;
            cursor.moveToNext();
        }
        return false;
    }
    public void updateUser(User user, String currname){
        SQLiteDatabase db = getWritableDatabase();
        String where = USERS_COLUMN_NAME + " = " + "'" +currname+ "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, user.getUsername());
        contentValues.put(USERS_COLUMN_PASS, user.getPassword());
        contentValues.put(USERS_COLUMN_AGE, user.getAge());
        contentValues.put(USER_COLUMNS_MAIL, user.getMail());
        db.update(USERS_TABLE_NAME, contentValues, where, null);
    }
    public Cursor getNamesAndAges(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",  USERS_TABLE_NAME), null);
        return cursor;
    }
}
