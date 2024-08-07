package cn.rich.community.controller.advice;

import cn.rich.community.util.CommunityUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("服务器发生异常: {}", e.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        // 构建返回给前端的错误信息
        String jsonResponse = String.format("{\"message\": \"服务器发生异常: %s\"}", e.getMessage());
        // 将错误信息写入响应
        response.getWriter().write(jsonResponse);
    }

}
