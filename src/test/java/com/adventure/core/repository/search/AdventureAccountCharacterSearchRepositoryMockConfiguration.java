package com.adventure.core.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link AdventureAccountCharacterSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AdventureAccountCharacterSearchRepositoryMockConfiguration {

    @MockBean
    private AdventureAccountCharacterSearchRepository mockAdventureAccountCharacterSearchRepository;

}
