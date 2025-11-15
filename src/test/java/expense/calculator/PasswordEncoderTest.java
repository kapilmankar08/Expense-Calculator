package expense.calculator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
  public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println("User password: " + encoder.encode("user123"));
    System.out.println("Admin password: " + encoder.encode("admin123"));
  }
}
