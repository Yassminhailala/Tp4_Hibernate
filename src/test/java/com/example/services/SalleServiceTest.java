package com.example.services;

import entities.Salle;
import services.SalleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SalleServiceTest {

    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle("A101");
        salleService.create(salle);
    }

    @After
    public void tearDown() {
        if (salle != null && salle.getId() > 0) {
            Salle found = salleService.findById(salle.getId());
            if (found != null) {
                salleService.delete(found);
            }
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Salle should have ID", salle.getId());
        assertTrue("Salle ID > 0", salle.getId() > 0);
    }

    @Test
    public void testFindById() {
        Salle found = salleService.findById(salle.getId());
        assertNotNull("Salle should be found", found);
        assertEquals("Code should match", "A101", found.getCode());
    }

    @Test
    public void testUpdate() {
        salle.setCode("B202");
        salleService.update(salle);

        Salle updated = salleService.findById(salle.getId());
        assertEquals("Updated code should match", "B202", updated.getCode());
    }

    @Test
    public void testDelete() {
        int id = salle.getId();
        salleService.delete(salle);

        Salle found = salleService.findById(id);
        assertNull("Salle should be deleted", found);
    }

    @Test
    public void testFindAll() {
        List<Salle> salles = salleService.findAll();
        assertNotNull("List should not be null", salles);
        assertTrue("List should not be empty", salles.size() > 0);
    }
}