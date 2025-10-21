package com.example.services;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MachineServiceTest {

    private MachineService machineService;
    private SalleService salleService;
    private Machine machine;
    private Salle salle;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();

        // Créer une salle
        salle = new Salle("TestSalle");
        salleService.create(salle);

        // Créer une machine
        machine = new Machine("TEST-001", new Date(), salle);
        machineService.create(machine);
    }

    @After
    public void tearDown() {
        // Nettoyer machine
        if (machine != null && machine.getId() > 0) {
            Machine foundMachine = machineService.findById(machine.getId());
            if (foundMachine != null) {
                machineService.delete(foundMachine);
            }
        }

        // Nettoyer salle
        if (salle != null && salle.getId() > 0) {
            Salle foundSalle = salleService.findById(salle.getId());
            if (foundSalle != null) {
                salleService.delete(foundSalle);
            }
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Machine should have ID", machine.getId());
        assertTrue("Machine ID > 0", machine.getId() > 0);
    }

    @Test
    public void testFindById() {
        Machine found = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", found);
        assertEquals("Ref should match", "TEST-001", found.getRef());
    }

    @Test
    public void testUpdate() {
        machine.setRef("TEST-002");
        machineService.update(machine);

        Machine updated = machineService.findById(machine.getId());
        assertEquals("Updated ref should match", "TEST-002", updated.getRef());
    }

    @Test
    public void testDelete() {
        int id = machine.getId();
        machineService.delete(machine);

        Machine found = machineService.findById(id);
        assertNull("Machine should be deleted", found);
    }

    @Test
    public void testFindBetweenDate() {
        Date hier = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Date demain = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        List<Machine> machines = machineService.findBetweenDate(hier, demain);

        assertNotNull("Machines list not null", machines);
        assertTrue("Should find machines", machines.size() > 0);

        // Vérifier que notre machine est dans la liste
        boolean found = false;
        for (Machine m : machines) {
            if (m.getId() == machine.getId()) {
                found = true;
                break;
            }
        }
        assertTrue("Our test machine should be in the result list", found);
    }
}