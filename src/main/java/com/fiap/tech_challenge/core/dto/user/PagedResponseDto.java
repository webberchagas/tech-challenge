package com.fiap.tech_challenge.core.dto.user;

import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PagedResponseDto<T> {

        private List<T> content;
        private int page;
        private int size;
        private long totalElements;

        public PagedResponseDto(List<T> content, int page, int size, long totalElements) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
        }
}
