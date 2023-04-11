import org.example.TobyApplication;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.exception.CustomException;
import org.example.exception.ExceptionMaker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TobyApplication.class)
public class MethodTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void init() throws ClassNotFoundException, SQLException {
        System.out.println("UserDao deleteAll()");
        userDao.deleteAll();
    }

    /**
     *  assertEquals
     */
    @Test
    public void method1() throws ClassNotFoundException, SQLException {

        User user = new User();
        user.setId("rereer");
        user.setName("김태우");
        user.setPassword("1111");

        // DB에 저장
        userDao.add(user);

        User findUser = userDao.get(user.getId());

        assertEquals(findUser.getPassword(), user.getPassword());
    }

    /**
     * @throws ArithmeticException
     */
    @Test(expected = ArithmeticException.class) //Junit5의 assertThrow와 같음
    public void method2() {
        int a = 3 / 0;
    }

    /**
     * 예외 메시지까지 체크하기
     */
    @Rule
    public ExpectedException exceptionWatcher = ExpectedException.none();

    @Test
    public void method3() throws CustomException {
        ExceptionMaker exceptionMaker = new ExceptionMaker();
        exceptionWatcher.expect(CustomException.class);
        exceptionWatcher.expectMessage("Exception 던지기");
        exceptionMaker.MakeException();
    }

    /**
     * fail 단정 메서드
     */
    @Test
    public void method4() {
        try {
            int a = 3 / 0;
        }catch (Exception e) {
            fail("0으로 나눠졌습니다.");
        }
    }
}
