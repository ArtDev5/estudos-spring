package pessoal.estudos.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Controller {

  @GetMapping("/public")
  ResponseEntity publicRoute() {
    return ResponseEntity.ok("<h1> Public route </h1<");
  }
  @GetMapping("/private")
  ResponseEntity privateRoute() {
    return ResponseEntity.ok("<h1> Public route </h1<");
  }
}
