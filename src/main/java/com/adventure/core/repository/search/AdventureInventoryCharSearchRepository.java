package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureInventoryChar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureInventoryChar} entity.
 */
public interface AdventureInventoryCharSearchRepository extends ElasticsearchRepository<AdventureInventoryChar, String> {
}
