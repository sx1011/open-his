package com.bjsxt.config.shiro;

import com.bjsxt.constants.Constants;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * 生成token
 * *如果没有token生成一个返回到前台，
 * *如果有就从请求头里面取出来
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 从头里面得到请求token，如果不存在就生成一个
        String token = WebUtils.toHttp(request).getHeader((Constants.TOKEN));
        if (StringUtils.hasText(token)) {
            return token;
        } else {
            return UUID.randomUUID().toString();
        }
    }
}
