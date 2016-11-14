package com.miss.foodie.exception;


import com.alibaba.fastjson.JSON;
import com.miss.foodie.dto.BaseResult;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:MissYou
 * @CreateTime:2016/11/14:15:11
 * @description:
 */
public class GloabalExceptionResolver implements HandlerExceptionResolver {
    private final Logger LOG = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        LOG.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage());
        //这里有2种选择
        //跳转到定制化的错误页面
	    /*ModelAndView error = new ModelAndView("error");
		error.addObject("exMsg", ex.getMessage());
		error.addObject("exType", ex.getClass().getSimpleName().replace("\"", "'"));*/
        //返回json格式的错误信息
        try {
            PrintWriter writer = response.getWriter();
            BaseResult<String> result=new BaseResult(false, ex.getMessage());
            writer.write(JSON.toJSONString(result));
            writer.flush();
        } catch (Exception e) {
            LOG.error("Exception:",e);
        }
        return null;
    }
}
