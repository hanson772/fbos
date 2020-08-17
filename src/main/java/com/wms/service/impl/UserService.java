package com.wms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wms.bean.enums.ERole;
import com.wms.bean.pojo.Employee;
import com.wms.config.CacheConfig;
import com.wms.service.IUserService;
import com.wms.util.OkHttpRequestUtils;
import koal.sgpmi.client.author.ClientForAuthor;
import koal.sgpmi.client.bean.ReturnAuthorBean;
import koal.sgpmi.client.constant.AuthorXMLTag;
import koal.sgpmi.client.ws.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService implements IUserService {
    //4A单点登录SLB
    @Value("${project.ssourl}")
    String ssoUrl;
    //4A统一授权SLB
    @Value("${project.authurl}")
    String authurl;
    //4A统一人员机构SLB
    @Value("${project.orgurl}")
    String orgurl;
    //4A统一人员机构SLB
    @Value("${project.weburl}")
    String weburl;
    //appcode
    @Value("${project.appcode}")
    String appcode;
    //#4A统一授权用户名
    @Value("${project.username4A}")
    String username4A;
    //#4A统一授权密码
    @Value("${project.password4A}")
    String password4A;

    public final String User_Header = "x-token";

    @Autowired
    CacheConfig cacheConfig;

    /**
     * 使用ticket登录
     * @param ticket
     */
    @Override
    public String loginByTicket(String ticket) {
        Assert.isTrue(StringUtils.hasText(ticket), "ticket不能为空");
        Employee user = checkTicketFromSSO(ticket);
        ERole[] roles = getAllRolesByUserCode(user.getUserId());
        user.setRoles(roles);
        // 缓存用户
        cacheConfig.put(ticket, user);
        return ticket;
    }


    /**
     * 根据请求获取当前登录人的身份信息ß
     *
     * @param request
     * @return
     */
    @Override
    public Employee getUserFromRequest(HttpServletRequest request) {
        String token = request != null ? request.getHeader(User_Header) : "";
        if(StringUtils.hasText(token)){
            return null;
        }

        Employee e = cacheConfig.get(token);
        return e;
        /*
        String role = request != null ? request.getHeader(User_Header) : "";
        role = StringUtils.hasText(role) ? role.toLowerCase() : "";
        ERole[] roles = null;
        switch (role) {
            case "gm":
                roles = new ERole[]{ERole.GM};
                break;
            case "bamj":
                roles = new ERole[]{ERole.BAMJ};
                break;
            case "zgy":
                roles = new ERole[]{ERole.ZGY};
                break;
            case "bgy":
                roles = new ERole[]{ERole.BGY};
                break;
            case "cxyh":
                roles = new ERole[]{ERole.CXYH};
                break;
            default:
                break;
        }
        if (roles == null || roles.length == 0) {
            return null;
        }
        return Employee.builder()
                .userId("x0001")
                .name("张无忌")
                .email("wuji.zhang@shga.com")
                .org("上海市公安局")
                .dept("档案科")
                .job("民警")
                .roles(roles)
                .build();

         */
    }


    /**
     * 校验sso-ticket
     *
     * @param ticket
     * @return
     */
    private Employee checkTicketFromSSO(String ticket) {
        try {
            Map<String, Object> query = new HashMap<>();
            query.put("ticket", ticket);
            query.put("service", weburl);
            query.put("format", "json");
            String response = OkHttpRequestUtils.doGet(ssoUrl + "/p3/serviceValidate", query);
            JSONObject json = JSONObject.parseObject(response);
            if (json != null) {
                String user = json.getJSONObject("serviceResponse")
                        .getJSONObject("authenticationSuccess")
                        .getString("user");
                String[] userstr = user.split(" ");
                // 返回一个Easy对象，key是用户姓名，value是用户code（身份证）
                Assert.isTrue(userstr.length > 0, "登录失败，没有证件号");
                return Employee.builder()
                        .name(userstr[0])
                        .userId(userstr[1])
                        .build();
            }
        } catch (Exception se) {
            log.error(se.getMessage(), se);
            Assert.isTrue(false, "ticket验证失败，" + se.getMessage());
        }
        return null;
    }


    /**
     * 获取用户的所有角色信息
     * @param userCode
     * @return
     */
    private ERole[] getAllRolesByUserCode(String userCode) {
        ClientForAuthor client = getAuthorClient();
        //String user_Code = "32032219921217621X";

        List<ERole> roles = new ArrayList<>();
        try {
            // 调用统一授权，获取当前usercode所属的所有角色列表
            ReturnAuthorBean<List<Map<String, Object>>> resOneData = client.getAuthorByUserCode(userCode);
            List<Map<String, Object>> mapList = resOneData.getData();
            if (!EmptyUtils.isEmpty(mapList)) {
                Object authors = mapList.get(0).get(AuthorXMLTag.AUTHOR_TAG);
                if (!EmptyUtils.isEmpty(authors)) {
                    JSONObject resp = JSONObject.parseObject(authors.toString());
                    if(resp.containsKey("appCode") && resp.getString("appCode").equalsIgnoreCase(appcode)){
                        if(resp.containsKey("roleList")){
                            // 循环所有角色，获取角色code
                            JSONArray roleArray = resp.getJSONArray("roleList");
                            // 本应用下的所有角色枚举，转换为map格式
                            Map<String, ERole> roleMap = ERole.mapByUserCode();
                            // 循环用户所属的每个角色
                            roleArray.forEach(x->{
                                String roleCode= ((JSONObject) x).getString("roleCode");
                                // 如果角色code属于本应用下的某个角色（code）， 加入角色list
                                if(roleMap.containsKey(roleCode)){
                                    roles.add(roleMap.get(roleCode));
                                }
                            });
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ERole[] array = new ERole[roles.size()];
        return roles.toArray(array);
    }

    /**
     * 统一授权客户端
     * @return
     */
    private ClientForAuthor getAuthorClient() {
        int version = 1;
        String appCode = appcode;
        String name = username4A;
        String passWord = password4A;
        String url = authurl;

        ClientForAuthor client = new ClientForAuthor();
        client.initHttp(version, appCode, name, passWord, url);
        return client;
    }

}
