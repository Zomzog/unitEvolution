package bzh.zomzog.prez.unitEvolution.service;


import bzh.zomzog.prez.unitEvolution.domain.Pony;
import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import bzh.zomzog.prez.unitEvolution.repository.PonyRepository;
import bzh.zomzog.prez.unitEvolution.service.mapper.MongoMapperImpl;
import bzh.zomzog.prez.unitEvolution.service.mapper.PonyMapper;
import bzh.zomzog.prez.unitEvolution.service.mapper.PonyMapperImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Unicorns;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PonyServiceTest {

    @InjectMocks
    private PonyService service;

    @Mock
    private PonyRepository repository;

    @Spy
    private PonyMapper mapper = new PonyMapperImpl(new MongoMapperImpl());

    @Test
    void listAll() {
        // GIVEN
        Pony pony = Pony.newBuilder()
            .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
            .name("Rarity")
            .type(Unicorns)
            .build();

        when(repository.findAllByType(Unicorns)).thenReturn(Collections.singletonList(pony));

        // WHEN
        List<PonyDto> result = service.listAll(Unicorns);

        verify(repository, times(1)).findAllByType(Unicorns);

        // THEN
        assertEquals(1, result.size());
        assertEquals("5cceb7e452d0e307dd8e7576", result.get(0).getId());
        assertEquals("Rarity", result.get(0).getName());
        assertEquals(Unicorns, result.get(0).getType());
    }

    @Test
    void findById() {
        // GIVEN
        Pony pony = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Rarity")
                .type(Unicorns)
                .build();

        when(repository.findById(new ObjectId("5cceb7e452d0e307dd8e7576"))).thenReturn(Optional.of(pony));

        // WHEN
        Optional<PonyDto> byId = service.getById("5cceb7e452d0e307dd8e7576");

        // THEN
        assertTrue(byId.isPresent());
        assertEquals("5cceb7e452d0e307dd8e7576", byId.get().getId());
        assertEquals("Rarity", byId.get().getName());
        assertEquals(Unicorns, byId.get().getType());
    }

    @Test
    void save() {
        // GIVEN
        PonyDto ponyDto = PonyDto.newBuilder()
                .name("Rarity")
                .type(Unicorns)
                .build();
        Pony pony = Pony.newBuilder()
                .name("Rarity")
                .type(Unicorns)
                .build();

        Pony created = Pony.newBuilder()
                .id(new ObjectId("5cceb7e452d0e307dd8e7576"))
                .name("Rarity")
                .type(Unicorns)
                .build();

        when(repository.save(pony)).thenReturn(created);

        // WHEN
        PonyDto result = service.save(ponyDto);

        // THEN
        assertEquals("5cceb7e452d0e307dd8e7576", result.getId());
        assertEquals("Rarity", result.getName());
        assertEquals(Unicorns, result.getType());
    }

    @Test
    void saveErrorWhenForcingId() {
        // GIVEN
        PonyDto ponyDto = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Rarity")
                .type(Unicorns)
                .build();

        // WHEN
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                service.save(ponyDto));

        assertEquals("Id must be null", illegalArgumentException.getMessage());
    }

    @Test
    @Disabled
    void crazyTestIgnored() {
        // GIVEN
        PonyDto ponyDto = PonyDto.newBuilder()
                .id("5cceb7e452d0e307dd8e7576")
                .name("Rarity")
                .type(Unicorns)
                .build();

        // WHEN
        service.save(ponyDto);
    }
}