package zhufengfm.jmxgrobby.com.zhufengfm.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbsAdapter<T> extends BaseAdapter {
	private int layoutId;
	private Context context;
	private List<T> list;
	public void addData(List<T> list){
		if(this.list==null)
			this.list = new ArrayList<T>();
		if(list!=null)
			this.list.addAll(list);
	}
	public  void clear(){
		this.list.clear();
	}
	public AbsAdapter(int layoutId, Context context, List<T> list) {
		super();
		this.layoutId = layoutId;
		this.context = context;
		this.list = list;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null)
		{
			convertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
			viewHolder= new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (AbsAdapter<T>.ViewHolder) convertView.getTag();
		}
		bindView(viewHolder,list.get(position));
		return convertView;
	}
	public abstract void bindView(ViewHolder vHolder,T data);
	public class ViewHolder{
		private Map<Integer, View> cacheView;
		private View itemView;
		public ViewHolder(View itemView) {
			super();
			this.itemView = itemView;
			cacheView = new HashMap<Integer, View>();
		}
		//查找指定id的子控件
		public View getView(int id){
			View v = cacheView.get(id);
			if(v==null)
			{
			v = itemView.findViewById(id);	
			if(v!=null)
				cacheView.put(id, v);
			}
			return v;
		}
	}
}
