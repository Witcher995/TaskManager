/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.repository;

import com.utp.teamwork.model.Branch;
import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Tasks;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AdamPrzedlacki
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void testFindOne() {
        entityManager.persist(new Branch("Programista"));
        Branch branch = branchRepository.findByNameBranch("Programista");
        assertEquals("Programista", branch.getNameBranch());
    }

    @Autowired
    private CheckListTaskRepository checkListRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Test
    public void testRelation() {
        entityManager.persist(new CheckListTask("Testowanie funkcjonalności", false));
        List<CheckListTask> checkListTasks = checkListRepository.findByTitle("Testowanie funkcjonalności");
        //entityManager.persist(new Tasks("Przetestowanie Aplikacji", checkListTasks));
        Tasks task = tasksRepository.findByTitle("Przetestowanie Aplikacji");
        //for (CheckListTask checkListTask : task.getCheckListTasks()) {
        //assertEquals("Testowanie funkcjonalności", checkListTask.getTitle());
    }
}
