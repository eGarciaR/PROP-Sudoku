/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.DomainCtrl;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel.criado.casas
 */
public class RankingManagerTest {
    
    public RankingManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class RankingManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        String gameName = DomainCtrl.GAME_NAME;
        RankingManager result = RankingManager.getInstance(gameName);
        RankingManager expResult = RankingManager.getInstance(gameName);
        assertEquals(expResult, result);
    }


    /**
     * Test of updateAll method, of class RankingManager.
     */
    @Test
    public void testUpdateAll() {
        System.out.println("updateAll");
        ArrayList<HashMap<String, Object>> ranking = new ArrayList<>();
        HashMap<String, Object> re;
        re = new HashMap<>();
        re.put("position", 1);
        re.put("user", "dani");
        re.put("score", 1);
        ranking.add(re);
        re = new HashMap<>();
        re.put("position", 1);
        re.put("user", "dani");
        re.put("score", 2);
        ranking.add(re);
        
        RankingManager instance = RankingManager.getInstance(DomainCtrl.GAME_NAME);
        instance.updateAll(ranking);
        assertEquals(null, instance.getAll());
    }

    /**
     * Test of getAll method, of class RankingManager.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        RankingManager instance = RankingManager.getInstance(DomainCtrl.GAME_NAME);
        ArrayList<HashMap<String, Object>> expResult = null;
        ArrayList<HashMap<String, Object>> result = instance.getAll();
        assertEquals(expResult, result);
    }
    
}
