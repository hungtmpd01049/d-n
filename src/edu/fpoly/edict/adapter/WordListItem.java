package edu.fpoly.edict.adapter;

import java.io.Serializable;


public class WordListItem implements Serializable {
	int id;
	String wordKey;
	String wordBrief;
	private String wordImage;
	
	public WordListItem() {

	}
	public WordListItem(int id, String wordKey, String wordBrief) {
		this.wordKey = wordKey;
		this.wordBrief = wordBrief;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getWordKey() {
		return wordKey;
	}
	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}
	public String getWordBrief() {
		return wordBrief;
	}
	public void setWordBrief(String wordBrief) {
		this.wordBrief = wordBrief;
	}
	public String getWordImage() {
		return wordImage;
	}
	public void setWordImage(String wordImage) {
		this.wordImage = wordImage;
	}
}
