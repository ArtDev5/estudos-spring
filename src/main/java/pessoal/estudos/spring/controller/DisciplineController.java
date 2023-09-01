package pessoal.estudos.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pessoal.estudos.spring.entity.DisciplineEntity;
import pessoal.estudos.spring.repository.DisciplineRepository;

@RestController
@RequestMapping("/disciplines")
@PreAuthorize("hasAnyAuthority('COORDINATOR', 'TEACHER')")
public class DisciplineController {

  @Autowired
  private DisciplineRepository disciplineRepository;

  @GetMapping
  ResponseEntity getDisciplines() {
    return ResponseEntity.ok(disciplineRepository.findAll());
  }

  @GetMapping("/{id}")
  ResponseEntity getDisciplineById(@PathVariable String id) {
    return ResponseEntity.ok(disciplineRepository.getById(id));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('COORDINATOR')")
  ResponseEntity createDiscipline(@RequestBody DisciplineEntity disciplineToCreate) {
    return ResponseEntity.ok(disciplineRepository.save(disciplineToCreate));
  }

  @DeleteMapping
  @PreAuthorize("hasAuthority('COORDINATOR')")
  ResponseEntity deleteDisciplineById(@PathVariable String id) {
    disciplineRepository.deleteById(id);
    return ResponseEntity.ok("");
  }
}
