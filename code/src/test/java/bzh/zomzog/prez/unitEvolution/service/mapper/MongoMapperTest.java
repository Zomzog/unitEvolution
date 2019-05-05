package bzh.zomzog.prez.unitEvolution.service.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;

class MongoMapperTest {

    private MongoMapper mapper = new MongoMapperImpl();

    @Test
    void mapNull() {
        assertNull(mapper.map((String) null));
    }
}