package io.github.pitzzahh.jokes.model;

import lombok.Builder;
import java.util.List;

@Builder
public record Joke(List<Category> categories, String joke) { }
