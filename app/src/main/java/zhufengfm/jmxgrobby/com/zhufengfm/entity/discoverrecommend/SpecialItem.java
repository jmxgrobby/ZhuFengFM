package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.Parsable;

/**
 * Created by Administrator on 2015/10/22.
 */

/**
 * 一个精品听单的数据实体
 */
public class SpecialItem implements Parsable{

    private int columnType ;
    private long specialId ;
    private String title ;

    private String subtitle ;
    private String footnote ;
    private String coverPath ;
    private String contentType ;


    @Override
    public void parseJson(JSONObject jsonObject) throws JSONException {
//        "columnType": 1,
//        "specialId": 347,
//        "title": "6个节目，触碰科幻电影中的外太空",
//        "subtitle": "每次太空探测任务都会唤起我们每个人对太空的憧憬，幸运的是，我们还可以跟着电影，将视野放大放远，去体验未来和宇宙的各种可能性",
//        "footnote": "6首声音",
//        "coverPath": "http://fdfs.xmcdn.com/group15/M09/4E/89/wKgDaFWyIP3TylAHAAGdBMZjWiQ689_mobile_small.jpg",
//        "contentType": "2"

        if (jsonObject != null) {

            columnType = jsonObject.optInt("columnType",0) ;

            specialId = jsonObject.getLong("specialId") ;

            title = jsonObject.getString("title") ;

            subtitle = jsonObject.getString("subtitle") ;

            footnote = jsonObject.getString("footnote") ;

            coverPath = jsonObject.getString("coverPath") ;

            contentType = jsonObject.getString("contentType") ;

        }

    }


}

