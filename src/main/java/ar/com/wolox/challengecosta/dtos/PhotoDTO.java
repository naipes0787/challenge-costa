package ar.com.wolox.challengecosta.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoDTO {

    private Long id;

    private Long albumId;

    private String title;

    private String url;

    private String thumbnailUrl;

}
