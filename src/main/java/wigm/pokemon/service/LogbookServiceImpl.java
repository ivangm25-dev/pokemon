package wigm.pokemon.service;

import org.springframework.stereotype.Service;
import wigm.pokemon.entity.enums.TypeRequest;
import wigm.pokemon.entity.repository.LogbookEntity;
import wigm.pokemon.repository.LogbookRepository;

import java.time.LocalDateTime;

@Service
public class LogbookServiceImpl implements LogbookService{

    private LogbookRepository logbookRepository;

    public  LogbookServiceImpl(LogbookRepository logbookRepository){
        this.logbookRepository = logbookRepository;
    }

    @Override
    public boolean save(String ip, LocalDateTime localDateTime, TypeRequest typeRequest, Object response) {
        LogbookEntity record = new LogbookEntity();
        record.setIp(ip);
        record.setCreatedDate(localDateTime);
        record.setTypeRequest(typeRequest);
        record.setResponse(response);
        logbookRepository.insert(record);
        return true;
    }
}
