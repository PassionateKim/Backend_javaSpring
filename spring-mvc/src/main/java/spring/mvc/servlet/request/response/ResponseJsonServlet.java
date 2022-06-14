package spring.mvc.servlet.request.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import spring.mvc.servlet.ServletData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        //직접 값 넣기
        ServletData servletData = new ServletData();
        servletData.setName("kim");
        servletData.setAge(20);

        //{"name": "kim", "age": 20}
        String result = objectMapper.writeValueAsString(servletData);
        resp.getWriter().write(result);
    }
}
