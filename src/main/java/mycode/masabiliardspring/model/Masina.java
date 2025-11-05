package mycode.masabiliardspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="masa_biliard")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data

public class Masina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,length = 255)
    private String marca;
    @Column(nullable = false)
    private int marime;
    @Column(nullable = false,length = 255)
    private String culoare;
    @Override
    public String toString(){
        String text=this.marca+" "+this.culoare+" "+this.marime;
        return text;
    }


}
