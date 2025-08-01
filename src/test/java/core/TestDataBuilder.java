package core;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.List;

public class TestDataBuilder {
    private static final Faker faker = new Faker();

    public static Map<String, Object> buildPostData(int userId) {
        Map<String, Object> postData = new HashMap<>();
        postData.put("userId", userId);
        postData.put("title", generateDynamicTitle());
        postData.put("body", generateDynamicContent());
        return postData;
    }

    public static Map<String, Object> buildCommentData(int postId) {
        Map<String, Object> commentData = new HashMap<>();
        commentData.put("postId", postId);
        commentData.put("name", faker.name().fullName());
        commentData.put("email", faker.internet().emailAddress());
        commentData.put("body", faker.lorem().paragraph());
        return commentData;
    }

    private static String generateDynamicTitle() {
        return faker.lorem().sentence(3) + " - " + System.currentTimeMillis();
    }

    private static String generateDynamicContent() {
        int paragraphs = ThreadLocalRandom.current().nextInt(1, 4);
        List<String> paragraphsList = faker.lorem().paragraphs(paragraphs);
        return String.join("\n\n", paragraphsList);
    }
}