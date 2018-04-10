package model.builders;

import model.filter.FilterByCategory;
import model.order.Order;
import model.vehicleType.Category;

public class FilterByCategoryBuilder {

    private FilterByCategory buildByCategory;

    public FilterByCategoryBuilder createFilterByCategory() {
        this.buildByCategory = new FilterByCategory();
        return this;
    }

    public FilterByCategory build() {
        return this.buildByCategory;
    }

    public FilterByCategoryBuilder withOrder(Order anyOrder) {
        this.buildByCategory.setOrder(anyOrder);
        return this;
    }

    public FilterByCategoryBuilder withCategory(Category anyCategory) {
        this.buildByCategory.setCategory(anyCategory);
        return this;
    }
}
