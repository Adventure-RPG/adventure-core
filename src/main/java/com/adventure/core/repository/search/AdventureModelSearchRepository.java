package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureModel} entity.
 */
public interface AdventureModelSearchRepository extends ElasticsearchRepository<AdventureModel, String> {
}
