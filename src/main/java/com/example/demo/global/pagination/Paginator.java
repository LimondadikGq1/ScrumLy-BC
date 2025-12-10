package com.example.demo.global.pagination;

import com.example.demo.global.pagination.impl.SortOrder;

import java.util.List;

public interface Paginator<T> {

    List<T> getFirstPage(Long userid,
                         int limit,
                         Sorter sortBy,
                         SortOrder order);

    List<T> getNextPage(Long userId,
                        int limit,
                        Object cursor,
                        Sorter sortBy,
                        SortOrder order);

    <V> V custCursor(Class<V> type, Object cursor);
}
