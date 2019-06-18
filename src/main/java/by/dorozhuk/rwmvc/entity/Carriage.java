package by.dorozhuk.rwmvc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Carriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Integer number;

    private String type;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Train train;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seat> seats;

    private Integer countSeats;

    private Integer newCountSeats;

    public Carriage() {
    }

    public Carriage(Train train) {
        this.train = train;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Integer getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(Integer countSeats) {
        this.countSeats = countSeats;
    }

    public Integer getNewCountSeats() {
        return newCountSeats;
    }

    public void setNewCountSeats(Integer newCountSeats) {
        this.newCountSeats = newCountSeats;
    }

    @Override
    public String toString() {
        return "Carriage{" +
                "id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", train=" + train +
                ", seats=" + seats +
                ", countSeats=" + countSeats +
                '}';
    }
}
