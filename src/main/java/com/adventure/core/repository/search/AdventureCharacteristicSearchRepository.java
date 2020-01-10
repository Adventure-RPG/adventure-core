package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureCharacteristic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureCharacteristic} entity.
 */
public interface AdventureCharacteristicSearchRepository extends ElasticsearchRepository<AdventureCharacteristic, String> {
}
