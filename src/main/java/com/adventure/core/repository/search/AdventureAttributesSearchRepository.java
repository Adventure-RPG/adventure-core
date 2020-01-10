package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureAttributes;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureAttributes} entity.
 */
public interface AdventureAttributesSearchRepository extends ElasticsearchRepository<AdventureAttributes, String> {
}
