package com.hd.v1unit.item;

import com.hd.v1.item.entity.ItemEntity;
import com.hd.v1.item.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;


@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Item Repository Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("dev")
public class RepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Order(1)
    @DisplayName("1. Insert Test")
    public void test1(){
        // given
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                        .name(name)
                        .price(price)
                .build());
        log.info("ItemEntity: {}", itemEntity);
        // then
        assertThat(name).isEqualTo(itemEntity.getName());
        assertThat(price).isEqualTo(itemEntity.getPrice());
        assertThat(itemEntity.getId()).isNotNull();
        assertThat(1L).isEqualTo(itemEntity.getId());
    }
}
