package model.builders;

import model.filter.ByCategory;
import model.order.Order;
import model.vehicleType.Category;

public class ByCategoryBuilder {

    private ByCategory buildByCategory;

    public ByCategoryBuilder createFilterByCategory() {
        this.buildByCategory = new ByCategory();
        return this;
    }

    public ByCategory build() {
        return this.buildByCategory;
    }

    public ByCategoryBuilder withOrder(Order anyOrder) {
        this.buildByCategory.setOrder(anyOrder);
        return this;
    }

    public ByCategoryBuilder withCategory(Category anyCategory) {
        this.buildByCategory.setCategory(anyCategory);
        return this;
    }
}
