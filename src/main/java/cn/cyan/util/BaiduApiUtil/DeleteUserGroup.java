package cn.cyan.util.BaiduApiUtil;

import cn.cyan.facedao.FacebaiduDeal;
import cn.cyan.util.GsonUtils;
import cn.cyan.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cyan
 * @Date: 2019/5/19 15:50
 */
public class DeleteUserGroup {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String groupDelete() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", "group_repeat");

            String param = GsonUtils.toJson(map);

            FacebaiduDeal facebaiduDeal = new FacebaiduDeal();
            String accessToken = facebaiduDeal.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DeleteUserGroup.groupDelete();
    }
}
