package top.itning.server.shwstudentgroup.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import top.itning.server.shwstudentgroup.handler.StudentGroupHandler;
import top.itning.server.shwstudentgroup.service.StudentGroupService;

import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author itning
 * @date 2019/4/30 19:38
 */
@Configuration
public class StudentGroupRouters {
    private final StudentGroupService studentGroupService;

    @Autowired
    public StudentGroupRouters(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @Bean
    RouterFunction<ServerResponse> userRouter(StudentGroupHandler studentGroupHandler) {
        return nest(
                all(),
                route()
                        .POST("/", studentGroupHandler::addGroup)
                        .DELETE("/{groupId}", studentGroupHandler::dropOutGroup)
                        .GET("/groups", studentGroupHandler::getAllGroups)
                        .GET("/exist", studentGroupHandler::isStudentJoinAnyStudentGroup)
                        .build())
                .andNest(
                        path("/internal"),
                        route()
                                .GET("/findGroupIdByStudentNumber/{studentNumber}", serverRequest -> ServerResponse.ok().body(studentGroupService.findGroupIdByStudentNumber(serverRequest.pathVariable("studentNumber")).collectList(), new ParameterizedTypeReference<List<String>>() {
                                }))
                                .build()
                );
    }
}