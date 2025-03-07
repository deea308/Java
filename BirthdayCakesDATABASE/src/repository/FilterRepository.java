package repository;

import domain.Entity;
import filter.AbstractFilter;

import java.util.*;

public class FilterRepository extends MemoryRepository{
    private AbstractFilter filter;
    public FilterRepository(AbstractFilter filter) {this.filter = filter;}


}
