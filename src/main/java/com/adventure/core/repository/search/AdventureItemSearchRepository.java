package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureItem} entity.
 */
public interface AdventureItemSearchRepository extends ElasticsearchRepository<AdventureItem, String> {
}
