package entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name = "findBetweenDateNative",
        query = "select * from machine where dateAchat between :d1 and :d2",
        resultClass = Machine.class
)
@NamedQuery(
        name = "findBetweenDate",
        query = "from Machine where dateAchat between :d1 and :d2"
)
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ref;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private Salle salle;

    // 🔹 Constructeurs
    public Machine() {
    }

    public Machine(String ref, Date dateAchat, Salle salle) {
        this.ref = ref;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    // 🔹 Getters et Setters
    public int getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", dateAchat=" + dateAchat +
                ", salle=" + (salle != null ? salle.getCode() : "null") +
                '}';
    }
}
