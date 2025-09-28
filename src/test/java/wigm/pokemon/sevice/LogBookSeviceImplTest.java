package wigm.pokemon.sevice;

import co.pokeapi.api.v2.pokemon.GenericComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import wigm.pokemon.entity.enums.TypeRequest;
import wigm.pokemon.entity.repository.LogbookEntity;
import wigm.pokemon.repository.LogbookRepository;
import wigm.pokemon.service.LogbookService;
import wigm.pokemon.service.LogbookServiceImpl;

import java.time.LocalDateTime;

@SpringBootTest()
public class LogBookSeviceImplTest {

    @Mock
    private LogbookRepository logbookRepository;
    @InjectMocks
    private LogbookServiceImpl logbookService;

    @Test
    public void saveRecord(){
        LogbookEntity entity = new LogbookEntity();
        entity.setIp("1.1.1.1");
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedDate(now);
        entity.setTypeRequest(TypeRequest.Name);
        entity.setResponse(new GenericComponent());
        Mockito.when(logbookRepository.save(Mockito.any())).thenReturn(entity);
        boolean save = logbookService.save("1.1.1.1", now, TypeRequest.Name, new GenericComponent());
        Assertions.assertEquals(save, true);
    }
}
