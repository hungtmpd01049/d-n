package edu.fpoly.edict.adapter;

import java.util.ArrayList;

import edu.fpoly.edict.R;
import edu.fpoly.edict.data.WordDataAdapter;
import edu.fpoly.edict.data.table.WordTable;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class WordListAdapter extends BaseAdapter {
	ArrayList<WordListItem> mList;
	Context mContext;
	
	public WordListAdapter(Context context) {
		mList = new ArrayList<WordListItem>();
		mContext = context;
	}
	
	public void loadData(){
		WordDataAdapter adapter = new WordDataAdapter(mContext);
		
		try {
			adapter.open();
			Cursor cursor =  adapter.getAll();
			while (cursor.moveToNext()){
				WordListItem item = new WordListItem();
				item.setId(cursor.getInt(cursor.getColumnIndex(WordTable.ID)));
				item.setWordKey(cursor.getString(cursor.getColumnIndex(WordTable.WORD_KEY)));
				item.setWordBrief(cursor.getString(cursor.getColumnIndex(WordTable.WORD_MEANING)));
				
				mList.add(item);
			}	
		} catch (Exception e) {
			Toast.makeText(mContext, "There is an error.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.word_list_item, null);
			TextView tv = (TextView) convertView.findViewById(R.id.tvWordItem);
			
			tv.setText(mList.get(position).getWordKey());
		}
		
		return convertView;
	}

}
