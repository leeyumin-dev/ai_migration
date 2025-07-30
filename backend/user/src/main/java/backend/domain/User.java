package backend.domain;

import backend.UserApplication;
import backend.domain.TokenRequested;
import backend.domain.UserLoggedIn;
import backend.domain.UserLoggedOut;
import backend.domain.UserRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @PostPersist
    public void onPostPersist() {
        UserLoggedIn userLoggedIn = new UserLoggedIn(this);
        userLoggedIn.publishAfterCommit();

        UserLoggedOut userLoggedOut = new UserLoggedOut(this);
        userLoggedOut.publishAfterCommit();

        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();

        TokenRequested tokenRequested = new TokenRequested(this);
        tokenRequested.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }
}
//>>> DDD / Aggregate Root
