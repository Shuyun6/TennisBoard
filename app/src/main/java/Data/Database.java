package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Shuyun on 2/18/2016/018.
 */
public class Database {
    static final String IMAGE = "image";
    static final String NAME = "name";
    static final String SEX = "sex";
    static final String BIRTH = "birth";
    static final String EMAIL = "email";
    static final String PHONE = "phone";
    static final String WEIGHT = "weight";
    static final String HEIGHT = "height";

    static final String DATABASE_NAME = "tennisboard";
    static final String MALE_TABLE = "maleplayer";
    static final String FEMALE_TABLE = "femaleplayer";
    static final int DATABASE_VERSION = 1;
    static final String MALETABLE_CREATE = "create table maleplayer (" +
            "image text, " +
            "name text primary key, " +
            "sex text, " +
            "birth text, " +
            "email text, phone text, weight text, height text );";
    static final String FEMALETABLE_CREATE = "create table femaleplayer (" +
            "image text, " +
            "name text primary key, " +
            "sex text, " +
            "birth text, " +
            "email text, phone text, weight text, height text );";
    Context context;
    DatabaseHelper maleDatabaseHelper;
    SQLiteDatabase db;

    public Database(Context context) {
        this.context = context;
        maleDatabaseHelper = new DatabaseHelper(context);
    }

    public Database open() throws SQLException {
        db = maleDatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        maleDatabaseHelper.close();
    }

    public long insertPlayer(Player player) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE, player.image);
        contentValues.put(NAME, player.name);
        contentValues.put(SEX, player.sex);
        contentValues.put(BIRTH, player.birth);
        contentValues.put(EMAIL, player.email);
        contentValues.put(PHONE, player.phone);
        contentValues.put(WEIGHT, player.weight);
        contentValues.put(HEIGHT, player.height);
        if (player.sex.equals("Male"))
            return db.insert(MALE_TABLE, null, contentValues);
        else
            return db.insert(FEMALE_TABLE, null, contentValues);
    }

    public Cursor getAllPlayers(int sex) {
        if (sex == 1)  //"1" equals to Male
            return db.query(MALE_TABLE, new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE, WEIGHT, HEIGHT},
                    null, null, null, null, NAME);
        else
            return db.query(FEMALE_TABLE, new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE, WEIGHT, HEIGHT},
                    null, null, null, null, NAME);

    }

    public Cursor getPlayer(String name, String sex) throws SQLException {
//        Cursor cursor=db.query(true, DATABASE_TABLE, new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE, WEIGHT, HEIGHT},
//                NAME+"="+name, null, null, null, null, null);
        Cursor cursor;
        if (sex.equals("Male")) {
            cursor = db.query("maleplayer", new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE,
                    WEIGHT, HEIGHT}, "name like ?", new String[]{name}, null, null, null, null);
        } else {
            cursor = db.query("femaleplayer", new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE,
                    WEIGHT, HEIGHT}, "name like ?", new String[]{name}, null, null, null, null);
        }
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public boolean ifExistPlayer(String name, String sex) throws SQLException {
        Cursor cursor;
        if (sex.equals("Male")) {
            cursor = db.query("maleplayer", new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE,
                    WEIGHT, HEIGHT}, "name like ?", new String[]{name}, null, null, null, null);
        } else {
            cursor = db.query("femaleplayer", new String[]{IMAGE, NAME, SEX, BIRTH, EMAIL, PHONE,
                    WEIGHT, HEIGHT}, "name like ?", new String[]{name}, null, null, null, null);
        }
        return cursor.getCount() != 0;
    }

    public void deletePlayer(String name, String sex) {
        if (sex.equals("Male"))
            db.delete(MALE_TABLE, "name=?", new String[]{name});
        else
            db.delete(FEMALE_TABLE, "name=?", new String[]{name});
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MALETABLE_CREATE);
            db.execSQL(FEMALETABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS player");
            onCreate(db);
        }
    }

}
