package org.example;

import entity.Game;
import entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //Game gra = new Game();
        //gra.setIdGame(7);
        //session.delete(gra);


        //for(int i = 33; i<36;i++) {
            Player player = new Player();
            player.setIdPlayer(36);
            session.delete(player);
       // }
        session.getTransaction().commit();
        session.close();


    }
}