package com.example.shareknow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //member variables
    //create the variables

    //table 1 Registration Information
    final static String DATABASE_NAME = "AppDatabase.db";
    final static int DATABASE_VERSION = 2;

    final static String TABLE1_NAME = "RegistrationInfo_table";
    final static String T1COL1 = "Uid";
    final static String T1COL2 = "Email";
    final static String T1COL3 = "GName";
    final static String T1COL4 = "FName";
    final static String T1COL5 = "DateOfBirth";
    final static String T1COL6 = "Address";
    final static String T1COL7 = "Category";


    //table 2 Books
    final static String TABLE2_NAME = "Books";
    final static String T2COL1 = "Bid";
    final static String T2COL2 = "Category";
    final static String T2COL3 = "BookTitle";
    final static String T2COL4 = "Author";
    final static String T2COL5 = "Publisher";
    final static String T2COL6 = "PublishYear";
    final static String T2COL7 = "BookStatus"; // rent,share or give away
    final static String T2COL8 = "BookAvailability";   //Available or not available
    final static String T2COL9 = "UserEmail";  //shows the owner of the book by email

    //table 3 Reading Tracker
    final static String TABLE3_NAME = "ReadingTracker";
    final static String T3COL1 = "Rid";
    final static String T3COL2 = "UserName";
    final static String T3COL3 = "RTBookTitle";
    final static String T3COL4 = "RTBookAuthor";
    final static String T3COL5 = "RTBookPublisher";

    //table 4 Messages
    final static String TABLE4_NAME = "Messages_table";
    final static String T4COL1 = "Mid";
    final static String T4COL2 = "Sender";
    final static String T4COL3 = "Receiver";
    final static String T4COL4 = "Message";

    //table 4 LoginInfo
    final static String TABLE5_NAME = "LoginInfo";
    final static String T5COL1 = "LogId";
    final static String T5COL2 = "Email";
    final static String T5COL3 = "Password";

    protected DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Query to create the table 1
        String query = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL1 + " INTEGER PRIMARY KEY," + T1COL2 + " TEXT," +
                T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT, " + T1COL6 + " TEXT," + T1COL7 + " TEXT)";

        //Query to create the table 2
        String query2 = "CREATE TABLE " + TABLE2_NAME + "(" + T2COL1 + " INTEGER PRIMARY KEY," + T2COL2 + " TEXT," +
                T2COL3 + " TEXT," + T2COL4 + " TEXT," + T2COL5 + " TEXT," + T2COL6 + " TEXT," + T2COL7 + " TEXT," + T2COL8 + " TEXT," + T2COL9 + " TEXT)";

        //Query to create the table 3
        String query3 = "CREATE TABLE " + TABLE3_NAME + "(" + T3COL1 + " INTEGER PRIMARY KEY," + T3COL2 + " TEXT," +
                T3COL3 + " TEXT," + T3COL4 + " TEXT," + T3COL5 + " TEXT)";

        //Query to crate the table 4
        String query4 = "CREATE TABLE " + TABLE4_NAME + "(" + T4COL1 + " INTEGER PRIMARY KEY," + T4COL2 + " TEXT," + T4COL3 + " TEXT," + T4COL4 +
                " TEXT)";

        //Query to crate the table 5
        String query5 = "CREATE TABLE " + TABLE5_NAME + "(" + T5COL1 + " INTEGER PRIMARY KEY," + T5COL2 + " TEXT," + T5COL3 + " TEXT)";


        //Execute the query 1
        sqLiteDatabase.execSQL(query);

        //Execute the query 2
        sqLiteDatabase.execSQL(query2);

        //Execute the query 3
        sqLiteDatabase.execSQL(query3);

        //Execute the query 4
        sqLiteDatabase.execSQL(query4);

        //Execute the query 5
        sqLiteDatabase.execSQL(query5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 > i) {
            //when version will be changed
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
            sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS " + TABLE2_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        }
        onCreate(sqLiteDatabase);
    }

    //method to add the registration information record
    public boolean addRec(String email, String gn, String fn, String dob, String address, String ri) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T1COL2, email);
        value.put(T1COL3, gn);
        value.put(T1COL4, fn);
        value.put(T1COL5, dob);
        value.put(T1COL6, address);
        value.put(T1COL7, ri);

        //method to insert the contents
        long r = sqLiteDatabase.insert(TABLE1_NAME, null, value);

        //if value is positive and greater than 0 it means values are inserted
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    // get LoggedInUser email from DB
    public Cursor getUserRec() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE5_NAME + " Order By LogID Desc Limit 1";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    // get LoggedInUser email from DB
    public Cursor getUserDataRec(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Email=\"" + email + "\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }




    //method to add the books record
    public boolean addBookRec(String bName, String bAuthor, String bPublisher, String bPYear, String bStatus, String bCategory, String Email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T2COL2, bCategory);
        value.put(T2COL3, bName);
        value.put(T2COL4, bAuthor);
        value.put(T2COL5, bPublisher);
        value.put(T2COL6, bPYear);
        value.put(T2COL7, bStatus);
        value.put(T2COL8, "Available");
        value.put(T2COL9, Email);


        //method to insert the contents
        long r = sqLiteDatabase.insert(TABLE2_NAME, null, value);

        //if value is positive and greater than 0 it means values are inserted
        if (r > 0)
            return true;
        else
            return false;
    }

    //method to update the book record
    public boolean updateUserInfoRec(String name, String loggedinUserEmail, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL3, name);
        values.put(T1COL2, loggedinUserEmail);
        values.put(T1COL6, address);

        //query to update the record in book table
        int u = sqLiteDatabase.update(TABLE1_NAME, values, T1COL2 + " = ?", new String[]{loggedinUserEmail});

        //if value is positive means record is updated else there is some error
        if (u > 0) {
            return true;
        } else {
            return false;
        }
    }


    //method to update the book record
    public boolean updateBookRec(String bName, String bAuthor, String bPublisher, String bPYear, String bStatus) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL4, bAuthor);     //updating the author name
        values.put(T2COL5, bPublisher); //updating the publisher
        values.put(T2COL6, bPYear);    //updating the publishing year
        values.put(T2COL7, bStatus);   //updating the book status

        //query to update the record in book table
        int u = sqLiteDatabase.update(TABLE2_NAME, values, "BookTitle = ?", new String[]{bName});

        //if value is positive means record is updated else there is some error
        if (u > 0) {
            return true;
        } else {
            return false;
        }
    }

    //method to retrieve the books name from the database
    public Cursor retreiveBooks() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to retrieve the books based on location
    public Cursor retrieveBooksbyLocation(String code) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME + " INNER JOIN RegistrationInfo_table ON Books.UserEmail = RegistrationInfo_table.Email  where Address=\""+ code + "\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to store the reading details in reading tracker
    public boolean readingTrackerRec(String userName, String bookName, String authorName, String publisherName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T3COL2, userName);
        value.put(T3COL3, bookName);
        value.put(T3COL4, authorName);
        value.put(T3COL5, publisherName);

        //method to insert the contents
        long r = sqLiteDatabase.insert(TABLE3_NAME, null, value);

        //if value is positive and greater than 0 it means values are inserted
        if (r > 0)
            return true;
        else
            return false;
    }

    //method to view the user data
    public Cursor viewUsers() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to delete the user based on name
    public boolean delUser(String gName) {
        //sql database object and writable access
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //if successful will return positive value
        int d = sqLiteDatabase.delete(TABLE1_NAME, "GName=?", new String[]{gName});
        if (d > 0)
            return true;
        else
            return false;
    }

    //method to delete the book based on name
    public boolean delBook(String name) {
        //sql database object and writable access
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //if successful will return positive value
        int d = sqLiteDatabase.delete(TABLE2_NAME, "BookTitle=?", new String[]{name});
        if (d > 0)
            return true;
        else
            return false;
    }

    //method to retrieve the books based on postal code
    public Cursor viewBooksByPostal(String pCode) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM RegistrationInfo_table " +
                " INNER JOIN Books ON RegistrationInfo_table.Category = Books.Category";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    //method to retrieve the reading tracker data
    public Cursor viewReadingHistory() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to add message to table 4
    public boolean addMessageRec(String sender, String receiver, String message) {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T4COL2, sender);
        value.put(T4COL3, receiver);
        value.put(T4COL4, message);
        Long r = sqlitedatabase.insert(TABLE4_NAME, null, value);
        if (r > 0)
            return true;
        else
            return false;

    }

    //method to retrieve the message table data
    public Cursor viewMessageRequests() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to delete the requests from message request table
    public boolean delReq(String name) {
        //sql database object and writable access
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //if successful will return positive value
        int d = sqLiteDatabase.delete(TABLE4_NAME, "Sender=?", new String[]{name});
        if (d > 0)
            return true;
        else
            return false;
    }

    //method to add loginInfo into table 5
    public boolean addLogInfo(String uName, String pwd) {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(T5COL2, uName);
        value.put(T5COL3, pwd);
        Long r = sqlitedatabase.insert(TABLE5_NAME, null, value);
        if (r > 0)
            return true;
        else
            return false;
    }

    //method to retreive the info from loginInfo
    public Cursor viewLoginInfo() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE5_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to update the book availability
    public boolean updateBookAvail(String bName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL8, "UnAvailable");

        //query to update the record in book table
        int u = sqLiteDatabase.update(TABLE2_NAME, values, "BookTitle = ?", new String[]{bName});

        //if value is positive means record is updated else there is some error
        if (u > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor checkUserRec(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE5_NAME + " WHERE  "+T5COL2+" = \"" + email + "\" AND " + T5COL3 + " = \"" + password + "\"";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }
}
