package workflow.tests;

import data.word.IWord;
import data.word.keyword.Keyword;
import data.word.successive.WordKeywordPair;
import workflow.indexing.Indexer;
import data.word.NullWordSingleton;

import java.util.*;

/**
 * Created by koushikkrishnan on 9/26/15.
 */
public class WorkflowTest {

    private String testData = "As they rounded a bend in the path that ran beside the river, Lara recognized the silhouette of a fig tree atop a nearby hill. The weather was hot and the days were long. The fig tree was in full leaf, but not yet bearing fruit.\n" +
            "Soon Lara spotted other landmarks—an outcropping of limestone beside the path that had a silhouette like a man’s face, a marshy spot beside the river where the waterfowl were easily startled, a tall tree that looked like a man with his arms upraised. They were drawing near to the place where there was an island in the river. The island was a good spot to make camp. They would sleep on the island tonight.\n" +
            "Lara had been back and forth along the river path many times in her short life. Her people had not created the path—it had always been there, like the river—but their deerskin-shod feet and the wooden wheels of their handcarts kept the path well worn. Lara’s people were salt traders, and their livelihood took them on a continual journey.\n" +
            "At the mouth of the river, the little group of half a dozen intermingled families gathered salt from the great salt beds beside the sea. They groomed and sifted the salt and loaded it into handcarts. When the carts were full, most of the group would stay behind, taking shelter amid rocks and simple lean-tos, while a band of fifteen or so of the heartier members set out on the path that ran alongside the river.\n" +
            "With their precious cargo of salt, the travelers crossed the coastal lowlands and traveled toward the mountains. But Lara’s people never reached the mountaintops; they traveled only as far as the foothills. Many people lived in the forests and grassy meadows of the foothills, gathered in small villages. In return for salt, these people would give Lara’s people dried meat, animal skins, cloth spun from wool, clay pots, needles and scraping tools carved from bone, and little toys made of wood.\n" +
            "Their bartering done, Lara and her people would travel back down the river path to the sea. The cycle would begin again.\n" +
            "It had always been like this. Lara knew no other life. She traveled back and forth, up and down the river path. No single place was home. She liked the seaside, where there was always fish to eat, and the gentle lapping of the waves lulled her to sleep at night. She was less fond of the foothills, where the path grew steep, the nights could be cold, and views of great distances made her dizzy. She felt uneasy in the villages, and was often shy around strangers. The path itself was where she felt most at home. She loved the smell of the river on a hot day, and the croaking of frogs at night. Vines grew amid the lush foliage along the river, with berries that were good to eat. Even on the hottest day, sundown brought a cool breeze off the water, which sighed and sang amid the reeds and tall grasses.\n" +
            "Of all the places along the path, the area they were approaching, with the island in the river, was Lara’s favorite.\n" +
            "The terrain along this stretch of the river was mostly flat, but in the immediate vicinity of the island, the land on the sunrise side was like a rumpled cloth, with hills and ridges and valleys. Among Lara’s people, there was a wooden baby’s crib, suitable for strapping to a cart, that had been passed down for generations. The island was shaped like that crib, longer than it was wide and pointed at the upriver end, where the flow had eroded both banks. The island was like a crib, and the group of hills on the sunrise side of the river were like old women mantled in heavy cloaks gathered to have a look at the baby in the crib—that was how Lara’s father had once described the lay of the land.\n" +
            "Larth spoke like that all the time, conjuring images of giants and monsters in the landscape. He could perceive the spirits, called numina, that dwelled in rocks and trees. Sometimes he could speak to them and hear what they had to say. The river was his oldest friend and told him where the fishing would be best. From whispers in the wind he could foretell the next day’s weather. Because of such skills, Larth was the leader of the group.";


    public void before() {}


    public void test() {
        Set<Keyword> set = new HashSet<Keyword>();
        Indexer.index(testData, set);
    }

    public void after() {

    }

    public static void main(String[] args) {
        WorkflowTest workflowTest = new WorkflowTest();
        workflowTest.test();

        List<WordKeywordPair> p = NullWordSingleton.getInstance().getWordKeywordPairs();
        ArrayDeque<IWord> queue = new ArrayDeque<>();
        queue.addLast(NullWordSingleton.getInstance());

        while (!queue.isEmpty()) {
            IWord pop = queue.removeFirst();
            System.out.print(pop + " " + pop.getFrequency());

            for (WordKeywordPair g : pop.getWordKeywordPairs()) {
                queue.addLast(g.getWord());
                System.out.println(g.getKeywordSet());
            }
            System.out.print('\n');
        }
    }

}
