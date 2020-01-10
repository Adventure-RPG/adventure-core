package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureScript;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureScript} entity.
 */
public interface AdventureScriptSearchRepository extends ElasticsearchRepository<AdventureScript, String> {
}
