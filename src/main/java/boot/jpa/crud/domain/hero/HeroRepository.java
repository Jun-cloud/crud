package boot.jpa.crud.domain.hero;

import boot.jpa.crud.dto.HeroFindAllResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// Long : Id 데이터 타입(SQL 자동완성을 위해)
public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query("select h " +
            "from Hero h " +
            "order by h.id desc")
    Page<HeroFindAllResponseDto> HeroFindAllResponse(Pageable pageable);
}
