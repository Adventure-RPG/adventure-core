package com.adventure.core.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link AdventureFractionSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AdventureFractionSearchRepositoryMockConfiguration {

    @MockBean
    private AdventureFractionSearchRepository mockAdventureFractionSearchRepository;

}
