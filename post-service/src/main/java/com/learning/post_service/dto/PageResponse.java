package com.learning.post_service.dto;

import java.util.Collections;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    int currentPage;
    int totalPage;
    int pageSize;
    long totalElement;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
