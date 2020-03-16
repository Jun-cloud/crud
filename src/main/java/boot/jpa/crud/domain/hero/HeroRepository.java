package boot.jpa.crud.domain.hero;

import org.springframework.data.jpa.repository.JpaRepository;

// Long : Id 데이터 타입(SQL 자동완성을 위해)
public interface HeroRepository extends JpaRepository<Hero, Long> {

}
