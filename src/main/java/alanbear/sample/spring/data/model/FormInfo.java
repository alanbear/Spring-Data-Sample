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
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "form_info")
public class FormInfo implements Serializable{

    private static final long serialVersionUID = 8720550350829973169L;
    @Id
    @Column(name = "form_id", unique = true, nullable = false, length = 45)
    private String formId;

    @Column(name = "form_name", nullable = false, length = 45)
    private String formName;

    @Column(name = "serial_number_date", nullable = false)
    private LocalDate serialNumberDate;

    @Column(name = "serial_number", nullable = false)
    private int serialNumber;
}
