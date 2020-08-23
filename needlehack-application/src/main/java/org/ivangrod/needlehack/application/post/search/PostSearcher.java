package org.ivangrod.needlehack.application.post.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.ivangrod.needlehack.domain.post.PostRepository;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;
import org.ivangrod.needlehack.domain.shared.criteria.Filters;
import org.ivangrod.needlehack.domain.shared.criteria.Order;
import org.ivangrod.needlehack.domain.shared.event.EventBus;

public class PostSearcher {

    private final PostRepository postRepository;
    private final EventBus eventBus;

    public PostSearcher(PostRepository postRepository, EventBus eventBus) {
        this.postRepository = postRepository;
        this.eventBus = eventBus;
    }

    public List<PostFound> execute(SearchingPostParams params) {

        List<HashMap<String, String>> filters = new ArrayList<>();

        // TODO: Not to hardcode fields. They must be extract from SearchingPostParams <- Controller
        filters.add(new HashMap<String, String>() {{
            put("field", "title");
            put("operator", "CONTAINS");
            put("value", params.getSearchingTerm());
        }});

        Criteria criteria = new Criteria(
                Filters.fromValues(filters),
                Order.none(),
                Optional.empty(),
                Optional.empty()
        );

        List<PostFound> postFoundsFromTerm = postRepository.matching(criteria).stream()
                .map(PostFound::fromPost).collect(Collectors.toList());
        return postFoundsFromTerm;
    }
}
