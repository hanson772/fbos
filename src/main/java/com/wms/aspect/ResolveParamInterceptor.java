package com.wms.aspect;

import com.wms.annotation.NeedAuth;
import com.wms.annotation.Who;
import com.wms.bean.enums.ERole;
import com.wms.bean.pojo.Employee;
import com.wms.config.CusException;
import com.wms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author max.chen
 * @class
 */
public class ResolveParamInterceptor extends HandlerInterceptorAdapter implements HandlerMethodArgumentResolver {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //如果对象不是handlermethod直接返回
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //  方法是否需要验证身份
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean hasNeed = false;


        // 根据方法注入@NeedAuth验证
        Method method = handlerMethod.getMethod();
        // 是否需要校验auth验证
        NeedAuth auth = method.getAnnotation(NeedAuth.class);
        NeedAuth classNeedAuth = handlerMethod.getBeanType().getAnnotation(NeedAuth.class);
        hasNeed = (auth != null || classNeedAuth != null);

        //  如果需要验证身份
        if (hasNeed) {
            ERole[] roles = auth != null ? auth.roles() : classNeedAuth.roles();
            Employee e = getEmployeeFromRequest(request);
            // 需要的角色不为空，而且不囊括在当前有用户拥有的角色
            if (!ERole.anyMatch(roles, e.getRoles())) {
                Assert.isTrue(false, "权限验证失败");
            }
        }

        return super.preHandle(request, response, handler);
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Who.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return getEmployeeFromRequest(request);
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request){
        Employee e = userService.getUserFromRequest(request);

        if(e == null){
            throw new CusException(401, "登录未验证");
        }
        return e;
    }
}
