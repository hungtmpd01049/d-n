package edu.fpoly.edict.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.fpoly.edict.R;
import edu.fpoly.edict.data.WordDataAdapter;
import edu.fpoly.edict.data.table.WordTable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class WordList2Adapter extends BaseAdapter {
	ArrayList<WordListItem> mList;
	Context mContext;
	
	public WordList2Adapter(Context context) {
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
				item.setWordImage(cursor.getString(cursor.getColumnIndex(WordTable.WORD_IMAGE)));
				
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
			
			convertView = inflater.inflate(R.layout.word_list2_item, null);
			TextView tvKey = (TextView) convertView.findViewById(R.id.tvWordKey);
			TextView tvMeaning = (TextView) convertView.findViewById(R.id.tvWordMeaning);
			ImageView iv = (ImageView) convertView.findViewById(R.id.ivWordImage);
			
			tvKey.setText(mList.get(position).getWordKey());
			tvMeaning.setText(mList.get(position).getWordBrief());
			
			if (mList.get(position).getWordImage() != null){
				InputStream is = null;
				try {
					is = mContext.getAssets().open("images/"+mList.get(position).getWordImage());
					BitmapFactory.Options options = new BitmapFactory.Options();

					Bitmap bit = BitmapFactory.decodeStream(is);
					iv.setImageBitmap(bit);
				} catch (IOException e) {}
				finally{
					try {
						is.close();
					} catch (IOException e) {}
				}
				
			}else{
				iv.setImageResource(R.drawable.ic_launcher);
			}
		}
		
		return convertView;
	}

}
