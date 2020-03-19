package boot.jpa.crud.web;

import boot.jpa.crud.dto.HeroFindByIdResponseDto;
import boot.jpa.crud.dto.HeroSaveRequestDto;
import boot.jpa.crud.dto.HeroUpdateRequestDto;
import boot.jpa.crud.service.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
public class HeroRestController {

    private HeroService heroService;

    @PostMapping("/save")
    public Long HeroSaveRequest(@RequestBody HeroSaveRequestDto dto) {

        IntStream.rangeClosed(0, 100).forEach(i ->
                heroService.HeroSaveRequest(HeroSaveRequestDto.builder()
                        .name("Jun")
                        .age(i)
                        .note("Hello")
                        .build()));

        return heroService.HeroSaveRequest(dto);
    }

    @PostMapping("/find")
    public HeroFindByIdResponseDto HeroFindByIdResponse(@RequestBody Long id) {
        return heroService.HeroFindByIdResponse(id);
    }

    @PutMapping("/update")
    public Long HeroUpdateRequest(@RequestBody HeroUpdateRequestDto dto){
        return heroService.HeroUpdateRequest(dto);
    }

    @DeleteMapping("/delete")
    public Long HeroDeleteByIdRequest(@RequestBody Long id) {
        heroService.HeroDeleteByIdRequest(id);
        return id;
    }

}
