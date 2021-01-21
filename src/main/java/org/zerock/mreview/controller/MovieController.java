package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.service.MovieService;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO: " + movieDTO);
        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("pageRequestDTO: " + pageRequestDTO);
        model.addAttribute("result", movieService.getList(pageRequestDTO));
    }

    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("mno: " + mno);
        MovieDTO movieDTO = movieService.getMovie(mno);
        model.addAttribute("dto", movieDTO);
    }

    @PostMapping("/remove")
    public String remove(long mno, RedirectAttributes redirectAttributes) {
        log.info("mno: " + mno);
        movieService.removeWithReplies(mno);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @PostMapping("/modify")
    public String modify(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
        log.info("movieDTO: " + movieDTO);
        movieService.modify(movieDTO);
        return "redirect:/movie/list";
    }
}
