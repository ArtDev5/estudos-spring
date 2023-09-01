package pessoal.estudos.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disciplines")
public class DisciplineEntity {
  @Id
  @GeneratedValue
  private String id;
  private String name;
  private Integer courseLoad;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getCourseLoad() {
    return courseLoad;
  }
}
