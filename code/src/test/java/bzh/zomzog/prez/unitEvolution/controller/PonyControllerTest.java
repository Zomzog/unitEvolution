package bzh.zomzog.prez.unitEvolution.controller;

import bzh.zomzog.prez.unitEvolution.domain.PonyDto;
import bzh.zomzog.prez.unitEvolution.service.PonyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static bzh.zomzog.prez.unitEvolution.domain.PonyType.Unicorns;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PonyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PonyService service;

    @Nested
    class ListAll {

        @DisplayName("List all without filters")
        @Test
        void withoutFilters() throws Exception {

            PonyDto pony1 = PonyDto.newBuilder()
                    .id("id1")
                    .name("Rarity")
                    .type(Unicorns)
                    .build();

            PonyDto pony2 = PonyDto.newBuilder()
                    .id("id2")
                    .name("Twilight Sparkle")
                    .type(Unicorns)
                    .build();

            when(service.listAll(isNull())).thenReturn(Arrays.asList(pony1, pony2));

            mockMvc.perform(get("/ponies"))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$", hasSize(2)))

                    .andExpect(jsonPath("$[0].id").value(pony1.getId()))
                    .andExpect(jsonPath("$[0].name").value(pony1.getName()))
                    .andExpect(jsonPath("$[0].type").value(pony1.getType().name()))

                    .andExpect(jsonPath("$[1].id").value(pony2.getId()))
                    .andExpect(jsonPath("$[1].name").value(pony2.getName()))
                    .andExpect(jsonPath("$[1].type").value(pony2.getType().name()));
        }

        @DisplayName("List all by type")
        @Test
        void byType() throws Exception {

            PonyDto pony1 = PonyDto.newBuilder()
                    .id("id1")
                    .name("Rarity")
                    .type(Unicorns)
                    .build();

            PonyDto pony2 = PonyDto.newBuilder()
                    .id("id2")
                    .name("Twilight Sparkle")
                    .type(Unicorns)
                    .build();

            when(service.listAll(Unicorns)).thenReturn(Arrays.asList(pony1, pony2));

            mockMvc.perform(get("/ponies?type={0}", Unicorns.name()))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$", hasSize(2)))

                    .andExpect(jsonPath("$[0].id").value(pony1.getId()))
                    .andExpect(jsonPath("$[0].name").value(pony1.getName()))
                    .andExpect(jsonPath("$[0].type").value(pony1.getType().name()))

                    .andExpect(jsonPath("$[1].id").value(pony2.getId()))
                    .andExpect(jsonPath("$[1].name").value(pony2.getName()))
                    .andExpect(jsonPath("$[1].type").value(pony2.getType().name()));
        }
    }

    @Nested
    class GetById {

        @DisplayName("Get by valid id")
        @Test
        void validId() throws Exception {

            PonyDto pony1 = PonyDto.newBuilder()
                    .id("id1")
                    .name("Rarity")
                    .type(Unicorns)
                    .build();

            when(service.getById("id1")).thenReturn(Optional.of(pony1));

            mockMvc.perform(get("/ponies/id1"))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$.id").value(pony1.getId()))
                    .andExpect(jsonPath("$.name").value(pony1.getName()))
                    .andExpect(jsonPath("$.type").value(pony1.getType().name()));
        }

        @DisplayName("Get by invalid id return not found")
        @Test
        void invalidId() throws Exception {

            when(service.getById("wrongId")).thenReturn(Optional.empty());

            mockMvc.perform(get("/ponies/wrongId"))
                    .andExpect(status().isNotFound());
        }
    }

    @DisplayName("Create new pony")
    @Test
    void save() throws Exception {

        PonyDto pony = PonyDto.newBuilder()
                .name("Rarity")
                .type(Unicorns)
                .build();

        PonyDto created = PonyDto.newBuilder()
                .id("id1")
                .name("Rarity")
                .type(Unicorns)
                .build();

        when(service.save(pony)).thenReturn(created);

        mockMvc.perform(post("/ponies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MockMvcUtils.convertObjectToJson(pony)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value(created.getName()))
                .andExpect(jsonPath("$.type").value(created.getType().name()));
    }
}
