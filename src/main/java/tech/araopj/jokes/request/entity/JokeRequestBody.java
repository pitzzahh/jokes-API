package tech.araopj.jokes.request.entity;

import tech.araopj.jokes.entity.Category;
import tech.araopj.jokes.entity.Language;
import lombok.*;

@Builder
public record JokeRequestBody(
        String joke,
        Category category,
        Language language
) {

}
