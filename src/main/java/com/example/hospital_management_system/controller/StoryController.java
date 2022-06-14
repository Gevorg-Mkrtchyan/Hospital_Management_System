package com.example.hospital_management_system.controller;


import com.example.hospital_management_system.domain.dto.StoryDto;
import com.example.hospital_management_system.domain.entity.Story;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/story/{registration_id}")
    @Operation(summary = "create story by registration_id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> create(@RequestBody StoryDto storyDto, @PathVariable("registration_id") Long registrationId) {
        Optional<StoryDto> storyDtoOptional = storyService.create(storyDto, registrationId);
        if (storyDtoOptional.isPresent()) {
            return new EntityCreatingResponse<StoryDto>().onSuccess(storyDtoOptional.get());
        }
        return new EntityCreatingResponse<StoryDto>().onFailure("Story");
    }

    @GetMapping("/stroy/{id}")
    @Operation(summary = "get by story id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<StoryDto> storyDtoOptional = storyService.getById(id);
        if (storyDtoOptional.isPresent()) {
            return new EntityLookupResponse<StoryDto>().onSuccess(storyDtoOptional.get());
        }
        return new EntityLookupResponse<StoryDto>().onFailure("Story");
    }

    @PutMapping("/story/{id}")
    @Operation(summary = "update by Story id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> update(@RequestBody StoryDto storyDto, @PathVariable("id") Long id) {
        Optional<StoryDto> storyDtoOptional = storyService.update(storyDto, id);
        if (storyDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<Story>().onFailure("Story");
        }
        return new EntityUpdatingResponse<StoryDto>().onSuccess(storyDtoOptional.get());
    }

    @DeleteMapping("/stroy/{id}")
    @Operation(summary = "delete by story id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<StoryDto> storyDtoOptional = storyService.getById(id);
        if (storyDtoOptional.isPresent()) {
            storyService.deleteById(id);
            return new EntityDeletingResponse<StoryDto>().onSuccess(storyDtoOptional.get(), "Story");
        }
        return new EntityLookupResponse<StoryDto>().onFailure("Story");
    }
}

