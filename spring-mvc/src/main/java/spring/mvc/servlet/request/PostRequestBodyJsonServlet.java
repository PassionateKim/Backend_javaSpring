package spring.mvc.servlet.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;
import spring.mvc.servlet.ServletData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "postRequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class PostRequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PostRequestBodyJsonServlet.service");

        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // json 형식 출력
        System.out.println("messageBody = " + messageBody);

        // json 형식 변환
        ServletData servletData = objectMapper.readValue(messageBody, ServletData.class);
        System.out.println("name = " + servletData.getName());
        System.out.println("age = " + servletData.getAge());

        resp.getWriter().write("ok");
    }
}
