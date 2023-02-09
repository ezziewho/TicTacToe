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
        name = "Game",
        schema = "",
        catalog = ""
)
public class Game {
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Id
    @Column(
            name = "id_game"
    )
    private int idGame;
    @Basic
    @Column(
            name = "id_player1"
    )
    private int idPlayer1;
    @Basic
    @Column(
            name = "id_player2"
    )
    private int idPlayer2;
    @Basic
    @Column(
            name = "result"
    )
    private String result;

    public Game() {
    }

    public int getIdGame() {
        return this.idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdPlayer1() {
        return this.idPlayer1;
    }

    public void setIdPlayer1(int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return this.idPlayer2;
    }

    public void setIdPlayer2(int idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
