package top.itning.server.shwwork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import top.itning.server.shwwork.dto.WorkDTO;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @Test
    public void testGetStudentUnDoneWork() {
        Page<WorkDTO> workDTOPage = workService.getStudentUnDoneWork("201601010317", 0, 10).block();
        System.out.println(workDTOPage.getTotalElements());
        System.out.println(workDTOPage.getTotalPages());
        workDTOPage.getContent().forEach(System.out::println);
    }

    @Test
    public void testGetStudentDoneWork() {
        Page<WorkDTO> workDTOPage = workService.getStudentDoneWork("201601010317", 0, 10).block();
        System.out.println(workDTOPage.getTotalElements());
        System.out.println(workDTOPage.getTotalPages());
        workDTOPage.getContent().forEach(System.out::println);
    }
}