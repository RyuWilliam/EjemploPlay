package co.edu.uptc.ejemplo.play.web.webControllers;

import co.edu.uptc.ejemplo.play.domain.dto.SuggestRequestDTO;
import co.edu.uptc.ejemplo.play.domain.services.EjemploPlayAiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "IA", description = "Operations we can do with AI help")
public class HelloController {

    private  final EjemploPlayAiService aiService;
    private final String platform;

    public HelloController(EjemploPlayAiService aiService,@Value("${spring.application.name}") String platform) {
        this.aiService = aiService;
        this.platform = platform;
    }


    @GetMapping("/hello")
    public String hello(){
        return aiService.generateGreeting(platform);
    }
    @PostMapping("/suggest")
    public ResponseEntity<String> generateMovieSuggestion(@RequestBody SuggestRequestDTO suggestRequestDTO){
        String suggestion = aiService.movieSuggestion(suggestRequestDTO.userPreferences());
        return ResponseEntity.ok(suggestion);
    }
}
