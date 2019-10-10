package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES_USERS =
                "CREATE TABLE " + UserMaster.User.TABLE_NAME + " (" +
                        UserMaster.User._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.User.COLUMN_NAME + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PASSWORD + " TEXT," +
                        UserMaster.User.COLUMN_NAME_TYPE + " TEXT)";

        String SQL_CREATE_ENTRIES_MESSAGE =
                "CREATE TABLE " + UserMaster.Message.TABLE_NAME + " (" +
                        UserMaster.Message._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Message.COLUMN_NAME_USER + " TEXT," +
                        UserMaster.Message.COLUMN_NAME_SUBJECT + " TEXT," +
                        UserMaster.Message.COLUMN_NAME_MESSAGE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_USERS);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserMaster.User.TABLE_NAME;

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public boolean addInfoUser(String Username, String Password, String Type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.User.COLUMN_NAME, Username);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD, Password);
        values.put(UserMaster.User.COLUMN_NAME_TYPE, Type);

        long newRowId = db.insert(UserMaster.User.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addInfoMessage(String Username, String Subject, String Message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.Message.COLUMN_NAME_USER, Username);
        values.put(UserMaster.Message.COLUMN_NAME_SUBJECT, Subject);
        values.put(UserMaster.Message.COLUMN_NAME_MESSAGE, Message);

        long newRowId = db.insert(UserMaster.Message.TABLE_NAME, null, values);
        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {UserMaster.User._ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = UserMaster.User.COLUMN_NAME + "=?" + " and " + UserMaster.User.COLUMN_NAME_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UserMaster.User.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public Cursor getAllmessage() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + UserMaster.Message.TABLE_NAME, null);
        return res;
    }

    public Cursor getmessageID(String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + UserMaster.Message._ID + " FROM " + UserMaster.Message.TABLE_NAME+
                " WHERE " + UserMaster.Message.COLUMN_NAME_SUBJECT + " = '" + subject + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
