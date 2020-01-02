package design_pattern.demo2;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void register() throws InterruptedException {
        UserController controller = new UserController();
        while (true){
            controller.register(new UserVo());
            Thread.sleep(1*1000);
        }

    }

    @Test
    public void login() {
    }
}