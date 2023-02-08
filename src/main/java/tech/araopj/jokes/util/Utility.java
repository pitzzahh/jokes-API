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

package tech.araopj.jokes.util;

import static io.github.pitzzahh.util.utilities.Util.isPresent;
import tech.araopj.jokes.repository.JokesRepository;
import tech.araopj.jokes.entity.Language;
import tech.araopj.jokes.entity.Category;
import tech.araopj.jokes.entity.Joke;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Utility {

    /**
     * Creates a list of default Joke objects and returns it.
     *
     * @return a list of jokes
     * @see Joke
     */
    static List<Joke> getJokes() {
        List<Joke> jokes = new ArrayList<>();
        Collections.addAll(jokes,
                Joke.builder().joke("Why do fathers take an extra pair of socks when they go golfing? In case they get a hole in one!")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("I\\'m afraid for the calendar. Its days are numbered.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a belt made out of watches? A \"waist of time\".")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a pile of cats? A \"meowntain\".")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder()
                        .joke("What do a tick and the Eiffel Tower have in common? They\\'re both Paris sites.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a fish wearing a bowtie? \"Sofishticated.\"")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("How do you follow Will Smith in the snow? You follow the fresh prints.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("If April showers bring May flowers, what do May flowers bring?\"Pilgrims\".")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a fake noodle? An \"impasta\".")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a cow with no legs? Ground beef.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a cow with two legs? Lean beef.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a belt made out of watches? A \"waist of time\".")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("I thought the dryer was shrinking my clothes. Turns out it was the refrigerator all along.")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("What do you call a factory that makes okay products? A \"SatisFACTORY\"")
                        .category(Category.DAD_JOKE)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa pagkain? Foodie!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa paglalaro? Gamer!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa paglalakbay? Traveler!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa paglalaro ng badminton? Shuttlecock!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa paglalaro ng basketball? Slam-dunk!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa babaeng mahilig sa paglalaro ng volleyball? Spiker!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa taong mahilig sa kanta? Music-lover!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Ano ang tawag sa isang mahilig sa basketball na namamalimos? Slamdunk-submit!")
                        .category(Category.ONE_LINER)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("Knock, knock. Who’s there? Cow says. Cow says who? No silly, cow says moooooo!")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("Knock, knock. Who’s there? Boo. Boo who? Don’t cry, it’s only a joke!")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("Knock, knock. Who’s there? Cows go. Cows go who? No silly, cows go moooooo!")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("Knock, knock. Who’s there? A little old lady. A little old lady who? I did not know you could yodel!")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.ENGLISH).build(),
                Joke.builder().joke("""
                                Knock! Knock!
                                Who\\’s there?
                                Tiger Leon Pating Kambing
                                Tiger Leon Pating Kambing who?
                                Kailan (Kailan)
                                Kailan mo ba mapapansin ang aking lihim?
                                Tiger Leon Pating Kambing
                                Di mo pa rin pansin""")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.FILIPINO).build(),
                Joke.builder().joke("""
                                Knock! Knock!
                                Who\\’s there?
                                New Zealand!
                                New Zealand who?
                                New Zealand ka sa mundong ito, laking tuwa ng magulang mo…""")
                        .category(Category.KNOCK_KNOCK)
                        .language(Language.FILIPINO).build()
        );
        return jokes.stream().distinct().toList();
    }

    /**
     * Checks if a joke exists in the database
     *
     * @param jokesRepository the jokes jokeRequestRepository
     * @param joke            the joke to check
     * @return true if the joke exists, false otherwise
     * @see JokesRepository
     * @see Joke
     */
    static boolean doesJokeExist(JokesRepository jokesRepository, String joke) {
        return jokesRepository
                .findAll()
                .stream()
                .map(j -> j.getJoke().split("\\s"))
                .anyMatch(str -> Arrays.stream(joke.split("\\s")).allMatch(s -> isPresent(str, s)));
    }

}