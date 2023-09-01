package pessoal.estudos.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;
  private String name;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  public UserEntity() {
  }

  public UserEntity(String id, String name, UserRole userRole) {
    this.id = id;
    this.name = name;
    this.userRole = userRole;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public UserRole getUserRole() {
    return userRole;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && userRole == that.userRole;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, userRole);
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", userRole=" + userRole +
        '}';
  }
}


enum UserRole {
  TEACHER, STUDENT, COORDINATOR
}