package com.devaneios.turmadeelite.external.courses;

import com.devaneios.turmadeelite.dto.SchoolClassViewDTO;
import com.devaneios.turmadeelite.security.guards.IsManager;
import com.devaneios.turmadeelite.security.guards.IsTeacher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/external/courses")
@AllArgsConstructor
public class ExternalCoursesController {

    private final ExternalCoursesService externalCoursesService;

    @IsManager
    @Operation(summary = "Lista todos os cursos de um sistema externo")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cursos encontrados com sucesso"
            )
    })
    @GetMapping
    public List<SchoolClassViewDTO> listAllCourses(Authentication authentication) throws IOException {
            String authUuid = (String) authentication.getPrincipal();
            return this.externalCoursesService.getAllCourses(authUuid);
    }

    @IsTeacher
    @GetMapping("/authenticated-teacher")
    public List<SchoolClassViewDTO> listClassesFromTeacher(Authentication authentication) throws IOException {
        String authUuid = (String) authentication.getPrincipal();
        return this.externalCoursesService.getCoursesFromTeacher(authUuid);
    }

//    @IsTeacher
//    @GetMapping("/{externalId}")
//    public ResponseEntity<?> getExternalClassById(@PathVariable String externalId, Authentication authentication) throws IOException {
//        String authUuid = (String) authentication.getPrincipal();
//        return ResponseEntity.ok(this.externalCoursesService.getExternalClassById(externalId, authUuid));
//    }

}
