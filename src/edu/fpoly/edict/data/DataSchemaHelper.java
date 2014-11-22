package edu.fpoly.edict.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.fpoly.edict.data.table.WordTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataSchemaHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "edictionary_db";
	private static final int DATABASE_VERSION = 1;

	DataSchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("Hello", WordTable.SQL_CREATE_TABLE);
		db.execSQL(WordTable.SQL_CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL(WordTable.SQL_DROP_TABLE);
		onCreate(db);
	}
}
