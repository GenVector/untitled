package userless;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class NormalLogin {
    private String username;
    private String password;

    public NormalLogin(String username) {
        this.username = username;
    }
}

public class UselessTest {

    private static String test(NormalLogin normalLogin) {
        String service = "default";
        String username = normalLogin.getUsername();
        int typeSplitIndex = normalLogin.getUsername().lastIndexOf("@");
        if (typeSplitIndex >= 0) {
            service = username.substring(typeSplitIndex + 1);
        }
        return service;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        NormalLogin normalLogin = new NormalLogin("GenVector");
        System.out.println(test(normalLogin));
    }
}
