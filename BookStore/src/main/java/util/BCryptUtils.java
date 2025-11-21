package util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    // Băm mật khẩu
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); 
    }

    // Kiểm tra mật khẩu nhập có khớp với mật khẩu đã băm không
    public static boolean check(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
