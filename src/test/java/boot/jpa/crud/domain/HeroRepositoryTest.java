package boot.jpa.crud.domain;

import boot.jpa.crud.domain.hero.Hero;
import boot.jpa.crud.domain.hero.HeroRepository;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

// 테스트에는 여러종류가 있지만 @SpringBootTest 이걸 쓰면 거의 다 쓸 수 있음
@SpringBootTest
public class HeroRepositoryTest {

    // 패키지가 다르기 때문에 전체 생성자(@AllArguments) 사용불가
    @Autowired
    private HeroRepository heroRepository;

    // @AfterEach : 한 테스트가 실행되면 반드시 실행되는 것
    @AfterEach()
    public void cleanUp() {
        heroRepository.deleteAll();
    }

    // auditing이 잘 작동하는지 확인
    @Test
    public void JpaAuditingTest() {

        LocalDateTime now = LocalDateTime.parse("2020-03-18T00:00:00");

        // given
        Hero input = Hero.builder()
                .name("Jun")
                .age(29)
                .note("Hello Java")
                .build();

        // when
        Hero output = heroRepository.save(input);

        // then
        // output.getCreatedDate() 이 날짜보다 now가 isAfter(뒤)인지 확인
        assertTrue(output.getCreatedDate().isAfter(now));
        assertTrue(output.getModifiedDate().isAfter(now));
    }

    // save가 잘 되는지 확인
    @Test
    public void HeroSaveRequestTest() {

        // given
        Hero input = Hero.builder()
                .name("Jun")
                .age(29)
                .note("Hello Java")
                .build();

        // when
        Hero output = heroRepository.save(input);

        // then
        assertThat(input, is(output));
    }

    // select가 잘 되는지 확인
    @Test
    public void HeroFindAllResponseTest(){
        // given
        IntStream.rangeClosed(1, 10).forEach(i ->
                heroRepository.save(Hero.builder()
                        .name("Jun")
                        .age(29)
                        .note("Hello Java")
                        .build()));

        // when
        List<Hero> output = heroRepository.findAll();

        // then
        assertThat(output.size(), is(10));
    }

    // 해당 id의 객체를 찾아올 수 있는지 확인
    @Test
    public void HeroFindByIdResponseTest() {
        // given
        Hero input = Hero.builder()
                .name("Jun")
                .age(29)
                .note("Hello Java")
                .build();

        heroRepository.save(input);

        // 1L -> Long 타입 1을 의미
        // when
        Hero output = heroRepository.findById(1L).orElse(null);

        // then
        assertThat(input.getName(), is(output.getName()));
        assertThat(input.getAge(), is(output.getAge()));
        assertThat(input.getNote(), is(output.getNote()));
    }

    // update가 잘 되는지 확인
    @Test
    public void HeroUpdateRequestTest() {
        // given
        Hero input = Hero.builder()
                .name("Jun")
                .age(29)
                .note("Hello Java")
                .build();

        heroRepository.save(input);

        // when
        heroRepository.save(Hero.builder()
                .id(1L)
                .name("Jun")
                .age(29)
                .note("Hello Java")
                .build());

        // then
        Hero output = heroRepository.findById(1L).orElse(null);

        assertThat(input.getId(), is(output.getId()));
        assertThat(toStringCreatedDate(input.getCreatedDate()), is(toStringCreatedDate(output.getCreatedDate())));
        assertTrue(output.getModifiedDate()
                .isAfter(input.getModifiedDate()));
    }

    // date format
    public String toStringCreatedDate(LocalDateTime createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(createdDate)
                .map(formatter::format)
                .orElse("");
    }

}
