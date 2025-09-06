package co.edu.uptc.ejemplo.play.domain.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface EjemploPlayAiService {
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de gestión de Películas {{platform}}.
            Usa menos de 120 caracteres, hazlo en inglés y ponle emojis.
            """)
    String generateGreeting(@V("platform") String platform);


    @SystemMessage("""
                Recomiendale tres películas al usuario según sus gustos. 
                No incluyas películas que estén por fuera de la plataforma EjemploPlay
                """)
    String movieSuggestion (@UserMessage String userMessage);

}
