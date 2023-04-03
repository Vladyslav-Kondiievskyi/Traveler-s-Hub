package com.example.travelershub.repository;

import com.example.travelershub.dto.request.FilterRequestDto;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseRepository<T> {
    default Specification<T> hasFields(FilterRequestDto filterRequestDto) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            Map<String, Object> filters = filterRequestDto.getFilters();
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof List) {
                    predicate = cb.and(predicate, root.get(field).in((List<?>) value));
                } else if (value instanceof String) {
                    predicate = cb.and(predicate, cb.like(root.get(field), "%" + value + "%"));
                } else if (value != null) {
                    predicate = cb.and(predicate, cb.equal(root.get(field), value));
                }
            }
            Map<String, String> sort = filterRequestDto.getSort();
            if (sort != null && !sort.isEmpty()) {
                String sortField = sort.keySet().stream().findFirst().orElse(null);
                if (sortField != null) {
                    String sortOrder = sort.get(sortField);
                    Sort.Direction direction = Sort.Direction.ASC;
                    if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                        direction = Sort.Direction.DESC;
                    }
                    query.orderBy(direction.isAscending() ? cb.asc(root.get(sortField)) : cb.desc(root.get(sortField)));
                }
            }
            return predicate;
        };
    }
}
