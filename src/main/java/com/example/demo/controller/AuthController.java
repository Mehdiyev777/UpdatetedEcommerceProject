package com.example.demo.controller;
import com.example.demo.auth.TokenManager;
import com.example.demo.auth.UserDetailsService;
import com.example.demo.dto.LoginDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController //Controller anotasyonunun daha özel bir halidir.
@RequestMapping("/auth") //Bu anotasyon ile gelen istekler ile sınıflar eşleştirilir.
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @GetMapping ({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    /*
        Bu sınıf üzerinde kullanacağımız bağımlılıkları inject ediyoruz.
    */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenManager tokenManager, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private AuthenticationManager authenticationManager;

    private TokenManager tokenManager;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Gelen kullanıcı adı ve parola bilgilerine göre authentication sağlanır.
     * response header'a generate edilen jwt token setlenir ve 200 http status kodu ile Login Success ibaresi response body'de gönderilir.
     * Bilgilerde bir hata varsa 400 http status kodu ile The username or password is incorrect. Please try again.
     * ibaresi response body'de gönderilir.
     * @param loginDto
     * @return
     */
    @PostMapping("/login") //auth/login 'e gelen web istekleri ile bu metodun eşleştirilmesi sağlanır.
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        try {
            if(loginDto != null && loginDto.getUsername() != null && loginDto.getPassword() != null){
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization","Bearer " + tokenManager.generateToken(loginDto.getUsername()));
                return ResponseEntity.ok().headers(headers).body("Login Success");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username or password is incorrect. Please try again.");

        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Kullanıcıdan alınan username ve password bilgisi database'e kaydedilir. 200 http status kodu ile username
     * has been successfully registered ibaresi gönderilir. Eğer bir sorun oluşursa 400 http status kodu ile
     * The username or password is incorrect. Please try again. response body'de gönderilir.
     * @param loginDto
     * @return
     */
    @PostMapping("/signup") //auth/signup 'a gelen web istekleri ile bu metodun eşleştirilmesi sağlanır.
    public ResponseEntity<String> signUp(@RequestBody LoginDto loginDto){
        try {
            if(loginDto != null && loginDto.getUsername() != null && loginDto.getPassword() != null){
               User user = new User();
               user.setName(loginDto.getUsername());
               user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
               userRepository.save(user);
               final String jwt = tokenManager.generateToken(loginDto.getUsername());

                return ResponseEntity.ok("jwt :" +  jwt +   "     has been successfully registered.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username or password is incorrect. Please try again.");

        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
