package boot.jpa.crud.web;

import boot.jpa.crud.dto.HeroFindAllResponseDto;
import boot.jpa.crud.service.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class IndexController {

    private HeroService heroService;

    @GetMapping("/")
    // PageableDefault : 첫 페이지
    public String index(Model model, @PageableDefault Pageable pageable) {
        Page<HeroFindAllResponseDto> pages = heroService.HeroFindAllResponse(pageable);
        model.addAttribute("pages", pages);

        int start = Math.max(1, pages.getNumber() - 2);
        int last = Math.min(start + 6, pages.getTotalPages());

        model.addAttribute("start", start);
        model.addAttribute("last", last);

//        model.addAttribute("title","JUnit project");
//        model.addAttribute("list",heroService.HeroFindAllResponse());
        // 자동으로 잡아줌
        return "index";
    }

}
