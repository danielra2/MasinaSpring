package mycode.masabiliardspring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "masa_biliard",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_marca_culoare", columnNames = {"marca", "culoare"})
        },
        indexes = {
                @Index(name = "idx_marca_culoare", columnList = "marca, culoare")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getMarime() {
        return marime;
    }

    public void setMarime(int marime) {
        this.marime = marime;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }
}
