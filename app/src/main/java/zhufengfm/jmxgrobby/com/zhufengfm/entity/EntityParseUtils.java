package zhufengfm.jmxgrobby.com.zhufengfm.entity;

import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.AlbumEntity;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.AlbumItem;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.TrackEntity;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */

/**
 * 一切实体的list解析生成类
 */
public class EntityParseUtils {
    public EntityParseUtils() {
    }

    /**
     * 解析发现类型
     * @param jsonObject
     * @return
     */
    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject jsonObject){
        List<DiscoverCategory> ret = null;

        if (jsonObject != null) {
            try {
                int code = jsonObject.getInt("ret");
                if(code== Configs.TASK_RESULT_OK){//获取json成功
                    ret = new LinkedList<>();
                    JSONArray list = jsonObject.getJSONArray("list");
                    if(list.length()>0){
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject object = list.getJSONObject(i);
                            DiscoverCategory category = new DiscoverCategory();
                            //利用实体类内部实现json解析，外部的代码，
                            //调用方便简洁
                            category.parseJSON(object);
                            ret.add(category);
                        }
                    }
                }else{

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    /**
     * 解析发现推荐
     * @param jsonObject
     * @return
     */
    public static HashMap<String,Object> parseDiscoverRecomments(JSONObject jsonObject) {

        HashMap<String,Object> result = null;
        List<DiscoverRecommenItem> ret = null;
        List<DiscoverRecommendHead> headList = null;
        if (jsonObject != null) {
            try {
                int code = jsonObject.getInt("ret");
                if(code==Configs.TASK_RESULT_OK){
                    result = new HashMap<>();
                    ret = new LinkedList<>();
                    //小编推荐内容解析
                    JSONObject object = jsonObject.getJSONObject("editorRecommendAlbums");
                    DiscoverRecommendAlbums editorRecommend =
                            new DiscoverRecommendAlbums();
                    editorRecommend.parseJSON(object);
                    ret.add(editorRecommend);

                    // 解析精品听单
                    JSONObject specialColumnJson = jsonObject.getJSONObject("specialColumn");

                    SpecialRecommend discoverRecomendSpecial = new SpecialRecommend() ;

                    discoverRecomendSpecial.parseJSON(specialColumnJson);

                    ret.add(discoverRecomendSpecial) ;



                    //  解析发现新奇
                    JSONObject columnsObject = jsonObject.getJSONObject("discoveryColumns");
                    DiscoverRecommenColumns recommenColumns = new DiscoverRecommenColumns();
                    recommenColumns.parseJSON(columnsObject);
                    ret.add(recommenColumns);


                    //热门推荐内容解析
                    JSONObject hotobject = jsonObject.getJSONObject("hotRecommends");
                    MyLog.d("debug11",hotobject.toString());
                    JSONArray hotlist = hotobject.getJSONArray("list");
                    for (int i = 0; i < hotlist.length(); i++) {
                        JSONObject discoverRecommendAlbumJSON = hotlist.getJSONObject(i);
                        DiscoverRecommendAlbums e =
                                new DiscoverRecommendAlbums();
                        e.parseJSON(discoverRecommendAlbumJSON);
                        ret.add(e);
                    }

                    result.put("list",ret);

                    //解析头部
                    JSONObject headObject = jsonObject.getJSONObject("focusImages");
                    JSONArray  headArray = headObject.getJSONArray("list");
                    headList = new LinkedList<>();
                    for (int i = 0; i < headArray.length(); i++) {
                        JSONObject headObj = headArray.getJSONObject(i);
                        DiscoverRecommendHead discoverRecommendHead =
                                new DiscoverRecommendHead();
                        discoverRecommendHead.parseJSON(headObj);
                        headList.add(discoverRecommendHead);
                    }
                    result.put("head",headList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 解析专辑
     */
    public static List<AlbumItem> parseAlbumItem(JSONObject jsonObject){
        List<AlbumItem> ret = null;
        if(jsonObject!=null){
            try {
                int code = jsonObject.getInt("ret");
                if (code == Configs.TASK_RESULT_OK) {
                    MyLog.d("debug111","code正确");
                    ret = new LinkedList<>();
                    //解析 正在播放的曲目
                    JSONObject albumEntity = jsonObject.getJSONObject("album");
                    AlbumEntity album = new AlbumEntity();
                    album.parseJSON(albumEntity);
                    ret.add(album);

                    //解析 专辑
                    JSONObject obj = jsonObject.getJSONObject("tracks");
                    JSONArray array = obj.getJSONArray("list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject trackObj = array.getJSONObject(i);
                        TrackEntity trackEntity = new TrackEntity();
                        trackEntity.parseJSON(trackObj);
                        ret.add(trackEntity);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        MyLog.d("debug111",ret.size()+"长度");
        return ret;
    }
}
