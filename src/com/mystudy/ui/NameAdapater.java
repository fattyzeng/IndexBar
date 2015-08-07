package com.mystudy.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * this is a adapter for inflating listview for descripting people's info
 * 
 * @author Administrator
 * 
 */
public class NameAdapater extends BaseAdapter {

	/**
	 * this is a data list of persons
	 */
	private List<Person> persons;
	/**
	 * this is a context that handle inflating view
	 */
	private Context mContext;

	/**
	 * this is contructor that initialize data
	 */
	public NameAdapater(List<Person> persons, Context mContext) {
		super();
		this.persons = persons;
		this.mContext = mContext;
	}

	/**
	 * this function that get list's count
	 */
	@Override
	public int getCount() {
		return this.persons.size();
	}

	/**
	 * this function that get the position's data
	 */
	@Override
	public Object getItem(int position) {
		return this.persons.get(position);
	}

	/**
	 * the function that get the position
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	// this is a temp String that store person's pinyin be used for difference
	// on pinyin
	String nameString = "";

	/**
	 * the function that get the item's view
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// the instance of viewholder
		ViewHolder holder = null;
		if (convertView == null) {
			// new viewHolder
			holder = new ViewHolder();
			// inflater the view
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.layout_item, null);
			// initial the textview of pinyin's character index
			holder.tv_index = (TextView) convertView
					.findViewById(R.id.tv_index);
			// initial the textview of person's name
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
	        
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// get the data of person
		Person person = persons.get(position);
		// if the first position the tv_index set visible
		if (position == 0) {
			holder.tv_index.setVisibility(View.VISIBLE);
		}
		// if the person's pinyin is the first character group by pinyin the
		// textview index show otherwise hide it
		boolean isshow = false;
		if (!nameString.equals(person.getPinYin())) {
			isshow = true;
			nameString = person.getPinYin();
		}
		holder.tv_index.setVisibility(isshow ? View.VISIBLE : View.GONE);
		// set person's pinyin to tv_index
		holder.tv_index.setText(person.getPinYin());
		// set person's name to tv_name
		holder.tv_name.setText(person.getName());
		return convertView;
	}

	/**
	 * this is a inner class that descripts the item_view and include a name
	 * textview and a index textview
	 * 
	 */
	static class ViewHolder {
		/**
		 * this is a textview that descript the person's data
		 */
		TextView tv_index;
		/**
		 * this is a textview that show the person's name
		 */
		TextView tv_name;
	}

}
