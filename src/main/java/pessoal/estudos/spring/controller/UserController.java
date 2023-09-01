package pessoal.estudos.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pessoal.estudos.spring.entity.UserEntity;
import pessoal.estudos.spring.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('COORDINATOR', 'TEACHER')")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity getUsers() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity getUserById(@PathVariable String id) {
    try {
      UserEntity userEntity = userRepository.getById(id);
      if (userEntity.getName() != null && userEntity.getId() != null) {
        return ResponseEntity.ok(userEntity);
      }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    } catch (EntityNotFoundException exception) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  @PreAuthorize("hasAuthority('COORDINATOR')")
  public ResponseEntity createUser(@RequestBody UserEntity userToCreate) {
    return ResponseEntity.ok(userRepository.save(userToCreate));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('COORDINATOR')")
  public ResponseEntity deleteUserById(@PathVariable String id) {
    userRepository.deleteById(id);
    return ResponseEntity.ok("");
  }
}
