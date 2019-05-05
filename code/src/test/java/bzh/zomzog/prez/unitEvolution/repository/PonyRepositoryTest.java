package bzh.zomzog.prez.unitEvolution.repository;

import bzh.zomzog.prez.unitEvolution.domain.Pony;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Earth;
import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Pegasi;
import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Unicorns;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataMongoTest
class PonyRepositoryTest {

    @Autowired
    private PonyRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void teardown() {
        mongoTemplate.dropCollection(Pony.class);
    }

    @Test
    void findById() {
        long now = System.currentTimeMillis();

        Pony pony = mongoTemplate.save(Pony.newBuilder()
                .name("Rainbow Dash")
                .type(Pegasi)
                .build());

        final Optional<Pony> fromDb = repository.findById(pony.getId());

        Pony expected = Pony.newBuilder()
                .name("Rainbow Dash")
                .type(Pegasi)
                .build();

        assertThat(fromDb).isPresent()
           .get().isEqualToIgnoringGivenFields(expected, "id", "creationDate");


        assertThat(fromDb.get().getId()).isNotNull();
        assertThat(fromDb.get().getCreationDate()).isGreaterThanOrEqualTo(now);
    }

    @Test
    void findByIdNotExisting() {
        final Optional<Pony> fromDb = repository.findById(ObjectId.get());

        assertThat(fromDb).isEmpty();
    }

    @Test
    void findByType() {
        mongoTemplate.save(Pony.newBuilder()
                .name("Rarity")
                .type(Unicorns)
                .build());

        mongoTemplate.save(Pony.newBuilder()
                .name("Twilight Sparkle")
                .type(Unicorns)
                .build());

        List<Pony> allByType = repository.findAllByType(Unicorns);

        assertEquals(2, allByType.size());

        assertEquals("Rarity", allByType.get(0).getName());
        assertEquals(Unicorns, allByType.get(0).getType());
        assertEquals("Twilight Sparkle", allByType.get(1).getName());
        assertEquals(Unicorns, allByType.get(1).getType());
    }

    @Test
    void findByTypeReturnOnlyType() {
        mongoTemplate.save(Pony.newBuilder()
                .id(ObjectId.get())
                .name("Rarity")
                .type(Unicorns)
                .build());

        mongoTemplate.save(Pony.newBuilder()
                .id(ObjectId.get())
                .name("Rainbow Dash")
                .type(Pegasi)
                .build());

        List<Pony> allByType = repository.findAllByType(Unicorns);

        assertEquals(1, allByType.size());

        assertEquals("Rarity", allByType.get(0).getName());
        assertEquals(Unicorns, allByType.get(0).getType());
    }

    @Test
    public void save() {
        long now = System.currentTimeMillis();

        Pony pony = Pony.newBuilder()
                .name("Big McIntosh")
                .type(Earth)
                .build();

        Pony result = repository.save(pony);

        assertNotNull(result.getId());
        assertEquals("Big McIntosh", result.getName());
        assertEquals(Earth, result.getType());
        assertTrue(result.getCreationDate() >= now);
    }
}
