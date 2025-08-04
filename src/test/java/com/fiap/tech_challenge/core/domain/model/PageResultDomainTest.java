package com.fiap.tech_challenge.core.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageResultDomainTest {

    @DisplayName("Deve permitir criar um PageRequest com sort vazio")
    @Test
    void shouldBeCreatePageableWithSortEmpty () {
        var pageRequest = PageResultDomain.buildPageable(0, 10, "");
        assertEquals(PageRequest.class, pageRequest.getClass());
    }

    @DisplayName("Deve permitir criar um PageRequest com sort nulo")
    @Test
    void shouldBeCreatePageableWithSortNull () {
        var pageRequest = PageResultDomain.buildPageable(0, 10, null);
        assertEquals(PageRequest.class, pageRequest.getClass());
    }

    @DisplayName("Deve permitir criar um PageRequest com sort desc")
    @Test
    void shouldBeCreatePageableWithSortDesc () {
        var pageRequest = PageResultDomain.buildPageable(0, 10, "name,desc");
        assertEquals(PageRequest.class, pageRequest.getClass());
    }

    @DisplayName("Deve permitir criar um PageRequest com sort sem direção")
    @Test
    void shouldBeCreatePageableWithSortWithoutDirection () {
        var pageRequest = PageResultDomain.buildPageable(0, 10, "name");
        assertEquals(PageRequest.class, pageRequest.getClass());
    }

}
