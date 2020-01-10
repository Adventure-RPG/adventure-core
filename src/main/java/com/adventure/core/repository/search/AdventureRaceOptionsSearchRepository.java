package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureRaceOptions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureRaceOptions} entity.
 */
public interface AdventureRaceOptionsSearchRepository extends ElasticsearchRepository<AdventureRaceOptions, String> {
}
