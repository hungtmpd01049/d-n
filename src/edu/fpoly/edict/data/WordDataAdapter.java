package edu.fpoly.edict.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import edu.fpoly.edict.data.table.WordTable;

public class WordDataAdapter {
	private DataSchemaHelper mHelper;
	private Context mContext;
	private SQLiteDatabase db;

	public WordDataAdapter(Context mContext) {
		this.mContext = mContext;

		mHelper = new DataSchemaHelper(mContext);
	}

	public void open() {
		db = mHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	public long addWord(String key, String meaning) {
		ContentValues cv = new ContentValues();
		cv.put(WordTable.WORD_KEY, key);
		cv.put(WordTable.WORD_MEANING, meaning);

		long result = db.insert(WordTable.TABLE_NAME, WordTable.WORD_KEY, cv);

		Log.d("Hello", "insert " + result);
		return result;
	}

	public long updateWord(String id, String key, String meaning) {
		ContentValues values = new ContentValues();
		values.put(WordTable.WORD_KEY, key);
		values.put(WordTable.WORD_MEANING, meaning);

		return db.update(WordTable.TABLE_NAME, values, WordTable.ID + " = "
				+ id, null);
	}

	public boolean deleteWord(int id) {
		return db.delete(WordTable.TABLE_NAME, WordTable.ID + "=" + id, null) > 0;
	}

	public Cursor getAll() {
		Cursor result = db.query(WordTable.TABLE_NAME, new String[] {
				WordTable.ID, WordTable.WORD_KEY, WordTable.WORD_MEANING,
				WordTable.WORD_IMAGE }, null, null, null, null, null);
		return result;
	}

	public Cursor getWord(int id) {
		Cursor cursor = db.query(WordTable.TABLE_NAME, new String[] {
				WordTable.ID, WordTable.WORD_KEY, WordTable.WORD_MEANING,
				WordTable.WORD_IMAGE }, WordTable.ID + "=" + id, null, null,
				null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	public Cursor getWord(String key) {
		Cursor cursor = db.query(WordTable.TABLE_NAME, new String[] {
				WordTable.ID, WordTable.WORD_KEY, WordTable.WORD_MEANING,
				WordTable.WORD_IMAGE },
				WordTable.WORD_KEY + " like '%" + key.trim() + "%'", null,
				null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	public String getWordMeaning(int id) {
		Cursor cursor = db.query(WordTable.TABLE_NAME, new String[] {
				WordTable.ID, WordTable.WORD_KEY, WordTable.WORD_MEANING },
				WordTable.ID + "=" + id, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();

			return cursor.getString(cursor
					.getColumnIndex(WordTable.WORD_MEANING));
		}
		return "";
	}
}
