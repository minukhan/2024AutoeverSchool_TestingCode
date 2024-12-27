package com.hd.v1unit.item;

import com.hd.v1.item.entity.ItemEntity;
import com.hd.v1.item.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Item Repository Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("h2db")
public class RepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void db_init(){
//        String sql = "ALTER TABLE item_db AUTO_INCREMENT = 1"; // mysql
        String sql = "ALTER TABLE item_db ALTER COLUMN id RESTART WITH 1"; // h2db
        entityManager.createNativeQuery(sql).executeUpdate();
    }


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
    @Test
    @Order(2)
    @DisplayName("2. FindById Test")
    public void test2(){
        // given
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        log.info("ItemEntity: {}", itemEntity);
        Optional<ItemEntity> item = itemRepository.findById(1L);
        // then
        if(item.isPresent()){
           assertThat(itemEntity.getName()).isEqualTo(item.get().getName());
        }else{
            assertThat(item).isEmpty();
        }

    }
    @Test
    @Order(3)
    @DisplayName("3. FindAll Test")
    public void test3(){
        // given
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());

        List<ItemEntity> item = itemRepository.findAll();
        // then
        assertThat(item).hasSize(1);
        assertThat(item.get(0).getName()).isEqualTo(name);

    }
    @Test
    @Order(4)
    @DisplayName("4. Update Test")
    public void test4(){
        // given
        String name = "pants1";
        long price = 1000L;
        String updateName = "pants2";
        long updatePrice = 2000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        ItemEntity updateItemEntity =  itemRepository.save(ItemEntity.builder()
                .id(1L)
                .name(updateName)
                .price(updatePrice)
                .build());
        // then
        assertThat(itemEntity.getId()).isEqualTo(updateItemEntity.getId());
        assertThat(updateItemEntity.getName()).isEqualTo(updateName);
        assertThat(updateItemEntity.getPrice()).isEqualTo(updatePrice);
    }
    @Test
    @Order(5)
    @DisplayName("5. Delete Test")
    public void test5(){
        // given
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        Optional<ItemEntity> item = itemRepository.findById(itemEntity.getId());
        itemRepository.delete(item.get());

        // then
        assertThat(itemRepository.findById(itemEntity.getId())).isNotPresent();

    }
}
