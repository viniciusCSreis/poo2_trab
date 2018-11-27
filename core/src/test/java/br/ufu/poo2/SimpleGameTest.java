package br.ufu.poo2;

import com.badlogic.gdx.ApplicationAdapter;
import org.junit.Test;
import static org.junit.Assert.*;


public class SimpleGameTest {

    @Test
    public void simpleGameTestExtendsApplicationAdapter(){
        SimpleGame simpleGame = new SimpleGame();
        assertTrue(simpleGame instanceof ApplicationAdapter);

    }


}
