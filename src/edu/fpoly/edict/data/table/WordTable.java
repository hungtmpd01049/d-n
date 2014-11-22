package edu.fpoly.edict.data.table;

import java.io.Serializable;

public class WordTable implements Serializable {
	public static final String SQL_CREATE_TABLE = "CREATE TABLE if not exists "
			+ WordTable.TABLE_NAME + " (" + WordTable.ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + WordTable.WORD_KEY
			+ " TEXT," + WordTable.WORD_MEANING + " TEXT);";
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
			+ WordTable.TABLE_NAME;
	public static final String ID = "_id";
	public static final String WORD_KEY = "word_key";
	public static final String WORD_MEANING = "word_meaning";
	public static final String WORD_IMAGE = "word_image";
	public static final String TABLE_NAME = "word_list";
}
