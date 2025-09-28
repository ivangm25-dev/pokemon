package wigm.pokemon.entity.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wigm.pokemon.entity.enums.TypeRequest;

import java.time.LocalDateTime;

@Data
@Document("logbook")
public class LogbookEntity {
    @Id
    private String id;
    private String ip;
    private LocalDateTime createdDate;
    private TypeRequest typeRequest;
    private Object response;
}
