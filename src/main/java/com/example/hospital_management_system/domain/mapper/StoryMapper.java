package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.StoryDto;
import com.example.hospital_management_system.domain.entity.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper implements BaseMapper<Story, StoryDto>{
    @Override
    public Story convertToEntity(StoryDto dto) {
        Story story = new Story();
        story.setDescription(dto.getDescription());
        story.setRecipe(dto.getRecipe());
        return story;
    }

    @Override
    public StoryDto convertToDto(Story entity) {
        StoryDto storyDto = new StoryDto();
        storyDto.setDescription(entity.getDescription());
        storyDto.setRecipe(entity.getRecipe());
        return storyDto;
    }
}
