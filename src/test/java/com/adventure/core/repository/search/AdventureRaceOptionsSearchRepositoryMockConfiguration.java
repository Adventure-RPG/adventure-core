package com.adventure.core.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link AdventureRaceOptionsSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AdventureRaceOptionsSearchRepositoryMockConfiguration {

    @MockBean
    private AdventureRaceOptionsSearchRepository mockAdventureRaceOptionsSearchRepository;

}
