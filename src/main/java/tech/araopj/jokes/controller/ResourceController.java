/*
 * MIT License
 *
 * Copyright (c) 2022 pitzzahh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tech.araopj.jokes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.concurrent.CopyOnWriteArrayList;
import tech.araopj.jokes.entity.Category;
import tech.araopj.jokes.entity.Language;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/resource")
public class ResourceController {

    @GetMapping("/categories")
    public Collection<Category> getCategories() {
        return Arrays.stream(Category.values())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    @GetMapping("/languages")
    public List<Language> getLanguages() {
        return Arrays.stream(Language.values())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

}
