package ar.com.wolox.challengecosta.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDTO {

    private Long id;

    private Long userId;

    private String title;

}
