package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverRecommenItem {
	private String title;
	private boolean hasMore;

	public void parseJSON(JSONObject jsonObject) {
		if (jsonObject != null) {
			try {
				title = jsonObject.getString("title");
				hasMore = jsonObject.getBoolean("hasMore");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
}