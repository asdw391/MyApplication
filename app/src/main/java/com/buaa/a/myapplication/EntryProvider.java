package com.buaa.a.myapplication;

import java.util.HashMap;



import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;


public class EntryProvider extends ContentProvider  {
    static final String PROVIDER_NAME = "com.buaa.a.myapplication";
    static final String URL = "content://" + PROVIDER_NAME + "/entry";
    static final Uri CONTENT_URI = Uri.parse(URL);


    //数据库内容
    static final String _ID = "_id";
    static final String DATE = "date";
    static final String MONEY = "money";
    static final String REMARKS= "remarks";
    static final String TYPE = "type";

    //SQLiteQueryBuilder类setProjectionMap方法 需要用到
    private static HashMap<String, String> ENTRY_PROJECTION_MAP;

    //设置matcher
    static final int ENTRY = 1;
    static final int ENTRY_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "entry", ENTRY);
        uriMatcher.addURI(PROVIDER_NAME, "entry/#", ENTRY_ID);
    }


    //数据库基础设置
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "MY_ENTRY";
    static final String ENTRY_TABLE_NAME = "entries";
    static final int DATABASE_VERSION = 1;//母鸡呀

    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + ENTRY_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " date  varchar NOT NULL, " +
                    " money  varchar NOT NULL, " +
                    " remarks  varchar NOT NULL, " +
                    " type  varchar NOT NULL);";

    /**
     * 创建和管理提供者内部数据源的帮助类.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_DB_TABLE);
        }


        //重建
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  ENTRY_TABLE_NAME);
            onCreate(db);
        }
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();

        //实例化DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * 如果不存在，则创建一个可写的数据库。
         */
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * 添加条目记录
         */
        long rowID = db.insert( ENTRY_TABLE_NAME, "", values);

        /**
         * 如果记录添加成功
         */

        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);//告知所以调用者
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case ENTRY:
                count = db.delete(ENTRY_TABLE_NAME, selection, selectionArgs);
                break;

            case ENTRY_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(ENTRY_TABLE_NAME, _ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case ENTRY:
                count = db.update(ENTRY_TABLE_NAME, values, selection, selectionArgs);
                break;

            case ENTRY_ID:
                count = db.update(ENTRY_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * 获取所有学生记录
             */
            case ENTRY:
                return "entries";

            /**
             * 获取一个特定的条目
             */
            case ENTRY_ID:
                return "entry";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(ENTRY_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case ENTRY:
                qb.setProjectionMap(ENTRY_PROJECTION_MAP);
                break;

            case ENTRY_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * 默认按照日期排序
             */
            sortOrder = DATE;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs,null, null, sortOrder);

        /**
         * 注册内容URI变化的监听器
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
}
