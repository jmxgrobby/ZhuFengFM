package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

//小编推荐 热门推荐
public class DiscoverRecommendAlbums extends DiscoverRecommenItem {
	private int ret;
	private List<AlbumRecommend> list;

	public List<AlbumRecommend> getList() {
		return list;
	}

	public void setList(List<AlbumRecommend> list) {
		this.list = list;
	}

	//调用父类的解析，解析父类需要使用的数据
	//因为super中包含了title 和hasmore
	public void parseJSON(JSONObject jsonObject){
		super.parseJSON(jsonObject);
		try {
			ret = jsonObject.optInt("ret");
			JSONArray array = jsonObject.getJSONArray("list");
			int length = array.length();

			if(length>0){
				list = new LinkedList<>();
				for (int i = 0; i < length; i++) {
					JSONObject ob = array.getJSONObject(i);
					AlbumRecommend albumRecommend = new AlbumRecommend();
					albumRecommend.parseJSON(ob);
					list.add(albumRecommend);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}