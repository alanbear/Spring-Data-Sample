package alanbear.sample.spring.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "human")
public class Human implements Serializable{

    private static final long serialVersionUID = 3314964877212871231L;

    @Id
    @Column(name = "id_number", unique = true, nullable = false, length = 15)
    private String idNumber;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "nick_name", nullable = false, length = 20)
    private String nickName;

    @Column(name = "sex", nullable = false, length = 1)
    private String sex;

    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "weight", nullable = false)
    private Float weight;

}
