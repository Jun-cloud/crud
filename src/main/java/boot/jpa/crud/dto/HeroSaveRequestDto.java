package boot.jpa.crud.dto;

import boot.jpa.crud.domain.hero.Hero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HeroSaveRequestDto {

    private String name;
    private int age;
    private String note;

    public Hero toEntity() {

        return Hero.builder()
                .name(name)
                .age(age)
                .note(note)
                .build();
    }

    @Builder
    public HeroSaveRequestDto(String name, int age, String note) {
        this.name = name;
        this.age = age;
        this.note = note;
    }

}
