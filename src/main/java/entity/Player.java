//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "Player",
        schema = "",
        catalog = ""
)
public class Player {
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Id
    @Column(
            name = "id_player"
    )
    private int idPlayer;
    @Basic
    @Column(
            name = "name"
    )
    private String name;

    public Player() {
    }

    public int getIdPlayer() {
        return this.idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
