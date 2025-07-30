package backend.domain;

import backend.NoticeboardApplication;
import backend.domain.PostDeleted;
import backend.domain.PostRegistered;
import backend.domain.PostUpdated;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Post_table")
@Data
//<<< DDD / Aggregate Root
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String title;

    private String content;

    private PostType type;

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    public static PostRepository repository() {
        PostRepository postRepository = NoticeboardApplication.applicationContext.getBean(
            PostRepository.class
        );
        return postRepository;
    }
}
//>>> DDD / Aggregate Root
