package ar.com.wolox.challengecosta.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {

    private Long id;

    private Long postId;

    private String name;

    private String email;

    private String body;

}
