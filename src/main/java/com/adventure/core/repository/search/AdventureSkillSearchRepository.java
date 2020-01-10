package com.adventure.core.repository.search;

import com.adventure.core.domain.AdventureSkill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AdventureSkill} entity.
 */
public interface AdventureSkillSearchRepository extends ElasticsearchRepository<AdventureSkill, String> {
}
