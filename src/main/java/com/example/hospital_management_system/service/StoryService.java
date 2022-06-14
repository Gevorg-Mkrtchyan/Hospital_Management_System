package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.StoryDto;
import com.example.hospital_management_system.domain.entity.Registration;
import com.example.hospital_management_system.domain.entity.Story;
import com.example.hospital_management_system.domain.mapper.StoryMapper;
import com.example.hospital_management_system.repository.RegistrationRepository;
import com.example.hospital_management_system.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;
    private final RegistrationRepository registrationRepository;
    public StoryService(StoryRepository storyRepository, StoryMapper storyMapper, RegistrationRepository registrationRepository) {
        this.storyRepository = storyRepository;
        this.storyMapper = storyMapper;
        this.registrationRepository = registrationRepository;
    }

    public Optional<StoryDto> create(StoryDto storyDto,Long registrationId) {
        Story storyToSave = storyMapper.convertToEntity(storyDto);
       Optional<Registration> registrationOptional = registrationRepository.findById(registrationId);
        if (registrationOptional.isEmpty()){
            return Optional.empty();
        }
        storyToSave.setRegistration(registrationOptional.get());
        Story savedStory = storyRepository.save(storyToSave);
        return Optional.of(storyMapper.convertToDto(savedStory));
    }

    public Optional<StoryDto> getById(Long id) {
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(storyMapper.convertToDto(story.get()));
    }

    public void deleteById(Long id) {
        storyRepository.deleteById(id);
    }

    public Optional<StoryDto> update(StoryDto storyDto, Long id) {
        Optional<Story> storyOptional = storyRepository.findById(id);
        if (storyOptional.isEmpty()){
            return Optional.empty();
        }
        Story storyToSave = storyMapper.convertToEntity(storyDto);
        storyOptional.get().setDescription(storyToSave.getDescription());
        storyOptional.get().setRecipe(storyToSave.getRecipe());
        Story savedStory = storyRepository.save(storyOptional.get());
        return Optional.of(storyMapper.convertToDto(savedStory));

    }
}
