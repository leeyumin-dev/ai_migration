package backend.domain;

import backend.NoticeboardApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PostQuery_table")
@Data
//<<< DDD / Aggregate Root
public class PostQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String title;

    private PostType type;

    private Integer viewCount;

    private Date createdAt;

    public static PostQueryRepository repository() {
        PostQueryRepository postQueryRepository = NoticeboardApplication.applicationContext.getBean(
            PostQueryRepository.class
        );
        return postQueryRepository;
    }
}
//>>> DDD / Aggregate Root
