package wigm.pokemon.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import wigm.pokemon.entity.repository.LogbookEntity;

public interface LogbookRepository extends MongoRepository<LogbookEntity, String> {

}