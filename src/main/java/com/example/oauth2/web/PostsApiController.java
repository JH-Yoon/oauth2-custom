package com.example.oauth2.web;

import com.example.oauth2.service.PostsService;
import com.example.oauth2.web.Dto.PostsListResponseDto;
import com.example.oauth2.web.Dto.PostsResponseDto;
import com.example.oauth2.web.Dto.PostsSaveRequestDto;
import com.example.oauth2.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {

        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postsService.findById(id);
    }

    @GetMapping("/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
