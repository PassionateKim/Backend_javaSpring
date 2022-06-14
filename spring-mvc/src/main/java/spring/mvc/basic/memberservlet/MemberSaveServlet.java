package spring.mvc.basic.memberservlet;

import spring.mvc.basic.Member;
import spring.mvc.basic.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "formServlet", urlPatterns = "/servlet-member-save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. POST로 받아온 데이터 꺼내기
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        //2. Member 객체 만들기
        Member member = new Member();
        member.setName(name);
        member.setAge(age);

        //3. 저장하기
        memberRepository.save(member);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter w = resp.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getName()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/servlet-member-list\">목록조회</a>\n" +
                "<a href=\"/index.html\">돌아가기</a>\n" +
                "</body>\n" +
                "</html>");
    }
}

