package zhufengfm.jmxgrobby.com.zhufengfm.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public class DiscoverCategory {
    /**
     * id : 3
     * name : book
     * title : 有声书
     * isChecked : false
     * orderNum : 1
     * coverPath : http://fdfs.xmcdn.com/group8/M07/17/A0/wKgDYFVxM-fQsucFAAAFRHjovdg062.png
     * selectedSwitch : false
     * isFinished : true
     * contentType : album
     */

    private int id;
    private String name;
    private String title;
    private boolean isChecked;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean isFinished;
    private String contentType;

    public void parseJSON(JSONObject json){
        if (json != null) {
            try {
                id = json.getInt("id");
                name = json.getString("name");
                title  = json.getString("title");
                orderNum = json.getInt("orderNum");
                // TODO 考虑可选还是必选

                isChecked =  json.optBoolean("isChecked");
                coverPath = json.optString("coverPath");
                selectedSwitch = json.optBoolean("selectedSwitch");
                isFinished = json.optBoolean("isFinished");
                contentType = json.optString("contentType");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
            throw new NullPointerException("json is  null");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public void setSelectedSwitch(boolean selectedSwitch) {
        this.selectedSwitch = selectedSwitch;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean getSelectedSwitch() {
        return selectedSwitch;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public String getContentType() {
        return contentType;
    }
}
