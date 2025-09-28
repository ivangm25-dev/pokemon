package wigm.pokemon.service;

import wigm.pokemon.entity.enums.TypeRequest;

import java.time.LocalDateTime;

public interface LogbookService {
    boolean save(String ip, LocalDateTime localDateTime, TypeRequest typeRequest, Object response);
}
